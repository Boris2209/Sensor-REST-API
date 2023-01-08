package ru.boris.springboot.Sensor.REST.API.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boris.springboot.Sensor.REST.API.models.Sensor;
import ru.boris.springboot.Sensor.REST.API.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepositopy;

    @Autowired
    public SensorService(SensorRepository sensorRepositopy) {
        this.sensorRepositopy = sensorRepositopy;
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepositopy.save(sensor);
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepositopy.findByName(name);
    }
}
