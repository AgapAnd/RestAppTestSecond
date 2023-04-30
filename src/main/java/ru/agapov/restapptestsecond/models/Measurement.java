package ru.agapov.restapptestsecond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "value")
    @NotEmpty
    @Range(min = -100, max = 100, message = "Показатели этого датчика по температуре находятся вне измеряемого диапазона")
    float value;
    @NotEmpty
    @Column(name = "raining")
    Boolean raining;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    Sensor source;

    public Measurement() {}

    public Measurement(float value, Boolean raining, Sensor source) {
        this.value = value;
        this.raining = raining;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
