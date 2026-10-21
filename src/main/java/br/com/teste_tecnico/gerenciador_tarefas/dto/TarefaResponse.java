package br.com.teste_tecnico.gerenciador_tarefas.dto;

import br.com.teste_tecnico.gerenciador_tarefas.util.StatusTarefa;

import java.time.LocalDateTime;

public record TarefaResponse(
    Long id,
    String titulo,
    String descricao,
    StatusTarefa status,
    LocalDateTime dataCriacao
) {
}
