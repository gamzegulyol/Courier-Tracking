package com.example.Courier.Tracking.repository;

import com.example.Courier.Tracking.model.entity.Courier;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

    Optional<Courier> findById(Long id);
}
