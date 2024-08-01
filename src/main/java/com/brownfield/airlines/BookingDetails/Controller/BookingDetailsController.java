package com.brownfield.airlines.BookingDetails.Controller;


import com.brownfield.airlines.BookingDetails.bookingDetailsDto.BookingDetailsDto;
import com.brownfield.airlines.BookingDetails.dto.BookingDetailsDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.brownfield.airlines.BookingDetails.Service.BookingDetailsService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingDetailsController {

    private final BookingDetailsService bookingDetailsService;

    public BookingDetailsController(BookingDetailsService bookingDetailsService) {
        this.bookingDetailsService = bookingDetailsService;
    }

    /*@GetMapping
    public ResponseEntity<List<BookingDetails>> getPnrDetails(@RequestBody BookingDetailsDto bookingDetail) {
        *//*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Unauthorized");
        }*//*

        return ResponseEntity.ok(bookingDetailsService.getAllBookings(bookingDetail.getFlightNumber(),bookingDetail.getFareClass()));

    }*/

    /*@GetMapping("/{id}")
    public ResponseEntity<BookingDetails> getBookingById(@PathVariable("id") String bookingId) {
        Optional<BookingDetails> bookingDetails = bookingDetailsService.getBookingById(bookingId);
        if (bookingDetails.isPresent()) {
            return ResponseEntity.ok(bookingDetails.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @PostMapping("/create/payment")
    public ResponseEntity<BookingDetailsDtoResponse> createBookingAndHandlePayment(@RequestBody BookingDetailsDto bookingDetail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Unauthorized");
        }

        return ResponseEntity.ok(bookingDetailsService.createBooking(bookingDetail));
    }

  /*  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") String bookingId) {
        bookingDetailsService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }*/
}
