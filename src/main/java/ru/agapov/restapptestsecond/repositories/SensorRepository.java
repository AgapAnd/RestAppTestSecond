package ru.agapov.restapptestsecond.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.agapov.restapptestsecond.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Sensor findSensorByName(String name);
}
