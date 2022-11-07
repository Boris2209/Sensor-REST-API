package ru.boris.springboot.Sensor.REST.API.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.boris.springboot.Sensor.REST.API.dto.MeasurementDTO;
import ru.boris.springboot.Sensor.REST.API.models.Measurement;
import ru.boris.springboot.Sensor.REST.API.services.MeasurementService;
import ru.boris.springboot.Sensor.REST.API.util.MeasurementNotAddException;
import ru.boris.springboot.Sensor.REST.API.util.SensorNotCreatedException;

import javax.validation.Valid;
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

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {

        Measurement measurementAdd = convertToMeasurement(measurementDTO);

        if (bindingResult.hasErrors()) {
            StringBuilder errorsMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorsMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }
            throw new MeasurementNotAddException(errorsMsg.toString());
        }

        measurementService.save(measurementAdd);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        System.out.println(measurementDTO);
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO (Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
