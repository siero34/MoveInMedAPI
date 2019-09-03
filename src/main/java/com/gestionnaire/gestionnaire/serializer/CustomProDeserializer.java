package com.gestionnaire.gestionnaire.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.gestionnaire.gestionnaire.model.Adresse;
import com.gestionnaire.gestionnaire.model.Domaine;
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

public class CustomProDeserializer extends StdDeserializer<Pro> {

    public CustomProDeserializer(){
        this(null);
    }

    public CustomProDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public Pro deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        // Récupération du nom
        String nomPro = node.get("nom").asText();

        // Récupération du prenom
        String prenomPro = node.get("prenom").asText();

        // Récupération du domaine
        String domainePro = node.get("domaine").asText();

        // Récupération de l'adresse mail
        String emailPro = node.get("email").asText();

        // Récupération du numéro de téléphone
        String numPro = node.get("num_tel").asText();

        // Récupération de l'adresse
        Adresse adressePro = new Adresse(
                node.at("/adresse/adresse").asText(),
                node.at("/adresse/ville").asText(),
                node.at("/adresse/zipCode").asInt());


        return new Pro(nomPro, prenomPro, Domaine.valueOf(domainePro), adressePro, numPro, emailPro);
    }
}
