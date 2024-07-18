package com.brownfield.airlines.checkIn;

import com.brownfield.airlines.BookingDetails.Dao.BookingDetailsDao;
import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CheckInDetailsService {

    private final CheckInDetailsRepository checkInDetailsRepository;
    private final BookingDetailsDao bookingDetailsDao;

    @Autowired
    public CheckInDetailsService(CheckInDetailsRepository checkInDetailsRepository, BookingDetailsDao bookingDetailsDao) {
        this.checkInDetailsRepository = checkInDetailsRepository;
        this.bookingDetailsDao = bookingDetailsDao;
    }

    public CheckInDetails checkIn(CheckInDetailsDto checkInDetailsDto) {
        Optional<BookingDetails> bookingDetails = bookingDetailsDao.findById(checkInDetailsDto.getPnrNumber());
        CheckInDetails checkInDetails = CheckInDetails.builder()
                .checkInTime(LocalDateTime.now())
                .checkInStatus(true)
                .seatNumber(checkInDetailsDto.getSeatNumber())
                .build();
        return checkInDetailsRepository.save(checkInDetails);
    }
}
