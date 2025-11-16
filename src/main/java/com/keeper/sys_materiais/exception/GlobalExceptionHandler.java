package com.keeper.sys_materiais.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_DIR = "logs";
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter LOG_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public GlobalExceptionHandler() {
        File logDirectory = new File(LOG_DIR);
        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex, WebRequest request) {
        // Logar no arquivo
        logException(ex, request);

        // Retornar resposta ao cliente
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now().toString());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("type", ex.getClass().getSimpleName());
        errorResponse.put("path", request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        logException(ex, request);

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now().toString());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("type", "RuntimeException");
        errorResponse.put("path", request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private void logException(Exception ex, WebRequest request) {
        try {
            String logFileName = generateLogFileName("backend-error");
            String logContent = formatExceptionLog(ex, request);
            writeLogToFile(logFileName, logContent);

            // Também logar no console
            System.err.println("🔴 ERRO CAPTURADO E SALVO EM: " + logFileName);
            ex.printStackTrace();

        } catch (IOException e) {
            System.err.println("❌ FALHA AO SALVAR LOG: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generateLogFileName(String prefix) {
        String date = LocalDateTime.now().format(FILE_DATE_FORMAT);
        return String.format("%s/%s_%s.txt", LOG_DIR, prefix, date);
    }

    private String formatExceptionLog(Exception ex, WebRequest request) {
        StringBuilder log = new StringBuilder();

        log.append("=".repeat(100)).append("\n");
        log.append("EXCEÇÃO CAPTURADA: ").append(LocalDateTime.now().format(LOG_DATE_FORMAT)).append("\n");
        log.append("=".repeat(100)).append("\n\n");

        log.append("TIPO         : ").append(ex.getClass().getName()).append("\n");
        log.append("MENSAGEM     : ").append(ex.getMessage()).append("\n");
        log.append("ENDPOINT     : ").append(request.getDescription(false)).append("\n");
        log.append("THREAD       : ").append(Thread.currentThread().getName()).append("\n\n");

        log.append("STACK TRACE:\n");
        log.append("-".repeat(100)).append("\n");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        log.append(sw.toString());

        log.append("-".repeat(100)).append("\n\n");

        return log.toString();
    }

    private void writeLogToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
        }
    }
}

