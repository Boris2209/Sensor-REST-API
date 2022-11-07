package ru.boris.springboot.Sensor.REST.API.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.boris.springboot.Sensor.REST.API.dto.MeasurementDTO;
import ru.boris.springboot.Sensor.REST.API.models.Measurement;
import ru.boris.springboot.Sensor.REST.API.services.MeasurementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }


    private MeasurementDTO convertToMeasurementDTO (Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
