package ru.boris.springboot.Sensor.REST.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boris.springboot.Sensor.REST.API.models.Sensor;
import ru.boris.springboot.Sensor.REST.API.services.SensorService;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepositopy extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
