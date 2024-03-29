package com.ironhack.ivanbank.repository;

import com.ironhack.ivanbank.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
