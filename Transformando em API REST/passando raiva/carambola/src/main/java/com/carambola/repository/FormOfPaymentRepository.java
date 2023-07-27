package com.carambola.repository;

import com.carambola.model.FormOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface FormOfPaymentRepository extends JpaRepository<FormOfPayment, Long> {
}