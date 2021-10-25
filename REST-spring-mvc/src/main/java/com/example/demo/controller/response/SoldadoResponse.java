package com.example.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class SoldadoResponse extends RepresentationModel {
    private Long id2;
    private String cpf;
    private String nome;
    private String raca;
    private String arma;

    @JsonProperty("id2")
    public Long getResourceId() {
        return id2;
    }

    @JsonProperty("id2")
    public void setId(Long id2) {
        this.id2 = id2;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }
}
