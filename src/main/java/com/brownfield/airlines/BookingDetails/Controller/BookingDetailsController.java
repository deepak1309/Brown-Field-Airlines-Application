package com.brownfield.airlines.BookingDetails.Controller;


import com.brownfield.airlines.BookingDetails.bookingDetailsDto.BookingDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.airlines.BookingDetails.Entity.BookingDetails;
import com.brownfield.airlines.BookingDetails.Service.BookingDetailsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingDetailsController {

    private final BookingDetailsService bookingDetailsService;

    public BookingDetailsController(BookingDetailsService bookingDetailsService) {
        this.bookingDetailsService = bookingDetailsService;
    }

    @GetMapping
    public List<BookingDetails> getAllBookings() {
        return bookingDetailsService.getAllBookings();
    }

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
    public ResponseEntity<String> createBookingAndHandlePayment(@RequestBody BookingDetailsDto bookingDetail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Unauthorized");
        }
        bookingDetailsService.createBooking(bookingDetail);
        return ResponseEntity.ok("Payment Successfull, Booking Ticket Generated");
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<BookingDetails> updateBooking(@PathVariable("id") String bookingId, @RequestBody BookingDetails bookingDetails) {
        BookingDetails updatedBooking = bookingDetailsService.updateBooking(bookingId, bookingDetails);
        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

  /*  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") String bookingId) {
        bookingDetailsService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }*/
}
