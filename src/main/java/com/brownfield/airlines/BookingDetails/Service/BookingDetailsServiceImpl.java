package com.brownfield.airlines.BookingDetails.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.brownfield.airlines.BookingDetails.bookingDetailsDto.BookingDetailsDto;
import com.brownfield.airlines.fare.Fare;
import com.brownfield.airlines.fare.FareDao;
import com.brownfield.airlines.flightdetails.Dao.FlightRepository;
import com.brownfield.airlines.flightdetails.entity.Flight;
import com.brownfield.airlines.passengerdetails.dao.PassengerDao;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brownfield.airlines.BookingDetails.Dao.BookingDetailsDao;
import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

    private final BookingDetailsDao bookingDetailsDao;
    private final PassengerDao passengerDao;
    private final FlightRepository flightRepository;
    private final FareDao fareDao;


    @Autowired
    public BookingDetailsServiceImpl(BookingDetailsDao bookingDetailsDao, PassengerDao passengerDao, FlightRepository flightRepository, FareDao fareDao) {
        this.bookingDetailsDao = bookingDetailsDao;
        this.passengerDao = passengerDao;
        this.flightRepository = flightRepository;
        this.fareDao = fareDao;
    }

    @Override
    public List<BookingDetails> getAllBookings() {
        return bookingDetailsDao.findAll();
    }

   /* @Override
    public Optional<BookingDetails> getBookingById(String bookingId) {
        return bookingDetailsDao.findById(bookingId);
    }*/

    @Override
    public  BookingDetails createBooking(BookingDetailsDto bookingDetailDto) {
        BookingDetails bookingDetail=new BookingDetails();

       List<Optional<Passenger>> passengers =  bookingDetailDto.getPassengerIds().stream().map(id -> passengerDao.findById(id)).collect(Collectors.toList());
        Optional<Flight> flight = flightRepository.findById(bookingDetailDto.getFlightId());
        Optional<Fare> fare = fareDao.findById(bookingDetailDto.getFareId());

        if(bookingDetailDto!=null){
            bookingDetail = BookingDetails.builder()
                    .flight(flight.get()).fare(fare.get()).bookingDate(LocalDateTime.now())
                    .bookingStatus(true).build();
        }
        BookingDetails  bookingDetails=bookingDetailsDao.save(bookingDetail);

        for (Optional<Passenger> p: passengers) {
            p.get().setBookingDetails(bookingDetail);
            passengerDao.save(p.get());
        }

        return bookingDetails;
    }

   /* @Override
    public BookingDetails updateBooking(Long bookingId, BookingDetails bookingDetails) {
        if (bookingDetailsDao.existsById(bookingId)) {
        	bookingDetails.setBookingId(bookingId);
            return bookingDetailsDao.save(bookingDetails);
        }
        return null;
    }*/

    @Override
    public void deleteBooking(String bookingId) {
        bookingDetailsDao.deleteById(bookingId);
    }
}
