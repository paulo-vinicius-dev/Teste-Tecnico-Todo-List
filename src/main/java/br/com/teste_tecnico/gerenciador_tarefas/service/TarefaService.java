package br.com.teste_tecnico.gerenciador_tarefas.service;

import java.util.List;

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

    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas(StatusTarefa status) {
        if (status != null) {
            return tarefaRepository.findByStatus(status);
        }
        return tarefaRepository.findAll();
        
    }

    public Tarefa buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com esse ID: " + id));
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com esse ID: " + id));
        
        if (tarefa.getStatus().equals(StatusTarefa.CONCLUIDO) 
            && !tarefaAtualizada.getStatus().equals(StatusTarefa.CONCLUIDO)
        ) {
            throw new IllegalArgumentException("Tarefa com o status concluida não pode ter o status alterado");
        }

        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setStatus(tarefaAtualizada.getStatus());
        
        return tarefaRepository.save(tarefa);
    }

    public void removerTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com esse ID: " + id));
        
        tarefaRepository.delete(tarefa);
    }
}
