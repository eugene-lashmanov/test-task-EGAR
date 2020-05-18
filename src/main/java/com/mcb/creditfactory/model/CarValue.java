package com.mcb.creditfactory.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAR_VALUE")
public class CarValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(name = "assessed_value")
    private BigDecimal value;

    @Column(name = "id_car")
    private Long carId;

    public CarValue(BigDecimal value, Long carId) {
        this.value = value;
        this.carId = carId;
    }
}
