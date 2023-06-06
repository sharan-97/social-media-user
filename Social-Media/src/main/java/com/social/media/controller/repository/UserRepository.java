package com.social.media.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
