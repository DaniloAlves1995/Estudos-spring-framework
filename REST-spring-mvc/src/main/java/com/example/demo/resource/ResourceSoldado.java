package com.example.demo.resource;

import com.example.demo.controller.SoldadoController;
import com.example.demo.controller.response.SoldadoListResponse;
import com.example.demo.controller.response.SoldadoResponse;
import com.example.demo.entity.SoldadoEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResourceSoldado {
    private ObjectMapper objectMapper;

    public ResourceSoldado(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SoldadoListResponse criarLink(SoldadoEntity soldadoEntity) {
        SoldadoListResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity, SoldadoListResponse.class);

        Link link = WebMvcLinkBuilder.linkTo(methodOn(SoldadoController.class)
                .buscarSoldado(soldadoEntity.getId()))
                .withSelfRel();
        System.out.println(link);


        soldadoListResponse.add(link);
        return soldadoListResponse;
    }

    public SoldadoResponse criarLinkDetalhes(SoldadoEntity soldadoEntity) {
        SoldadoResponse soldadoListResponse = objectMapper.convertValue(soldadoEntity, SoldadoResponse.class);
        if(soldadoEntity.getStatus().equals("morto")){
            Link link = WebMvcLinkBuilder.linkTo(methodOn(SoldadoController.class)
                            .deletarSoldado(soldadoEntity.getId()))
                            .withRel("remover")
                            .withTitle("Deletar Soldado")
                                    .withType("delete");
            soldadoListResponse.add(link);
        }else if(soldadoEntity.getStatus().equals("vivo")){
            Link link = WebMvcLinkBuilder.linkTo(methodOn(SoldadoController.class)
                            .frenteCastelo(soldadoEntity.getId()))
                    .withRel("batalhar")
                    .withTitle("Ir pra frente do castelo")
                    .withType("put");
            soldadoListResponse.add(link);
        }



        return soldadoListResponse;
    }
}
