package com.example.Courier.Tracking.service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.Courier.Tracking.model.entity.CourierLocation;
import com.example.Courier.Tracking.repository.CourierLocationRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourierLocationRepositoryServiceTest {

    @Mock
    private CourierLocationRepository repository;

    @InjectMocks
    private CourierLocationRepositoryService service;

    @Test
    public void should_Save_Courier_Location() {
        CourierLocation courierLocation = new CourierLocation();

        when(repository.save(courierLocation)).thenReturn(courierLocation);

        CourierLocation savedCourierLocation = service.save(courierLocation);

        assertEquals(courierLocation, savedCourierLocation);
    }

    @Test
    public void should_Find_By_Courier_Id_Order_By_Creation_DateTime_Asc() {
        CourierLocation location1 = new CourierLocation();
        location1.setCreationDatetime(LocalDateTime.now().minusHours(1));
        CourierLocation location2 = new CourierLocation();
        location2.setCreationDatetime(LocalDateTime.now());
        List<CourierLocation> locations = Arrays.asList(location1, location2);

        when(repository.findByCourierIdOrderByCreationDatetimeAsc(1L)).thenReturn(locations);

        List<CourierLocation> result = service.findByCourierIdOrderByCreationDatetimeAsc(1L);

        assertEquals(locations, result);
    }
}
