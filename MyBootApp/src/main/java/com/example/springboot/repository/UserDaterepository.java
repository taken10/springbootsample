package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.Entity.UserData;

public interface UserDaterepository extends JpaRepository<UserData, Integer> {

}
