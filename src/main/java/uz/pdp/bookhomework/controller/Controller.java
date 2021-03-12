package uz.pdp.bookhomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.bookhomework.service.BookService;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String home(){
        return "home";
    }


    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("book",bookService.getOne(id));
        return "edit";
    }

}
