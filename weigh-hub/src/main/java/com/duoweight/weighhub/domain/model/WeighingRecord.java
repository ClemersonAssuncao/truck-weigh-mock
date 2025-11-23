package com.duoweight.weighhub.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class WeighingRecord {
    
    private final String id;
    private final ScaleId scaleId;
    private final TruckPlate plate;
    private final Weight weight;
    private final LocalDateTime timestamp;
    
    public WeighingRecord(ScaleId scaleId, TruckPlate plate, Weight weight) {
        this.id = UUID.randomUUID().toString();
        this.scaleId = scaleId;
        this.plate = plate;
        this.weight = weight;
        this.timestamp = LocalDateTime.now();
    }
    
    public WeighingRecord(String id, ScaleId scaleId, TruckPlate plate, Weight weight, LocalDateTime timestamp) {
        this.id = id;
        this.scaleId = scaleId;
        this.plate = plate;
        this.weight = weight;
        this.timestamp = timestamp;
    }
    
    public String getId() {
        return id;
    }
    
    public ScaleId getScaleId() {
        return scaleId;
    }
    
    public TruckPlate getPlate() {
        return plate;
    }
    
    public Weight getWeight() {
        return weight;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return "WeighingRecord{" +
                "id='" + id + '\'' +
                ", scaleId=" + scaleId +
                ", plate=" + plate +
                ", weight=" + weight +
                ", timestamp=" + timestamp +
                '}';
    }
}
