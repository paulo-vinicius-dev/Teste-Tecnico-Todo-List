package br.com.teste_tecnico.gerenciador_tarefas.util;

import br.com.teste_tecnico.gerenciador_tarefas.dto.TarefaCriarRequest;
import br.com.teste_tecnico.gerenciador_tarefas.dto.TarefaResponse;
import br.com.teste_tecnico.gerenciador_tarefas.model.Tarefa;

public class TarefaMapper {

    public static Tarefa paraModelo(TarefaCriarRequest tarefaCriarRequest) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tarefaCriarRequest.titulo());
        tarefa.setDescricao(tarefaCriarRequest.descricao());
        return tarefa;
    }

    public static TarefaResponse paraResponse(Tarefa tarefa) {
        return new TarefaResponse(
            tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus(), tarefa.getDataCriacao()
        );
    }
}
