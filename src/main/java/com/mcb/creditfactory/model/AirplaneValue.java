package com.mcb.creditfactory.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AIRPLANE_VALUE")
public class AirplaneValue extends Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "assessed_value")
    private BigDecimal value;

    @Column(name = "airplane_id")
    private Long airplaneId;

    public AirplaneValue(BigDecimal value, Long airplaneId) {
        this.dateTime = LocalDateTime.now();
        this.value = value;
        this.airplaneId = airplaneId;
    }
}
