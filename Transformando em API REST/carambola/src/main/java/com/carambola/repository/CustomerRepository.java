package com.carambola.repository;

import com.carambola.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.role a WHERE a.id = 1 and u.active = true")
    public List<User> showEstablishments();
}
