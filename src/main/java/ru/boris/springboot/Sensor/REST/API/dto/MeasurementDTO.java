package ru.boris.springboot.Sensor.REST.API.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.boris.springboot.Sensor.REST.API.models.Sensor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class MeasurementDTO {

    @Min(-100)
    @Max(100)
    @NotNull
    private double temperature;

    @NotNull
    private boolean raining;

    @NotNull(message = "Name should not be empty")
    private SensorDTO sensor;
}
