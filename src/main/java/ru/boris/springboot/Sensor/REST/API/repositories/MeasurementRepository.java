package ru.boris.springboot.Sensor.REST.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.boris.springboot.Sensor.REST.API.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
