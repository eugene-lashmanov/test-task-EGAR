package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.CarValue;
import com.mcb.creditfactory.repository.CarRepository;
import com.mcb.creditfactory.repository.CarValueRepository;
import com.mcb.creditfactory.service.CollateralObjectAdapter;
import com.mcb.creditfactory.Util;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private ExternalApproveService approveService;
    private CarRepository carRepository;
    private CarValueRepository carValueRepository;
    private BigDecimal value;

    public CarServiceImpl(ExternalApproveService approveService, CarRepository carRepository, CarValueRepository carValueRepository) {
        this.approveService = approveService;
        this.carRepository = carRepository;
        this.carValueRepository = carValueRepository;
    }

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CollateralObjectAdapter(dto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car saveValue(Car car) {
        CarValue carValue = new CarValue(getValue(), car.getId());
        carValueRepository.save(carValue);
        return car;
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto dto) {
        value = dto.getValue();
        return new Car(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getPower(),
                dto.getYear()
        );
    }

    @Override
    public CarDto toDTO(Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYear(),
                Util.getLastValue(carValueRepository.findAllByCarId(car.getId()))
        );
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

    @Override
    public Long getId(CarDto carDto) {
        return carDto.getId();
    }

    public BigDecimal getValue() {
        return value;
    }
}
