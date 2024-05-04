package com.example.Courier.Tracking.service.repository;

import com.example.Courier.Tracking.exception.CourierTrackingException;
import com.example.Courier.Tracking.model.entity.Courier;
import com.example.Courier.Tracking.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourierRepositoryServiceTest {

    @Mock
    private CourierRepository repository;

    @InjectMocks
    private CourierRepositoryService service;

    @Test
    public void should_Save_Courier() {
        Courier courier = new Courier();
        courier.setId(1L);

        when(repository.save(courier)).thenReturn(courier);

        Courier savedCourier = service.save(courier);

        assertEquals(courier, savedCourier);
    }

    @Test
    public void should_Find_Courier_By_Id_When_Exists() {
        Courier courier = new Courier();
        courier.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(courier));

        Courier foundCourier = service.findById(1L);

        assertEquals(courier, foundCourier);
    }

    @Test
    public void should_Throw_Exception_When_Courier_Not_Found() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CourierTrackingException.class, () -> service.findById(1L));
    }
}
