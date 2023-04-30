package ru.agapov.restapptestsecond.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.agapov.restapptestsecond.DTO.SensorDTO;
import ru.agapov.restapptestsecond.models.Sensor;
import ru.agapov.restapptestsecond.services.SensorService;
import ru.agapov.restapptestsecond.util.SensorErrorResponse;
import ru.agapov.restapptestsecond.util.SensorNotFoundException;
import ru.agapov.restapptestsecond.util.SensorNotRegisteredException;
import ru.agapov.restapptestsecond.util.SensorValidator;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController (SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }
    @GetMapping
    public List<Sensor> showAllSensors() {
        return sensorService.findAll();
    }
    @PostMapping("/registration")
    public SensorDTO registrateSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        // надо добавить код - проверку на наличие сенсора с именем в БД
        // для этого надо запросить список всех датчиков из БД
        //  и если датчик с таким именем уже есть, то выбросить исключение
        for (Sensor each : sensorService.findAll()) {
            if (each.getName().toLowerCase().equals(sensorDTO.getName().toLowerCase())) {
//                return ResponseEntity.ok(HttpStatus.CONFLICT);
                throw new SensorNotRegisteredException("Sensor with this name is already exist");
            }
        }

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append((error.getDefaultMessage()));
            }
            throw new SensorNotRegisteredException(errorMsg.toString());
        }
        sensorService.save(convertToSensor(sensorDTO));
//        return ResponseEntity.ok(HttpStatus.OK);
        return sensorDTO;
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                "Sensor with this name wasn't found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotRegisteredException e) {
//        SensorErrorResponse response = new SensorErrorResponse(
//                "Sensor with this name is already exist",
//                System.currentTimeMillis()
//        );
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        return sensor;
    }

}
