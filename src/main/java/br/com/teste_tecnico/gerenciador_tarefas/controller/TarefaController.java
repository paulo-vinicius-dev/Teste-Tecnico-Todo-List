package br.com.teste_tecnico.gerenciador_tarefas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste_tecnico.gerenciador_tarefas.model.Tarefa;
import br.com.teste_tecnico.gerenciador_tarefas.service.TarefaService;
import br.com.teste_tecnico.gerenciador_tarefas.util.StatusTarefa;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/api/tarefas")
public class TarefaController {
    
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa tarefaCriada = tarefaService.criarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(@RequestParam(required = false)  StatusTarefa status) {
        return ResponseEntity.ok(tarefaService.listarTarefas(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTarefaPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tarefaService.buscarTarefaPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<?> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        try {
            return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefa));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        try {
            tarefaService.removerTarefa(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
