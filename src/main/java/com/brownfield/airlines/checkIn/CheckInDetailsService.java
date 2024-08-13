package com.brownfield.airlines.checkIn;

import com.brownfield.airlines.BookingDetails.Dao.BookingDetailsDao;
import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;
import com.brownfield.airlines.passengerdetails.dao.PassengerDao;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckInDetailsService {

    private final CheckInDetailsRepository checkInDetailsRepository;
    private final BookingDetailsDao bookingDetailsDao;
    private final PassengerDao passengerDao;

    @Autowired
    public CheckInDetailsService(CheckInDetailsRepository checkInDetailsRepository, BookingDetailsDao bookingDetailsDao, PassengerDao passengerDao) {
        this.checkInDetailsRepository = checkInDetailsRepository;
        this.bookingDetailsDao = bookingDetailsDao;
        this.passengerDao = passengerDao;
    }

    public List<CheckInDetails> checkIn(CheckInDetailsDto checkInDetailsDto) throws Exception {
        Optional<BookingDetails> bookingDetails = bookingDetailsDao.findById(checkInDetailsDto.getPnrNumber());
        List<Passenger> passengers = passengerDao.findByBookingId(bookingDetails.get().getBookingId()).stream().filter(p -> p.getCheckInDetails() == null).collect(Collectors.toList());

        List<CheckInDetails> checkInDetailsList;
        if (!passengers.isEmpty()) {
            if(checkInDetailsDto.getSeatNumber().size() > passengers.size()) throw new Exception("Please select number of seats based on total passenegers in Pnr Ticket :"+checkInDetailsDto.getPnrNumber());
            checkInDetailsList = checkInDetailsDto.getSeatNumber().stream()
                    .map(seatNumber -> {
                        CheckInDetails checkInDetails = CheckInDetails.builder()
                                .checkInTime(LocalDateTime.now())
                                .checkInStatus(true)
                                .seatNumber(seatNumber)
                                .build();
                        checkInDetailsRepository.save(checkInDetails);
                        return checkInDetails;
                    })
                    .collect(Collectors.toList());

            for (int i = 0; i < checkInDetailsList.size(); i++) {
                passengers.get(i).setCheckInDetails(checkInDetailsList.get(i));
                passengerDao.save(passengers.get(i)); // Save the passenger with updated check-in details
            }
    }
        else throw new Exception("No passengers with PNR :"+checkInDetailsDto.getPnrNumber()+" left to make a checkIn");

       return checkInDetailsList;

    }
    }

