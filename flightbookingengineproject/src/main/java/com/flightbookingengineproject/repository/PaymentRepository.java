package com.flightbookingengineproject.repository;

import com.flightbookingengineproject.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentInfo,Long> {}


