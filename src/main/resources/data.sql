
INSERT INTO pro(nom, prenom, domaine) VALUES ('Medecin','Un','kine');
INSERT INTO pro(nom, prenom, domaine) VALUES ('Medecin','Deux','generaliste');

INSERT INTO patient(nom, prenom, pro_id) VALUES ('Michel','Mich', 2);
INSERT INTO patient(nom, prenom, pro_id) VALUES ('Jean','Jean', 2);


INSERT INTO relation(patient_id, pro_id) VALUES (1,1);
INSERT INTO relation(patient_id, pro_id) VALUES (1,2);
INSERT INTO relation(patient_id, pro_id) VALUES (2,2);

