package ru.agapov.restapptestsecond.controllers;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.agapov.restapptestsecond.DTO.MeasurementDTO;
import ru.agapov.restapptestsecond.models.Measurement;
import ru.agapov.restapptestsecond.models.Sensor;
import ru.agapov.restapptestsecond.repositories.MeasurementRepository;
import ru.agapov.restapptestsecond.services.MeasurementService;
import ru.agapov.restapptestsecond.services.SensorService;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final MeasurementRepository measurementRepository;

    public MeasurementController(MeasurementService measurementService,
                                 SensorService sensorService, MeasurementRepository measurementRepository) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.measurementRepository = measurementRepository;
    }

    @PostMapping("/add")
    public MeasurementDTO addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        if (isSensorInDB(measurementDTO.getSource()))
            measurementService.save(convertToMeasurement(measurementDTO));
    }

    private boolean isSensorInDB(Sensor source) {
        List<Sensor> sensorList = sensorService.findAll();
        for (Sensor each : sensorList) {
            if (each.getName().equals(source.getName()))
                return false;
        }
        return true;
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = new Measurement();
        measurement.setValue(measurementDTO.getValue());
        measurement.setRaining(measurementDTO.getRaining());
        measurement.setSource(measurementDTO.getSource());
        return measurement;
    }
}
