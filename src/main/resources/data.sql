
INSERT INTO pro(nom, prenom, domaine) VALUES ('Medecin','Un','kine');
INSERT INTO pro(nom, prenom, domaine) VALUES ('Medecin','Deux','generaliste');

INSERT INTO patient(nom, prenom, pro_id, date_de_naissance) VALUES ('Michel','Mich', 2, '2000-12-29');
INSERT INTO patient(nom, prenom, pro_id, date_de_naissance) VALUES ('Jean','Jean', 2, '1999-01-12');

INSERT INTO relation(patient_id, pro_id) VALUES (1,1);
INSERT INTO relation(patient_id, pro_id) VALUES (1,2);
INSERT INTO relation(patient_id, pro_id) VALUES (2,2);

INSERT INTO adresse(adresse, ville, zip_code) VALUES ('135 rue de la Chenaie', 'Montpellier', 34090);
INSERT INTO adresse(adresse, ville, zip_code) VALUES ('Move in med', 'Baillargues', 34999);

INSERT INTO patient(nom, prenom, pro_id, date_de_naissance, adresse_id) VALUES ('Paul','Jacques', 1, '1960-02-28', 1);

INSERT INTO pro(nom, prenom, domaine, adresse_id) VALUES ('John', 'Deuf', 'psy', 2);