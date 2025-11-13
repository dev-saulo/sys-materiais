package com.keeper.sys_materiais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login.html";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/login.html";
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:/home.html";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "redirect:/usuarios.html";
    }

    @GetMapping("/cadastrarItem")
    public String cadastrarItem() {
        return "redirect:/cadastrarItem.html";
    }

    @GetMapping("/solicitarMaterial")
    public String solicitarMaterial() {
        return "redirect:/solicitarMaterial.html";
    }

    @GetMapping("/detalhesItem")
    public String detalhesItem() {
        return "redirect:/detalhesItem.html";
    }

    @GetMapping("/detalhesUsuario")
    public String detalhesUsuario() {
        return "redirect:/detalhesUsuario.html";
    }
}
