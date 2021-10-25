package br.com.globallabs.springwebmvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jedi")
public class Jedi {

    @Id
    @Column(name = "id_jedi")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 10, message = "Nome deve está entre 3 e 10 caracteres")
    @NotBlank(message = "Nome não pode estar em branco")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Sobrenome não pode estar em branco")
    @Column(name = "last_name")
    private String lastName;



    public Jedi(final String name, final String lastname) {
        this.name = name;
        this.lastName = lastname;
    }

    public Jedi(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
}
