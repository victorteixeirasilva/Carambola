package com.carambola.repository;

import com.carambola.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<User, Long> {

//    @Query()
//    public Iterable<User> showEstablishments();
}
