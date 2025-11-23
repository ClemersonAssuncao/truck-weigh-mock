package com.truckweigh.server.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "weighing_records")
public class WeighingRecordEntity {
    
    @Id
    private String id;
    
    @Column(name = "scale_id", nullable = false)
    private String scaleId;
    
    @Column(name = "truck_plate", nullable = false)
    private String truckPlate;
    
    @Column(name = "weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;
    
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
    
    public WeighingRecordEntity() {
    }
    
    public WeighingRecordEntity(String id, String scaleId, String truckPlate, BigDecimal weight, LocalDateTime timestamp) {
        this.id = id;
        this.scaleId = scaleId;
        this.truckPlate = truckPlate;
        this.weight = weight;
        this.timestamp = timestamp;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getScaleId() {
        return scaleId;
    }
    
    public void setScaleId(String scaleId) {
        this.scaleId = scaleId;
    }
    
    public String getTruckPlate() {
        return truckPlate;
    }
    
    public void setTruckPlate(String truckPlate) {
        this.truckPlate = truckPlate;
    }
    
    public BigDecimal getWeight() {
        return weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
