package com.group19.reportingservice.domain.repository;

import com.group19.reportingservice.domain.model.Portfolio;
import com.group19.reportingservice.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio,Long> {
    Optional<Portfolio> findByUserId(User user);
}
