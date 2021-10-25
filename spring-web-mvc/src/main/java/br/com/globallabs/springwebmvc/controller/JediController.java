package br.com.globallabs.springwebmvc.controller;

import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class JediController {

    //injeta uma instancia de repositório e colocar na classe
    //a variável "repository" já é uma instância
    @Autowired
    private JediRepository repository;

    @GetMapping("/jedi")
    public ModelAndView jedi() {
        //vai redenrizar o html com o nome de jedi dentro de templats
        //return "jedi";

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi() {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-jedi");

        modelAndView.addObject("jedi", new Jedi());

        return modelAndView;
    }

    //para receber o post
    //o objeto vindo do post é o parâmetro do método
    //bindresults são resultados de erros de validação
    //redirect serve para levar infors com o redirecionamento, nesse caso leva uma mensagem
    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors())
            return "new-jedi";

        repository.save(jedi);

        redirectAttributes.addFlashAttribute("message", "Jedi cadastrado com sucesso!");

        //redireciona a página para a de nome jedi
        return "redirect:jedi";
    }
}
