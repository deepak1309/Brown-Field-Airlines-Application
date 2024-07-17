package com.brownfield.airlines.BookingDetails.Service;

import java.util.List;
import java.util.Optional;

import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;

public interface BookingDetailsService {
	
	 List<BookingDetails> getAllBookings();

	    Optional<BookingDetails> getBookingById(String bookingId);

	    BookingDetails createBooking(BookingDetails bookingDetails);

	    BookingDetails updateBooking(String bookingId, BookingDetails bookingDetails);

	    void deleteBooking(String bookingId);

}
