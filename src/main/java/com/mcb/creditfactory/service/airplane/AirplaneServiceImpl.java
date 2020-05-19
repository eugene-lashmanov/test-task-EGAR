package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.AirplaneValue;
import com.mcb.creditfactory.repository.AirplaneRepository;
import com.mcb.creditfactory.repository.AirplaneValueRepository;
import com.mcb.creditfactory.service.CollateralObjectAdapter;
import com.mcb.creditfactory.util.AirplaneUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    private ExternalApproveService approveService;
    private AirplaneRepository airplaneRepository;
    private AirplaneValueRepository airplaneValueRepository;
    private BigDecimal value;

    public AirplaneServiceImpl(ExternalApproveService approveService, AirplaneRepository airplaneRepository, AirplaneValueRepository airplaneValueRepository) {
        this.approveService = approveService;
        this.airplaneRepository = airplaneRepository;
        this.airplaneValueRepository = airplaneValueRepository;
    }

    @Override
    public boolean approve(AirplaneDto dto) {
        return approveService.approve(new CollateralObjectAdapter(dto)) == 0;
    }

    @Override
    public Airplane save(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane saveValue(Airplane airplane) {
        AirplaneValue airplaneValue = new AirplaneValue(getValue(), airplane.getId());
        airplaneValueRepository.save(airplaneValue);
        return airplane;
    }

    @Override
    public Optional<Airplane> load(Long id) {
        return airplaneRepository.findById(id);
    }

    @Override
    public Airplane fromDto(AirplaneDto dto) {
        value = dto.getValue();
        return new Airplane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getManufacturer(),
                dto.getYear(),
                dto.getFuelCapacity(),
                dto.getSeats()
        );
    }

    @Override
    public AirplaneDto toDTO(Airplane airplane) {
        return new AirplaneDto(
                airplane.getId(),
                airplane.getBrand(),
                airplane.getModel(),
                airplane.getManufacturer(),
                airplane.getYear(),
                airplane.getFuelCapacity(),
                airplane.getSeats(),
                AirplaneUtil.getLastValue(airplaneValueRepository.findAllByAirplaneId(airplane.getId()))
        );
    }

    @Override
    public Long getId(Airplane airplane) {
        return airplane.getId();
    }

    @Override
    public Long getId(AirplaneDto airplaneDto) {
        return airplaneDto.getId();
    }

    public BigDecimal getValue() {
        return value;
    }
}
