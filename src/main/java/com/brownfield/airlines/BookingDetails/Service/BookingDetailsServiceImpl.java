package com.brownfield.airlines.BookingDetails.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.brownfield.airlines.BookingDetails.bookingDetailsDto.BookingDetailsDto;
import com.brownfield.airlines.BookingDetails.dto.*;
import com.brownfield.airlines.Inventory.dao.InventoryDao;
import com.brownfield.airlines.Inventory.entity.Inventory;
import com.brownfield.airlines.Login.dao.UserDao;
import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.fare.Fare;
import com.brownfield.airlines.fare.FareDao;
import com.brownfield.airlines.flightdetails.Dao.FlightRepository;
import com.brownfield.airlines.flightdetails.entity.Aircraft;
import com.brownfield.airlines.flightdetails.entity.Flight;
import com.brownfield.airlines.passengerdetails.dao.PassengerDao;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import com.brownfield.airlines.payment.Payment;
import com.brownfield.airlines.payment.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.brownfield.airlines.BookingDetails.Dao.BookingDetailsDao;
import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

    private final BookingDetailsDao bookingDetailsDao;
    private final PassengerDao passengerDao;
    private final FlightRepository flightRepository;
    private final FareDao fareDao;
    private final PaymentDao paymentDao;
    private final InventoryDao inventoryDao;
    private final UserDao userDao;


    @Autowired
    public BookingDetailsServiceImpl(BookingDetailsDao bookingDetailsDao, PassengerDao passengerDao, FlightRepository flightRepository, FareDao fareDao, PaymentDao paymentDao, InventoryDao inventoryDao, UserDao userDao) {
        this.bookingDetailsDao = bookingDetailsDao;
        this.passengerDao = passengerDao;
        this.flightRepository = flightRepository;
        this.fareDao = fareDao;
        this.paymentDao = paymentDao;
        this.inventoryDao = inventoryDao;
        this.userDao = userDao;
    }

  /*  @Override
    public List<BookingDetails> getAllBookings(String flightNumber, FareClass fareClass) {
        return bookingDetailsDao.findAllByFlightNumberAndFareClass(flightNumber,fareClass);
    }*/

   /* @Override
    public Optional<BookingDetails> getBookingById(String bookingId) {
        return bookingDetailsDao.findById(bookingId);
    }*/

    @Override
    public  BookingDetailsDtoResponse createBooking(BookingDetailsDto bookingDetailDto) {
        BookingDetails bookingDetail=new BookingDetails();
        Payment payment = null;

        User user =getCurrentUser();

       List<Optional<Passenger>> passengers = passengerDao.findAllByUser(user).stream()
                                                          .filter(p -> (p.get().getBookingDetails()==null)).collect(Collectors.toList());

       Flight flight = flightRepository.findByFlightNumber(bookingDetailDto.getFlightNumber());
       Optional<Fare> fare = fareDao.findByFlightAndFareClass(flight,bookingDetailDto.getFareClass() );


        if(bookingDetailDto!=null){
            bookingDetail = BookingDetails.builder()
                    .flight(flight).fare(fare.get()).bookingDate(LocalDateTime.now())
                    .bookingStatus(true).build();
            payment = Payment.builder().paymentDate(LocalDateTime.now()).bookingDetails(bookingDetail)
                    .totalAmount(passengers.size()*fare.get().getPrice()).paymentStatus(true).build();
        }
        else throw new RuntimeException("Booking already made for the passenger");
        BookingDetails  bookingDetails=bookingDetailsDao.save(bookingDetail);
        Payment PaymentResponse = paymentDao.save(payment);

        if(payment.isPaymentStatus()){
            Inventory inventory=inventoryDao.findByFlight(flight);
            inventory.setAvailable_seats(inventory.getAvailable_seats()-passengers.size());
            inventory.setReserved_seats(inventory.getReserved_seats()+passengers.size());
            inventoryDao.save(inventory);
        }

        for (Optional<Passenger> p: passengers) {
            p.get().setBookingDetails(bookingDetail);
            passengerDao.save(p.get());
        }

        return convertToBookingDetailsDtoResponse(bookingDetail,PaymentResponse,passengers);

    }

    private BookingDetailsDtoResponse convertToBookingDetailsDtoResponse(BookingDetails bookingDetails,Payment payment,List<Optional<Passenger>> passengers) {
        BookingDetailsDtoResponse dto = new BookingDetailsDtoResponse();
        dto.setBookingId(bookingDetails.getBookingId());
        dto.setBookingDate(bookingDetails.getBookingDate());
        dto.setBookingStatus(bookingDetails.isBookingStatus());
        dto.setFlight(convertToFlightDto(bookingDetails.getFlight()));
        dto.setPayment(convertToPaymentDto(payment));
        dto.setPassengers(convertToPassengersDto(passengers));
        return dto;
    }

    private List<PassengerDto> convertToPassengersDto(List<Optional<Passenger>> passengers){
        List<PassengerDto> listDto = new ArrayList<>();
        for(Optional<Passenger> p : passengers){
            PassengerDto dto = new PassengerDto();
            dto.setName(p.get().getName());
            dto.setGender(p.get().getGender());
            dto.setPhoneNumber(p.get().getPhoneNumber());
            dto.setEmail(p.get().getEmail());
            listDto.add(dto);
        }
        return listDto;
    }

    private FlightDto convertToFlightDto(Flight flight) {
        FlightDto dto = new FlightDto();
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setSource(flight.getSource());
        dto.setDestination(flight.getDestination());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setAircraft(convertToAircraftDto(flight.getAircraft()));
        return dto;
    }

    private AircraftDto convertToAircraftDto(Aircraft aircraft) {
        AircraftDto dto = new AircraftDto();
        dto.setModel(aircraft.getModel());
        dto.setName(aircraft.getName());
        return dto;
    }

    private PaymentDto convertToPaymentDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentStatus(payment.isPaymentStatus());
        dto.setTotalAmount(payment.getTotalAmount());
        return dto;
    }

    private User getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId;
        if (principal instanceof UserDetails) {
            loginId = ((UserDetails) principal).getUsername();
        } else {
            loginId = principal.toString();
        }
        return userDao.findByLoginId(loginId);//..orElseThrow(() -> new RuntimeException("User not found"));
    }

   /* @Override
    public BookingDetails updateBooking(Long bookingId, BookingDetails bookingDetails) {
        if (bookingDetailsDao.existsById(bookingId)) {
        	bookingDetails.setBookingId(bookingId);
            return bookingDetailsDao.save(bookingDetails);
        }
        return null;
    }*/

   /* @Override
    public void deleteBooking(String bookingId) {
        bookingDetailsDao.deleteById(bookingId);
    }*/
}
