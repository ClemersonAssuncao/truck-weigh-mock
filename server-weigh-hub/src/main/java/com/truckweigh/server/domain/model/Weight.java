package com.truckweigh.server.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Weight {
    
    private final BigDecimal value;
    
    public Weight(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.value = value;
    }
    
    public Weight(double value) {
        this(BigDecimal.valueOf(value));
    }
    
    public BigDecimal getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return Objects.equals(value, weight.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return value.toString() + " kg";
    }
}
