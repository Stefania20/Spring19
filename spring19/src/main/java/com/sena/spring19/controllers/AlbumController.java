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

import com.sena.spring19.model.Album;
import com.sena.spring19.service.AlbumService;

@Controller
@SessionAttributes("album")
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService  albumd;
    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("albums", albumd.findAll());
        return "album/listar";    
    }

    @GetMapping("/vera")
    public String verc(Model m){
        Album alb=new Album();
        alb.setId(1);
        alb.setNombre_album("love");
        alb.setAnio_publicacion("2020");
        alb.setEstado_album(false); 
        m.addAttribute("album", alb);
        return "album/vera";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
        Album album=null;
        if(id>0){
            album=albumd.findOne(id);
        }else{
            return "redirect:listar";
        }
        m.addAttribute("album",album);
        m.addAttribute("accion", "Actualizar Album");
        return "album/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Album album=new Album();
        m.addAttribute("album", album);
        m.addAttribute("accion", "Agregar Album");
        return "album/form"; 
    }

    @PostMapping("/add")
    public String add(@Valid Album album,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "album/form";
        }
        albumd.save(album);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
		
		if(id > 0) {
			albumd.delete(id);
		}
		return "redirect:../listar";
	}


    
}
