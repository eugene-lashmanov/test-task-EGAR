package com.mcb.creditfactory.service.collateralObject;


import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("car")
public class CarObjectServiceImpl implements CollateralObjectService<Car, CarDto> {

    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private CarRepository carRepository;

    @Override
    public boolean approve(CarDto carDto) {
        return approveService.approve(new CollateralObjectAdapter(carDto)) == 0;
    }

    @Override
    public Car save(Car entity) {
        return carRepository.save(entity);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getPower(),
                carDto.getYear(),
                carDto.getValue()
        );
    }

    @Override
    public CarDto toDTO(Car entity) {
        return new CarDto(
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getPower(),
                entity.getYear(),
                entity.getValue()
        );
    }

    @Override
    public Long getId(Car entity) {
        return entity.getId();
    }
}