package ru.agapov.restapptestsecond.util;

public class SensorNotRegisteredException extends RuntimeException{
    public SensorNotRegisteredException(String msg) {
        super(msg);
    }
}
