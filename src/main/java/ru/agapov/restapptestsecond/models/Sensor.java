package ru.agapov.restapptestsecond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Sensors")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2, max = 10, message = "Name of sensor should be between 2 and 10 characters")
    @NotEmpty(message = "Name of sensor should not be empty!")
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "source", fetch = FetchType.LAZY)
    List<Measurement> measurementList;

    public Sensor(String name) {
        this.name = name;
    }
    public Sensor() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
