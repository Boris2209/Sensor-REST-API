package ru.boris.springboot.Sensor.REST.API.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotEmpty(message = "Name should not be empty")
    private Sensor sensor;

    @Column(name = "temperature")
    @Min(-100)
    @Max(100)
    @NotNull
    private double temperature;

    @Column(name = "raining")
    @NotNull
    private boolean raining;

    @Column(name = "measurement_time")
    private LocalDateTime measurementTime;
}
