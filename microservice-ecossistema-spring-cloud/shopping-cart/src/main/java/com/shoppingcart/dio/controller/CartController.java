package com.shoppingcart.dio.controller;

import com.shoppingcart.dio.model.Cart;
import com.shoppingcart.dio.model.Item;
import com.shoppingcart.dio.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @RequestMapping(value="/{id}", method = RequestMethod.POST)
    public Cart addItems(@PathVariable("id") Integer id, @RequestBody Item item) {
        //busca cart pelo id
        Optional<Cart> optional = cartRepository.findById(id);
        Cart cart;

        //caso seja vazio cria um novo
        if(optional.isEmpty()){
            cart = new Cart(id);
        }else{
            cart = optional.get();
        }

        //adiciona o item no cart
        cart.getItems().add(item);

        //salva o cart com o novo item
        return cartRepository.save(cart);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Cart> findById(@PathVariable("id") Integer id) {
        return cartRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void clear(@PathVariable("id") Integer id) {
        cartRepository.deleteById(id);
    }

}
