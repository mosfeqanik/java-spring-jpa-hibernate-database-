package com.PayGuardTransactionAPI.Project.repository;

import com.PayGuardTransactionAPI.Project.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Mark this interface as a Spring Data Repository component
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}

