package com.sena.spring19.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sena.spring19.model.Genero;
import com.sena.spring19.model.IGenero;




@Controller
@SessionAttributes("genero")
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private IGenero generod;

    @GetMapping(path={"/listar"})
    public String listar(Model m){
        m.addAttribute("generos", generod.findAll());
        return "genero/listar";
    }

    @GetMapping("/verg")
    public String verg(Model m){
        Genero gen=new Genero();
        gen.setId(1);
        gen.setNombre_genero("Salsa");
        gen.setEstado_genero(false); 
        m.addAttribute("genero", gen);
        return "genero/verg";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
        Genero genero=null;
        if(id>0){
            genero=generod.findOne(id);

        }else{
            return "redirect:listar";
        }
        m.addAttribute("genero",genero);
        m.addAttribute("accion", "Actualizar Genero");
        return "genero/form";
    }

    @GetMapping("/form")
    public String form(Model m){
        Genero genero=new Genero();
        m.addAttribute("genero", genero);
        m.addAttribute("accion", "Agregar Genero");
        return "genero/form"; 

    }

    @PostMapping("/add")
    public String add(@Valid Genero genero,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "genero/form";
        }
        generod.save(genero);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        if(id > 0){
            generod.delete(id);
        }
        return "redirect:../listar";
    }
    
}
