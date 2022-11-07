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
import ru.boris.springboot.Sensor.REST.API.util.ErrorResponse;
import ru.boris.springboot.Sensor.REST.API.util.MeasurementNotAddException;
import ru.boris.springboot.Sensor.REST.API.util.MeasurementValidator;
import ru.boris.springboot.Sensor.REST.API.util.SensorNotCreatedException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Map<String, Integer> getCountRainingDay() {
        Map<String, Integer> count = new HashMap<>();
        count.put("Count", measurementService.countRuiningDay());
        return count;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {

        measurementValidator.validate(measurementDTO, bindingResult);

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
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO (Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotAddException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
