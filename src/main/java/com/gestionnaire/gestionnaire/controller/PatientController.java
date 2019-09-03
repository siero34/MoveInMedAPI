package com.gestionnaire.gestionnaire.controller;

import com.gestionnaire.gestionnaire.dao.PatientDao;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;
import com.gestionnaire.gestionnaire.service.PatientService;
import com.gestionnaire.gestionnaire.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ProService proService;

    @GetMapping
    public List<Patient> listePatients(){
        return patientService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> ajouterPatient(@RequestBody Patient patient){

        if(patient.getPro() != null) {
            Pro pro = proService.findById(patient.getPro().getId());
            if (pro == null) {
                return new ResponseEntity("Le pro d'id " + patient.getPro().getId() + " est introuvable dans la base de données", HttpStatus.BAD_REQUEST);
            }
        }


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
    public ResponseEntity<Patient> findById(@PathVariable int id) {
        Patient patientFound = patientService.findById(id);

        if(patientFound == null){
            return new ResponseEntity("Le patient d'id "+id+" est introuvable dans la base de données!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(patientFound);
    }


    @GetMapping(value = "/{id}/pros")
    public Set<Pro> chercherPros(@PathVariable int id){
        return patientService.getPros(id);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Patient patient = patientService.findById(id);

        if (patient == null) {
            return new ResponseEntity("Le patient d'id "+id+" est introuvable dans la base de données",HttpStatus.BAD_REQUEST);
        }

        patientService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable int id, @RequestBody Patient patient) {
        if(patientService.findById(id) == null){
            return new ResponseEntity("Le patient d'id "+id+" est introuvable dans la base de données", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(patientService.save(patient));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Patient>> search(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false)@DateTimeFormat(pattern="dd-MM-yyyy") LocalDate date){

        if((nom == null)&&(prenom == null)&&(date == null))
            return new ResponseEntity("Entrez au moins un paramètre", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(patientService.search(nom, prenom, date));

    }

}
