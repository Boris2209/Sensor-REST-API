package ru.boris.springboot.Sensor.REST.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.boris.springboot.Sensor.REST.API.models.Sensor;

public interface SensorRepositopy extends JpaRepository<Sensor, Integer> {
}
