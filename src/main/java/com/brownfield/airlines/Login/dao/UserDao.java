package com.brownfield.airlines.Login.dao;

import com.brownfield.airlines.Login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    User findByLoginId(String Id);
}
