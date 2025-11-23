package com.duoweight.duoweigh.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FallbackStorage {
    
    private static final Logger logger = LoggerFactory.getLogger(FallbackStorage.class);
    private static final String FALLBACK_DIR = "failed_weighings";
    private static final DateTimeFormatter FILENAME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
    
    private final Gson gson;
    private final File fallbackDirectory;
    
    public FallbackStorage() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.fallbackDirectory = new File(FALLBACK_DIR);
        
        if (!fallbackDirectory.exists()) {
            boolean created = fallbackDirectory.mkdirs();
            if (created) {
                logger.info("Created fallback directory: {}", fallbackDirectory.getAbsolutePath());
            }
        }
    }
    
    public void saveFailedWeighing(String scaleId, String plate, BigDecimal weight, String errorMessage) {
        try {
            Map<String, Object> record = new HashMap<>();
            record.put("scaleId", scaleId);
            record.put("plate", plate);
            record.put("weight", weight);
            record.put("timestamp", LocalDateTime.now().toString());
            record.put("error", errorMessage);
            record.put("status", "FAILED");
            
            String filename = String.format("weighing_%s_%s.json", 
                    scaleId, 
                    LocalDateTime.now().format(FILENAME_FORMATTER));
            File file = new File(fallbackDirectory, filename);
            
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(record, writer);
                logger.info("Saved failed weighing to: {}", file.getAbsolutePath());
            }
            
        } catch (IOException e) {
            logger.error("Failed to save weighing record to fallback storage", e);
        }
    }
    
    public int getFailedRecordsCount() {
        File[] files = fallbackDirectory.listFiles((dir, name) -> name.endsWith(".json"));
        return files != null ? files.length : 0;
    }
    
    public File getFallbackDirectory() {
        return fallbackDirectory;
    }
}
