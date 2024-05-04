package com.example.Courier.Tracking.service.repository;

import com.example.Courier.Tracking.model.entity.Store;
import com.example.Courier.Tracking.repository.StoreRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StoreRepositoryService {

    private final StoreRepository repository;

    public Store save(Store store) {
        return repository.save(store);
    }

    public List<Store> findAll() {
        return repository.findAll();
    }


}
