package com.gestionnaire.gestionnaire.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;

import java.io.IOException;

public class CustomProSerializer extends StdSerializer<Pro> {

    public CustomProSerializer() {
        this(null);
    }

    public CustomProSerializer(Class<Pro> t) {
        super(t);
    }

    @Override
    public void serialize(Pro pro, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", pro.getId());
        gen.writeStringField("nom", pro.getNom());
        gen.writeStringField("prenom", pro.getPrenom());
        gen.writeStringField("profession", pro.getDomaine().toString());
        int i = 1;
        for(Patient patient : pro.getPatients()){

        }
        gen.writeEndObject();
    }
}
