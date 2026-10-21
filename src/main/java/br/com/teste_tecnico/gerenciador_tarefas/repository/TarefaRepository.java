package br.com.teste_tecnico.gerenciador_tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste_tecnico.gerenciador_tarefas.model.Tarefa;
import br.com.teste_tecnico.gerenciador_tarefas.util.StatusTarefa;

@Repository 
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatus(StatusTarefa status);
}
