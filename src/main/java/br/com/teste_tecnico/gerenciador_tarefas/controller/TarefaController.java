package br.com.teste_tecnico.gerenciador_tarefas.controller;

import br.com.teste_tecnico.gerenciador_tarefas.dto.TarefaAtualizarRequest;
import br.com.teste_tecnico.gerenciador_tarefas.dto.TarefaCriarRequest;
import br.com.teste_tecnico.gerenciador_tarefas.dto.TarefaResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import br.com.teste_tecnico.gerenciador_tarefas.model.Tarefa;
import br.com.teste_tecnico.gerenciador_tarefas.service.TarefaService;
import br.com.teste_tecnico.gerenciador_tarefas.util.StatusTarefa;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController 
@RequestMapping("/api/tarefas")
public class TarefaController {
    
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> criarTarefa(@Valid @RequestBody TarefaCriarRequest tarefaCriarRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.criarTarefa(tarefaCriarRequest));
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listarTarefas(@RequestParam(required = false)  StatusTarefa status) {
        return ResponseEntity.ok(tarefaService.listarTarefas(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscarTarefaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarTarefaPorId(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizarTarefa(
            @PathVariable Long id,
            @Valid @RequestBody TarefaAtualizarRequest tarefaAtualizarRequest
    ) {
        return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefaAtualizarRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Long id) {
        tarefaService.removerTarefa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
