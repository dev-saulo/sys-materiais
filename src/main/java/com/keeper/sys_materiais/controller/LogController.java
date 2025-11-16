package com.keeper.sys_materiais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LogController {

    private static final String LOG_DIR = "logs";
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter LOG_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public LogController() {
        // Criar diretório de logs se não existir
        File logDirectory = new File(LOG_DIR);
        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
        }
    }

    @PostMapping("/log-error")
    public ResponseEntity<String> logError(@RequestBody Map<String, Object> errorInfo) {
        try {
            String logFileName = generateLogFileName("frontend-error");
            writeLogToFile(logFileName, formatErrorLog(errorInfo));
            return ResponseEntity.ok("Log registrado com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao salvar log: " + e.getMessage());
        }
    }

    private String generateLogFileName(String prefix) {
        String date = LocalDateTime.now().format(FILE_DATE_FORMAT);
        return String.format("%s/%s_%s.txt", LOG_DIR, prefix, date);
    }

    private String formatErrorLog(Map<String, Object> errorInfo) {
        StringBuilder log = new StringBuilder();
        log.append("=".repeat(80)).append("\n");
        log.append("ERRO CAPTURADO: ").append(LocalDateTime.now().format(LOG_DATE_FORMAT)).append("\n");
        log.append("=".repeat(80)).append("\n");

        errorInfo.forEach((key, value) -> {
            log.append(String.format("%-20s: %s\n", key, value));
        });

        log.append("-".repeat(80)).append("\n\n");
        return log.toString();
    }

    private void writeLogToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
        }
    }
}

