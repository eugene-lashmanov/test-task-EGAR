package com.mcb.creditfactory.service.collateralObject;


import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneObjectServiceImpl implements CollateralObjectService<Airplane, AirplaneDto> {

    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public boolean approve(AirplaneDto airplaneDto) {
        return approveService.approve(new CollateralObjectAdapter(airplaneDto)) == 0;
    }

    @Override
    public Airplane save(Airplane entity) {
        return airplaneRepository.save(entity);
    }

    @Override
    public Optional<Airplane> load(Long id) {
        return airplaneRepository.findById(id);
    }

    @Override
    public Airplane fromDto(AirplaneDto airplaneDto) {
        return new Airplane(
                airplaneDto.getId(),
                airplaneDto.getBrand(),
                airplaneDto.getModel(),
                airplaneDto.getManufacturer(),
                airplaneDto.getYear(),
                airplaneDto.getFuelCapacity(),
                airplaneDto.getSeats(),
                airplaneDto.getValue()
        );
    }

    @Override
    public AirplaneDto toDTO(Airplane entity) {
        return new AirplaneDto(
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getManufacturer(),
                entity.getYear(),
                entity.getFuelCapacity(),
                entity.getSeats(),
                entity.getValue()
        );
    }

    @Override
    public Long getId(Airplane entity) {
        return entity.getId();
    }
}
