package com.gestionnaire.gestionnaire.controller;


import com.gestionnaire.gestionnaire.dao.ProDao;
import com.gestionnaire.gestionnaire.model.Domaine;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;
import com.gestionnaire.gestionnaire.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pros")
public class ProController {

    @Autowired
    private ProService proService;

    @GetMapping
    public List<Pro> findAll(){
        return proService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> addPro(@RequestBody Pro pro){
        Pro proAdded = proService.save(pro);

        if(proAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pro> findById(@PathVariable int id) {
        Pro proFound = proService.findById(id);

        if(proFound == null){
            return new ResponseEntity("Le pro d'id "+id+" est introuvable dans la base de données!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(proFound);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Pro>> search(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String domaine){

        if((nom == null)&&(prenom == null)&&(domaine == null))
            return new ResponseEntity("Entrez au moins un paramètre", HttpStatus.BAD_REQUEST);

        if(domaine != null)
            return ResponseEntity.ok(proService.search(nom, prenom, Domaine.valueOf(domaine)));

        return ResponseEntity.ok(proService.search(nom, prenom, null));
    }

    @GetMapping(value = "/domaine")
    public ResponseEntity<List<Pro>> findByDomaine(@RequestParam String domaine){
        return ResponseEntity.ok(proService.findByDomaine(Domaine.valueOf(domaine)));
    }
}
