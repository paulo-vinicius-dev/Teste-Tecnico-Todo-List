package br.com.teste_tecnico.gerenciador_tarefas.service;

import java.util.List;

import br.com.teste_tecnico.gerenciador_tarefas.dto.TarefaAtualizarRequest;
import br.com.teste_tecnico.gerenciador_tarefas.dto.TarefaCriarRequest;
import br.com.teste_tecnico.gerenciador_tarefas.dto.TarefaResponse;
import br.com.teste_tecnico.gerenciador_tarefas.util.TarefaMapper;
import org.springframework.stereotype.Service;

import br.com.teste_tecnico.gerenciador_tarefas.model.Tarefa;
import br.com.teste_tecnico.gerenciador_tarefas.repository.TarefaRepository;
import br.com.teste_tecnico.gerenciador_tarefas.util.StatusTarefa;
import jakarta.persistence.EntityNotFoundException;

@Service 
public class TarefaService {
    
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public TarefaResponse criarTarefa(TarefaCriarRequest tarefaCriarRequest) {
        Tarefa tarefa = TarefaMapper.paraModelo(tarefaCriarRequest);
        return TarefaMapper.paraResponse(tarefaRepository.save(tarefa));
    }

    public List<TarefaResponse> listarTarefas(StatusTarefa status) {
        if (status != null) {
            return tarefaRepository.findByStatus(status).stream().map(TarefaMapper::paraResponse).toList();
        }
        return tarefaRepository.findAll().stream().map(TarefaMapper::paraResponse).toList();
    }

    public TarefaResponse buscarTarefaPorId(Long id) {
        Tarefa tarefa =  tarefaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com esse ID: " + id));
        return TarefaMapper.paraResponse(tarefa);
    }

    public TarefaResponse atualizarTarefa(Long id, TarefaAtualizarRequest tarefaAtualizada) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com esse ID: " + id));

        if (tarefa.getStatus().equals(StatusTarefa.CONCLUIDO) 
            && !tarefaAtualizada.status().equals(StatusTarefa.CONCLUIDO)
        ) {
            throw new RuntimeException("Tarefa com o status concluida não pode ter o status alterado");
        }

        tarefa.setTitulo(tarefaAtualizada.titulo());
        tarefa.setDescricao(tarefaAtualizada.descricao());
        tarefa.setStatus(tarefaAtualizada.status());
        
        return TarefaMapper.paraResponse(tarefaRepository.save(tarefa));
    }

    public void removerTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com esse ID: " + id));
        
        tarefaRepository.delete(tarefa);
    }
}
