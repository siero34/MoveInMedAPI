package com.gestionnaire.gestionnaire.controller;

import com.gestionnaire.gestionnaire.dao.PatientDao;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;
import com.gestionnaire.gestionnaire.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> listePatients(){
        return patientService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> ajouterPatient(@RequestBody Patient patient){
        Patient patientAdded = patientService.save(patient);

        if(patientAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}")
    public Patient chercherPatient(@PathVariable int id){
        return patientService.findById(id);
    }

    @GetMapping(value = "/{id}/pros")
    public Set<Pro> chercherPros(@PathVariable int id){
        return patientService.getPros(id);
    }
}
