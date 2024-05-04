package com.example.Courier.Tracking.service.repository;

import com.example.Courier.Tracking.exception.CourierTrackingException;
import com.example.Courier.Tracking.model.entity.Courier;
import com.example.Courier.Tracking.model.enums.ErrorCode;
import com.example.Courier.Tracking.repository.CourierRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CourierRepositoryService {

    private final CourierRepository repository;

    public Courier save(Courier courier) {
        return repository.save(courier);
    }

    public Courier findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new CourierTrackingException(ErrorCode.COURIER_NOT_FOUND));
    }

}
