package com.example.demo.config;

import com.example.demo.enun.Raca;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.serializer.Serializer;

import java.io.IOException;

@Configuration
public class Jackson {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        //propriedades não mapeadas n quebrem
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //falha se alguma propriedade estiver vazia
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //compatibilidade de arrays
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        //Serializa datas
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(racaModuleMapper());
        return objectMapper;
    }

    public SimpleModule racaModuleMapper() {
        SimpleModule dateModule = new SimpleModule("JSONRacaModule", PackageVersion.VERSION);
        dateModule.addSerializer(Raca.class, new RacaSerialize());
        dateModule.addDeserializer(Raca.class, new RacaDeserialize());
        return dateModule;
    }

    class RacaSerialize extends StdSerializer<Raca> {
        public RacaSerialize() {
            super(Raca.class);
        }

        @Override
        public void serialize(Raca raca, JsonGenerator json, SerializerProvider provider) throws IOException {
            json.writeString(raca.getValue());
        }
    }

    class RacaDeserialize extends StdDeserializer<Raca> {
        public RacaDeserialize() {
            super(Raca.class);
        }

        @Override
        public Raca deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return Raca.of(p.getText());
        }
    }
}
