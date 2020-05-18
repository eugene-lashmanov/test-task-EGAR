package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
