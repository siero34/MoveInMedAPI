package com.gestionnaire.gestionnaire.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gestionnaire.gestionnaire.model.Patient;
import com.gestionnaire.gestionnaire.model.Pro;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.io.IOException;

public class CustomPatientSerializer extends StdSerializer<Patient> {


    public CustomPatientSerializer() {
        this(null);
    }

    public CustomPatientSerializer(Class<Patient> t) {
        super(t);
    }

    @Override
    public void serialize(Patient patient, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();

            gen.writeNumberField("id", patient.getId());
            gen.writeStringField("nom", patient.getNom());
            gen.writeStringField("prenom", patient.getPrenom());

            gen.writeObjectFieldStart("traitant");
                gen.writeStringField("nom", patient.getPro().getNom());
                gen.writeStringField("prenom", patient.getPro().getPrenom());
            gen.writeEndObject();

//            gen.writeArrayFieldStart("liste_pros");
//                for(Pro pro : patient.getPros()){
//                    gen.writeObjectFieldStart("");
//                    gen.writeStringField("nom", pro.getNom());
//                    gen.writeStringField("prenom", pro.getPrenom());
//                    gen.writeEndObject();
//                }
//            gen.writeEndArray();

            gen.writeEndObject();
        gen.writeEndObject();

    }
}
