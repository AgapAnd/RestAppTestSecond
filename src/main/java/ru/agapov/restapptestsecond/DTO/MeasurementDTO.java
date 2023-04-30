package ru.agapov.restapptestsecond.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import ru.agapov.restapptestsecond.models.Sensor;

public class MeasurementDTO {

    @NotEmpty
    @Range(min = -100, max = 100, message = "Показатели этого датчика по температуре находятся вне измеряемого диапазона")
    float value;
    @NotEmpty
    @Column(name = "raining")
    Boolean raining;

    @NotEmpty
    Sensor source;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSource() {
        return source;
    }

    public void setSource(Sensor source) {
        this.source = source;
    }
}
