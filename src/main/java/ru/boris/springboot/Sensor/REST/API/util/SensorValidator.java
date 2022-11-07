package ru.boris.springboot.Sensor.REST.API.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.boris.springboot.Sensor.REST.API.dto.SensorDTO;
import ru.boris.springboot.Sensor.REST.API.models.Sensor;
import ru.boris.springboot.Sensor.REST.API.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if (sensorService.findByName(sensorDTO.getName()).isPresent()) {
            errors.rejectValue("name", "", "Such a sensor has already been registered");
        }
    }
}
