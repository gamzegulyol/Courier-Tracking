package com.example.Courier.Tracking.repository;

import com.example.Courier.Tracking.model.entity.Courier;
import com.example.Courier.Tracking.model.entity.CourierStoreEntryLog;
import com.example.Courier.Tracking.model.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierStoreEntryLogRepository extends JpaRepository<CourierStoreEntryLog, Long> {

    CourierStoreEntryLog findFirstByCourierAndStoreOrderByEntryTimeDesc(Courier courier, Store store);

    List<CourierStoreEntryLog> findByCourierId(Long id);


}
