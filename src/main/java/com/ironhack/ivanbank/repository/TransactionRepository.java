package com.ironhack.ivanbank.repository;

import com.ironhack.ivanbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findAllByOrigin_PrimaryOwner_Passport_OrDestination_PrimaryOwner_Passport(String user, String user2);
}
