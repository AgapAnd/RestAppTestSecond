package ru.agapov.restapptestsecond.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @Size(min = 2, max = 10, message = "Name of sensor should be between 2 and 10 characters")
    @NotEmpty(message = "Name of sensor should not be empty!")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
