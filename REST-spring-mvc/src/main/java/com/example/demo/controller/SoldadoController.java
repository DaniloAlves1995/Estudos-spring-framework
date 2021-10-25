package com.example.demo.controller;

import com.example.demo.controller.request.SoldadoEditRequest;
import com.example.demo.controller.response.SoldadoListResponse;
import com.example.demo.controller.response.SoldadoResponse;
import com.example.demo.dto.Soldado;
import com.example.demo.entity.SoldadoEntity;
import com.example.demo.repository.SoldadoRepository;
import com.example.demo.resource.ResourceSoldado;
import com.example.demo.service.SoldadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//informa que é um controller de rest
@RestController
@RequestMapping("/v1/soldado")
public class SoldadoController {

    private SoldadoService service;
    private ObjectMapper objectMapper;


    public SoldadoController(SoldadoService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoldadoResponse> buscarSoldado(@PathVariable() Long id) {
        SoldadoResponse soldado = service.buscarSoldado(id);
        return ResponseEntity.status(HttpStatus.OK).body(soldado);
    }

    @PostMapping
    public ResponseEntity criarSoldado(@RequestBody Soldado soldado) {
        service.criarSoldado(soldado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editarSoldado(@PathVariable("id") Long id, @RequestBody SoldadoEditRequest soldado) {
        service.editarSoldado(id, soldado);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarSoldado(@PathVariable("id") Long id) {
        service.deletarSoldado(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/frente-castelo/{id}")
    public ResponseEntity frenteCastelo(@PathVariable("id") Long id) {
        //FAZER ALGO
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<SoldadoListResponse>> buscarSoldados() {
        CollectionModel<SoldadoListResponse> soldadoListResponses = service.buscarSoldados();
        return ResponseEntity.status(HttpStatus.OK).body(soldadoListResponses);
    }
}
