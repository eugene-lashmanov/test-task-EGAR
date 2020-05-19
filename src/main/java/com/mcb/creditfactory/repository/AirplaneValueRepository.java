package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.AirplaneValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirplaneValueRepository extends JpaRepository<AirplaneValue, Long> {

    @Modifying
    @Query("SELECT a FROM AirplaneValue a WHERE a.airplaneId=:airplaneId ORDER BY a.dateTime DESC")
    List<AirplaneValue> findAllByAirplaneId(@Param("airplaneId") Long id);
}
