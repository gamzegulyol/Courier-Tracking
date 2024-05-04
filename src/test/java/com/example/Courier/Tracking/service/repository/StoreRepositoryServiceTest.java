package com.example.Courier.Tracking.service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.Courier.Tracking.model.entity.Store;
import com.example.Courier.Tracking.repository.StoreRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StoreRepositoryServiceTest {

    @Mock
    private StoreRepository repository;

    @InjectMocks
    private StoreRepositoryService service;

    @Test
    public void should_Save_Store() {
        Store store = new Store();

        when(repository.save(store)).thenReturn(store);

        Store savedStore = service.save(store);

        assertEquals(store, savedStore);
    }

    @Test
    public void should_Find_All_Stores() {
        Store store1 = new Store();
        Store store2 = new Store();
        List<Store> stores = Arrays.asList(store1, store2);

        when(repository.findAll()).thenReturn(stores);

        List<Store> result = service.findAll();

        assertEquals(stores, result);
    }
}
