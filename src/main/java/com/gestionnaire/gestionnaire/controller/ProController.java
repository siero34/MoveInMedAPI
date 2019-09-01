package com.gestionnaire.gestionnaire.controller;


import com.gestionnaire.gestionnaire.dao.ProDao;
import com.gestionnaire.gestionnaire.model.Pro;
import com.gestionnaire.gestionnaire.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value ="/{id}")
    public Pro findById(@PathVariable int id){
        return proService.findById(id);
    }
}