package com.example.demo.controller.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class SoldadoListResponse extends RepresentationModel {
    private Long Resourceid;
    private String nome;
    private String raca;

    @JsonProperty("Resourceid")
    public Long getResourceId() {
        return Resourceid;
    }

    @JsonProperty("Resourceid")
    public void setId(Long id) {
        this.Resourceid = id;
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
}
