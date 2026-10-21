package br.com.teste_tecnico.gerenciador_tarefas.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        Object message,
        String path
) {
}
