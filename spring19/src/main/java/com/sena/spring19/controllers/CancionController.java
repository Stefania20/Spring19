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

import com.sena.spring19.model.Cancion;
import com.sena.spring19.model.ICancion;

@Controller
@SessionAttributes("cancion")
@RequestMapping("/cancion")
public class CancionController {
    
    @Autowired
    private ICancion canciond;

    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("canciones", canciond.findAll());
        return "cancion/listar";    
    }

    @GetMapping("/verc")
    public String verc(Model m){
        Cancion can=new Cancion();
        can.setId(1);
        can.setNombre_cancion("hols");
        can.setFecha_grabacion("2022");
        can.setDuracion_cancion("2 min");
        can.setEstado_cancion(false); 
        m.addAttribute("cancion", can);
        return "cancion/verc";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
        Cancion cancion=null;
        if(id>0){
            cancion=canciond.findOne(id);

        }else{
            return "redirect:listar";
        }
        m.addAttribute("cancion",cancion);
        m.addAttribute("accion", "Actualizar Cancion");
        return "cancion/form";
    }

    @GetMapping("/form")
    public String form(Model m){
        Cancion cancion=new Cancion();
        m.addAttribute("cancion", cancion);
        m.addAttribute("accion", "Agregar Cancion");
        return "cancion/form"; 

    }

    @PostMapping("/add")
    public String add(@Valid Cancion cancion,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "cancion/form";
        }
        canciond.save(cancion);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        if(id > 0){
            canciond.delete(id);
        }
        return "redirect:../listar";
    }
    
    
}
