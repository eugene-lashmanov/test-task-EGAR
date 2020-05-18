package com.mcb.creditfactory.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAR_VALUE")
public class CarValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "assessed_value")
    private BigDecimal value;

    @Column(name = "car_id")
    private Long carId;

    public CarValue(BigDecimal value, Long carId) {
        this.dateTime = LocalDateTime.now();
        this.value = value;
        this.carId = carId;
    }
}
