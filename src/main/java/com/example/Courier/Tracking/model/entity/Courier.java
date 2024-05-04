package com.example.Courier.Tracking.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "courier")
@SuperBuilder
@NoArgsConstructor
public class Courier extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "courier", fetch = FetchType.LAZY)
    private List<CourierLocation> locations;

    @OneToMany(mappedBy = "courier", fetch = FetchType.LAZY)
    private List<CourierStoreEntryLog> storeEntries;

}
