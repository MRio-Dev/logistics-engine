package com.fleetOps.logistics_engine.entity;

import com.fleetOps.logistics_engine.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "truck_trip",
        indexes = {
                @Index(name = "idx_status", columnList = "status"),
                @Index(name = "idx_truck", columnList = "truck_number")
        })
public class TruckTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String truckNumber;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    private Double distanceKm;
    private Double mileage;

    private BigDecimal dieselRate;
    private Double dieselConsumption;

    private BigDecimal tollCost;

    private BigDecimal ratePerKm;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime completedAt;

}
