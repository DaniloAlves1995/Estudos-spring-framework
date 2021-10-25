package br.com.globallabs.springwebmvc.service;

import br.com.globallabs.springwebmvc.exception.JediNotFoundException.JediNotFoundException;
import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    @Autowired
    private JediRepository repository;


    public Jedi findById(final Long id)  {
        final Optional<Jedi> jedi = repository.findById(id);

        if(jedi.isPresent()){
            return jedi.get();
        }else{
            throw new JediNotFoundException();
        }
    }

    public List<Jedi> findAll() {
        return repository.findAll();
    }


    public Jedi save(final Jedi jedi) {
        return repository.save(jedi);
    }

    public Jedi update(final Long id, Jedi newJedi) {
        Optional<Jedi> jediOptional = repository.findById(id);
        final Jedi oldJedi;

        if(jediOptional.isPresent()){
            oldJedi = jediOptional.get();
        }else{
            throw new JediNotFoundException();
        }

        oldJedi.setName(newJedi.getName());
        oldJedi.setLastName(newJedi.getLastName());

        return repository.save(oldJedi);
    }

    public void delete(final Long id) {
        Jedi jedi = findById(id);

        repository.delete(jedi);
    }
}
