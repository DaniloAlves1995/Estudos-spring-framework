package br.com.globallabs.springwebmvc.rest;

import br.com.globallabs.springwebmvc.exception.JediNotFoundException.JediNotFoundException;
import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import br.com.globallabs.springwebmvc.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class JediResource {

    @Autowired
    private JediService service;

    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi() {
        return service.findAll();
    }

    //PahVariable é para receber como parâmetro do método o valor da url
    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id) throws JediNotFoundException {
        final Jedi jedi =  service.findById(id);

        return ResponseEntity.ok(jedi);
    }

    //a variável jedi vem pelo post pelo body
    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody Jedi jedi) {
        return service.save(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<Object> updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi newJedi) {

        final Jedi jedi = service.update(id, newJedi);

        return ResponseEntity.ok(jedi);
    }

    @DeleteMapping("/api/jedi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
       service.delete(id);
    }

}
