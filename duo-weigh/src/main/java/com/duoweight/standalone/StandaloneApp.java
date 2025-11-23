package com.duoweight.standalone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StandaloneApp {
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("Duo Weight - Standalone App");
        System.out.println("=================================\n");
        
        Map<String, Object> appInfo = new HashMap<>();
        appInfo.put("name", "Duo Weight Standalone");
        appInfo.put("version", "1.0.0");
        appInfo.put("startTime", LocalDateTime.now().toString());
        appInfo.put("status", "Running");
        
        System.out.println("Application Info:");
        System.out.println(gson.toJson(appInfo));
        System.out.println();
        
        if (args.length > 0) {
            System.out.println("Arguments received:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("  [" + i + "] " + args[i]);
            }
        } else {
            System.out.println("No arguments provided.");
        }
        
        System.out.println("\nApplication completed successfully!");
    }
}
