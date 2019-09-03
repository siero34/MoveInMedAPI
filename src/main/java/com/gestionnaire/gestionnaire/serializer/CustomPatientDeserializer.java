package com.gestionnaire.gestionnaire.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.gestionnaire.gestionnaire.model.Adresse;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomPatientDeserializer extends StdDeserializer<Patient> {

    public CustomPatientDeserializer(){
        this(null);
    }

    public CustomPatientDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public Patient deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        // Récupération du champ "nom"
        String nomPatient = node.get("nom").asText();

        // Récupération du champ "prenom"
        String prenomPatient = node.get("prenom").asText();

        // Récupération de l'id du professionnel traitant
        int proId = (Integer) ((IntNode) node.get("pro_id")).numberValue();

        // Récupération des id des professionnels en relation avec le patient
        Set<Pro> pros_ids = new HashSet<>();
        if (node.has("liste_pros") && node.get("liste_pros").isArray() && node.get("liste_pros").size() > 0) {
            for (JsonNode jsonNode : node.get("liste_pros")) {
                int proid = jsonNode.asInt();
                pros_ids.add(new Pro(proid));
            }
        }

        // Récupération de la date et transformation en objet Date
        String dateDeNaissancePatient = node.get("date_de_naissance").asText();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(dateDeNaissancePatient);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Récupération de l'adresse mail
        String emailPatient = node.get("email").asText();

        // Récupération du numéro de téléphone
        String numPatient = node.get("num_tel").asText();

        // Récupération de l'adresse
        Adresse adressePatient = new Adresse(
                node.at("/adresse/adresse").asText(),
                node.at("/adresse/ville").asText(),
                node.at("/adresse/zip_code").asInt());


        return new Patient(nomPatient, prenomPatient, new Pro(proId), pros_ids, date, adressePatient, numPatient, emailPatient);
    }
}
