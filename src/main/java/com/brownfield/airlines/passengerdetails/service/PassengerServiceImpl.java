package com.brownfield.airlines.passengerdetails.service;

import com.brownfield.airlines.Login.dao.UserDao;
import com.brownfield.airlines.Login.entity.User;
import com.brownfield.airlines.passengerdetails.dao.PassengerDao;
import com.brownfield.airlines.passengerdetails.dto.PassengerDetailsRequestDto;
import com.brownfield.airlines.passengerdetails.entity.Passenger;
import com.brownfield.airlines.passengerdetails.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {

    private UserDao userDao;
    private PassengerDao passengerDao;

    @Autowired
    PassengerServiceImpl(UserDao userDao, PassengerDao passengerDao){
        this.userDao=userDao;
        this.passengerDao=passengerDao;
    }

    @Override
    public Passenger addPassenger(PassengerDetailsRequestDto passengerDetailsRequestDto) {
        User user =getCurrentUser();
        Passenger passenger = Passenger.builder()
                .name(passengerDetailsRequestDto.getName())
                .email(passengerDetailsRequestDto.getEmail())
                .phoneNumber(passengerDetailsRequestDto.getPhoneNumber())
                .user(user)
                .build();

        return  passengerDao.save(passenger);
    }

    public User getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId;
        if (principal instanceof UserDetails) {
            loginId = ((UserDetails) principal).getUsername();
        } else {
            loginId = principal.toString();
        }
        return userDao.findByLoginId(loginId);//..orElseThrow(() -> new RuntimeException("User not found"));
    }

}
