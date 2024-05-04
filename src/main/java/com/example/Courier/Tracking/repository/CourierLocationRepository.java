package com.example.Courier.Tracking.repository;

import com.example.Courier.Tracking.model.entity.CourierLocation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierLocationRepository extends JpaRepository<CourierLocation, Long> {

    List<CourierLocation> findByCourierIdOrderByCreationDatetimeAsc(Long courierId);
}

