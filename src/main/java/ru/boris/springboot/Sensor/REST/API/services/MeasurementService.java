package ru.boris.springboot.Sensor.REST.API.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.springboot.Sensor.REST.API.models.Measurement;
import ru.boris.springboot.Sensor.REST.API.models.Sensor;
import ru.boris.springboot.Sensor.REST.API.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        Optional<Sensor> sensor =  sensorService.findByName(measurement.getSensor().getName());
        if (sensor.isPresent())
            measurement.setSensor(sensor.get());
        measurement.setMeasurementTime(LocalDateTime.now());
        measurementRepository.save(measurement);
    }
}
