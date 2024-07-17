package com.brownfield.airlines.BookingDetails.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brownfield.airlines.BookingDetails.Dao.BookingDetailsDao;
import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

    private final BookingDetailsDao bookingDetailsDao;

    @Autowired
    public BookingDetailsServiceImpl(BookingDetailsDao bookingDetailsDao) {
        this.bookingDetailsDao = bookingDetailsDao;
    }

    @Override
    public List<BookingDetails> getAllBookings() {
        return bookingDetailsDao.findAll();
    }

    @Override
    public Optional<BookingDetails> getBookingById(String bookingId) {
        return bookingDetailsDao.findById(bookingId);
    }

    @Override
    public BookingDetails createBooking(BookingDetails bookingDetails) {
        return bookingDetailsDao.save(bookingDetails);
    }

    @Override
    public BookingDetails updateBooking(String bookingId, BookingDetails bookingDetails) {
        if (bookingDetailsDao.existsById(bookingId)) {
        	bookingDetails.setBookingId(bookingId);
            return bookingDetailsDao.save(bookingDetails);
        }
        return null;
    }

    @Override
    public void deleteBooking(String bookingId) {
        bookingDetailsDao.deleteById(bookingId);
    }
}
