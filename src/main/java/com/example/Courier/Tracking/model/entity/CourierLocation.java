package com.example.Courier.Tracking.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "courier_location")
@SuperBuilder
@NoArgsConstructor
public class CourierLocation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
    private double latitude;
    private double longitude;
}
