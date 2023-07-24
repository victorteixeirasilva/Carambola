package com.carambola.repository;

import com.carambola.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.role a WHERE a.id = 1")
    public Iterable<User> showEstablishments();
}
