package ru.boris.springboot.Sensor.REST.API.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.boris.springboot.Sensor.REST.API.models.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class MeasurementDTO {

    @NotEmpty(message = "Name should not be empty")
    private Sensor sensor;

    @Min(-100)
    @Max(100)
    @NotNull
    private double temperature;

    @NotNull
    private boolean raining;
}
