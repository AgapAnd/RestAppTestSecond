package ru.agapov.restapptestsecond.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agapov.restapptestsecond.models.Measurement;
import ru.agapov.restapptestsecond.repositories.MeasurementRepository;

@Service
@Transactional
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {

    }
}
