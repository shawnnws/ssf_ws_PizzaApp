package sg.com.nus.iss.workshop_pizzaapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.com.nus.iss.workshop_pizzaapp.model.Delivery;
import sg.com.nus.iss.workshop_pizzaapp.model.Order;
import sg.com.nus.iss.workshop_pizzaapp.model.Pizza;
import sg.com.nus.iss.workshop_pizzaapp.service.PizzaService;

@Controller
public class PizzaController {
    @Autowired
    private PizzaService pizzaSvc;

    @PostMapping(path="/pizza")
    public String postPizza(Model model , HttpSession sess, @Valid Pizza pizza,
        BindingResult bindings){
        if(bindings.hasErrors())
            return "index";
        
        List<ObjectError> errors = pizzaSvc.validatePizzaOrder(pizza);
        if(!errors.isEmpty()){
            for(ObjectError e: errors)
                bindings.addError(e);
            return "index";
        } 
        sess.setAttribute("pizza", pizza);
        model.addAttribute("delivery", new Delivery());
        return "delivery";
    }

    @PostMapping(path="/pizza/order")
    public String postPizzaOrder(Model model, HttpSession session,
        @Valid Delivery delivery, BindingResult bindings){
        if(bindings.hasErrors()){
            return "delivery";
        }

        Pizza p = (Pizza)session.getAttribute("pizza");
        Order o = pizzaSvc.savePizzaOrder(p, delivery);
        model.addAttribute("order", o);
        return "order";
    }
}
