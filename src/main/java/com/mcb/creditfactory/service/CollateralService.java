package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: reimplement this
@Service
public class CollateralService {

    private AirplaneService airplaneService;
    private CarService carService;

    public CollateralService(AirplaneService airplaneService, CarService carService) {
        this.airplaneService = airplaneService;
        this.carService = carService;
    }

    public Long saveCollateral(Collateral object) {
        if (object instanceof CarDto) {
            CarDto carDto = (CarDto) object;
            return saveProcessingForCar(carDto);
        } else if (object instanceof AirplaneDto) {
            AirplaneDto airplaneDto = (AirplaneDto) object;
            return saveProcessingForAirplane(airplaneDto);
        }
        throw new IllegalArgumentException();
    }

    public Collateral getInfo(Collateral object) {
        if (object instanceof CarDto) {
            CarDto carDto = (CarDto) object;
            return infoProcessingForCar(carDto);
        } else if (object instanceof AirplaneDto) {
            AirplaneDto airplaneDto = (AirplaneDto) object;
            return infoProcessingForAirplane(airplaneDto);
        }
        throw new IllegalArgumentException();
    }

    public Long estimate(Collateral object) {
        if (object instanceof CarDto) {
            CarDto carDto = (CarDto) object;
            return estimateCar(carDto);
        } else if (object instanceof AirplaneDto) {
            AirplaneDto airplaneDto = (AirplaneDto) object;
            return estimateAirplane(airplaneDto);
        }
        throw new IllegalArgumentException();
    }

    private Long estimateCar(CarDto carDto) {
        return Optional.of(carDto)
                .map(carService::fromDto)
                .map(carService::saveValue)
                .map(carService::getId)
                .orElse(null);
    }

    private Long estimateAirplane(AirplaneDto airplaneDto) {
        return Optional.of(airplaneDto)
                .map(airplaneService::fromDto)
                .map(airplaneService::saveValue)
                .map(airplaneService::getId)
                .orElse(null);
    }

    private Long saveProcessingForCar(CarDto carDto) {
        boolean approved = carService.approve(carDto);
        if (!approved) {
            return null;
        }
        return Optional.of(carDto)
                .map(carService::fromDto)
                .map(carService::save)
                .map(carService::saveValue)
                .map(carService::getId)
                .orElse(null);
    }

    private Long saveProcessingForAirplane(AirplaneDto airplaneDto) {
        boolean approved = airplaneService.approve(airplaneDto);
        if (!approved) {
            return null;
        }
        return Optional.of(airplaneDto)
                .map(airplaneService::fromDto)
                .map(airplaneService::save)
                .map(airplaneService::saveValue)
                .map(airplaneService::getId)
                .orElse(null);
    }

    private Collateral infoProcessingForCar(CarDto carDto) {
        return Optional.of(carDto)
                .map(carService::fromDto)
                .map(carService::getId)
                .flatMap(carService::load)
                .map(carService::toDTO)
                .orElse(null);
    }

    private Collateral infoProcessingForAirplane(AirplaneDto airplaneDto) {
        return Optional.of(airplaneDto)
                .map(airplaneService::fromDto)
                .map(airplaneService::getId)
                .flatMap(airplaneService::load)
                .map(airplaneService::toDTO)
                .orElse(null);
    }
}
