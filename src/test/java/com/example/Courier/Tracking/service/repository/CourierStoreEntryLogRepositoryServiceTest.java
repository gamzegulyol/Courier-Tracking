package com.example.Courier.Tracking.service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.Courier.Tracking.model.entity.Courier;
import com.example.Courier.Tracking.model.entity.CourierStoreEntryLog;
import com.example.Courier.Tracking.model.entity.Store;
import com.example.Courier.Tracking.repository.CourierStoreEntryLogRepository;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourierStoreEntryLogRepositoryServiceTest {

    @Mock
    private CourierStoreEntryLogRepository repository;

    @InjectMocks
    private CourierStoreEntryLogRepositoryService service;

    @Test
    public void testFindFirstByCourierAndStoreOrderByEntryTimeDesc() {
        Courier courier = new Courier();
        Store store = new Store();
        CourierStoreEntryLog entryLog = new CourierStoreEntryLog();
        entryLog.setCourier(courier);
        entryLog.setStore(store);
        entryLog.setEntryTime(Instant.now());

        when(repository.findFirstByCourierAndStoreOrderByEntryTimeDesc(courier, store)).thenReturn(entryLog);

        CourierStoreEntryLog result = service.findFirstByCourierAndStoreOrderByEntryTimeDesc(courier, store);

        assertEquals(entryLog, result);
    }

    @Test
    public void testFindByCourierId() {
        Long courierId = 1L;
        List<CourierStoreEntryLog> entryLogs = Collections.singletonList(new CourierStoreEntryLog());

        when(repository.findByCourierId(courierId)).thenReturn(entryLogs);

        List<CourierStoreEntryLog> result = service.findByCourierId(courierId);

        assertEquals(entryLogs, result);
    }
}
