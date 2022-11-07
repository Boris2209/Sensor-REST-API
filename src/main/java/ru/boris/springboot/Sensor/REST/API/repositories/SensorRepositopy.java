package ru.boris.springboot.Sensor.REST.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boris.springboot.Sensor.REST.API.models.Sensor;

@Repository
public interface SensorRepositopy extends JpaRepository<Sensor, Integer> {
}
