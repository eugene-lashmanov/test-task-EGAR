package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.CarValue;
import com.mcb.creditfactory.repository.CarRepository;
import com.mcb.creditfactory.repository.CarValueRepository;
import com.mcb.creditfactory.util.CarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarValueRepository carValueRepository;

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public CarDto saveValue(CarDto carDto) {
        CarValue carValue = new CarValue(carDto.getValue(), carDto.getId());
        carValueRepository.save(carValue);
        return carDto;
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto dto) {
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
                CarUtil.getLastValue(carValueRepository.findAllByCarId(car.getId()))
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
}
