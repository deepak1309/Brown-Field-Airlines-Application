package com.brownfield.airlines.BookingDetails.Service;

import com.brownfield.airlines.BookingDetails.bookingDetailsDto.BookingDetailsDto;
import com.brownfield.airlines.BookingDetails.dto.BookingDetailsDtoResponse;

public interface BookingDetailsService {


	BookingDetailsDtoResponse createBooking(BookingDetailsDto bookingDetails);
	

}
