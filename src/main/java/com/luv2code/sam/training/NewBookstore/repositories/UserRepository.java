package com.luv2code.sam.training.NewBookstore.repositories;

import com.luv2code.sam.training.NewBookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

}

