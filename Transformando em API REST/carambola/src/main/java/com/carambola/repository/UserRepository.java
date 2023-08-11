package com.carambola.repository;

import com.carambola.model.Catalog;
import com.carambola.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndActiveTrue(Long userId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Catalog c WHERE c.user.id = :userId")
    boolean hasCatalog(Long userId);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.formOfPayment.id != 0 AND u.id = :userId")
    boolean hasFormOfPayment(Long userId);

    @Query("SELECT u FROM User u WHERE u.active = true")
    List<User> findAllAndActiveTrue();

}
