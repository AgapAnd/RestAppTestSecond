package ru.agapov.restapptestsecond.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agapov.restapptestsecond.models.Sensor;
import ru.agapov.restapptestsecond.repositories.SensorRepository;
import ru.agapov.restapptestsecond.util.SensorNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void save(Sensor sensor) {
        enrichSensor(sensor);
        sensorRepository.save(sensor);
    }
    public void enrichSensor(Sensor sensor) {
        // дополнение объекта сенсор какими-либо данными, которые не входили в сенсорДТО
        // в нашем случае таких данных у нас нет
    }

    public Sensor findOne(String name) {
        Optional<Sensor> foundSensor = Optional.ofNullable(sensorRepository.findSensorByName(name));
        return foundSensor.orElseThrow(SensorNotFoundException::new);
    }
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }
//    public Sensor findOne(String name) {
//        Sensor foundSensor = sensorRepository.findSensorByName(name);
//        return foundSensor;
//    }
}
