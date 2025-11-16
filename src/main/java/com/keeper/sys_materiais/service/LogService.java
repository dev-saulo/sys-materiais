package com.keeper.sys_materiais.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogService {

    private static final String LOG_DIR = "logs";
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter LOG_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public LogService() {
        File logDirectory = new File(LOG_DIR);
        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
            System.out.println("📁 Diretório de logs criado: " + logDirectory.getAbsolutePath());
        }
    }

    /**
     * Log de informação geral
     */
    public void info(String message) {
        logToFile("info", "INFO", message);
    }

    /**
     * Log de warning
     */
    public void warn(String message) {
        logToFile("warn", "WARN", message);
        System.err.println("⚠️  " + message);
    }

    /**
     * Log de erro
     */
    public void error(String message) {
        logToFile("error", "ERROR", message);
        System.err.println("❌ " + message);
    }

    /**
     * Log de erro com exceção
     */
    public void error(String message, Exception e) {
        StringBuilder errorLog = new StringBuilder();
        errorLog.append(message).append("\n");
        errorLog.append("Exceção: ").append(e.getClass().getName()).append("\n");
        errorLog.append("Mensagem: ").append(e.getMessage()).append("\n");
        errorLog.append("Stack Trace:\n");

        for (StackTraceElement element : e.getStackTrace()) {
            errorLog.append("  at ").append(element.toString()).append("\n");
        }

        logToFile("error", "ERROR", errorLog.toString());
        System.err.println("❌ " + message);
        e.printStackTrace();
    }

    /**
     * Log de debug
     */
    public void debug(String message) {
        logToFile("debug", "DEBUG", message);
    }

    /**
     * Log customizado com categoria específica
     */
    public void log(String category, String level, String message) {
        logToFile(category, level, message);
    }

    private void logToFile(String category, String level, String message) {
        try {
            String logFileName = generateLogFileName(category);
            String formattedLog = formatLog(level, message);
            writeLogToFile(logFileName, formattedLog);
        } catch (IOException e) {
            System.err.println("ERRO AO GRAVAR LOG: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generateLogFileName(String category) {
        String date = LocalDateTime.now().format(FILE_DATE_FORMAT);
        return String.format("%s/%s_%s.txt", LOG_DIR, category, date);
    }

    private String formatLog(String level, String message) {
        return String.format("[%s] [%s] %s\n",
            LocalDateTime.now().format(LOG_DATE_FORMAT),
            level,
            message);
    }

    private void writeLogToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
        }
    }
}

