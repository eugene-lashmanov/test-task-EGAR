package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.CarValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarValueRepository extends JpaRepository<CarValue, Long> {

    @Modifying
    @Query("SELECT c FROM CarValue c WHERE c.carId=:carId ORDER BY c.dateTime DESC")
    List<CarValue> findAllByCarId(@Param("carId") Long id);
}
