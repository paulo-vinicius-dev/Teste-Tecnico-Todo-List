package br.com.teste_tecnico.gerenciador_tarefas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TarefaCriarRequest(
    @Size(min = 3, max = 100, message = "titulo deve ter o tamnho entre 3-100 caracteres")
    @NotBlank(message = "titulo nao pode ser vazio")
    String titulo,

    @Size(max = 500, message = "descricao deve ter um tamanho maximo de 500 caracteres")
    String descricao
) {
}
