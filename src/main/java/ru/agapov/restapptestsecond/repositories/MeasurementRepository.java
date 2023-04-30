package ru.agapov.restapptestsecond.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.agapov.restapptestsecond.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
