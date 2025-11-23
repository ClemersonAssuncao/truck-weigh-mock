package com.duoweight.standalone.util;

import java.util.HashMap;
import java.util.Map;

public class DataProcessor {
    
    public Map<String, Object> processData(String input) {
        Map<String, Object> result = new HashMap<>();
        result.put("input", input);
        result.put("length", input.length());
        result.put("uppercase", input.toUpperCase());
        result.put("lowercase", input.toLowerCase());
        result.put("processed", true);
        return result;
    }
    
    public double calculateAverage(double... numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0.0;
        }
        
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }
}
