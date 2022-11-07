package ru.boris.springboot.Sensor.REST.API.util;

public class MeasurementNotAddException extends RuntimeException{
    public MeasurementNotAddException(String msg) {
        super(msg);
    }
}
