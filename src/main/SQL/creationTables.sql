CREATE TABLE Creneau(
        idCreneau   Int NOT NULL auto_increment,
        dateCreneau Date ,
        heure       Varchar (25) ,
        lieu        Varchar (100) ,
        idEvenement Int ,
        idPartie    Int ,
        idInscrit   Int ,
        PRIMARY KEY (idCreneau )
)ENGINE=InnoDB;

CREATE TABLE Evenement(
        idEvenement Int NOT NULL auto_increment,
        titre       Varchar (25) ,
        contenu     Varchar (2000) ,
        imageE      Varchar (35) ,
        idCreneau   Int ,
        PRIMARY KEY (idEvenement )
)ENGINE=InnoDB;

CREATE TABLE Partie(
        idPartie      Int NOT NULL auto_increment,
        nomScenario   Varchar (25) ,
        nomJeu        Varchar (25) ,
        nombreMin     Int ,
        nombreMax     Int ,
        desUtil       Varchar (25) ,
        typeSoiree    Varchar (25) ,
        genre         Varchar (25) ,
        typeJ         Varchar (25) ,
        ton           Varchar (25) ,
        inspiration   Varchar (50) ,
        niveauAttendu Varchar (30) ,
        presentation  Varchar (400) ,
        image         Varchar (35) ,
        idCreneau     Int ,
        idInscrit     Int ,
        idInscrit_1   Int ,
        PRIMARY KEY (idPartie )
)ENGINE=InnoDB;

CREATE TABLE Nouvelle(
        idNouvelle    Int NOT NULL auto_increment ,
        titreNouvelle Varchar (25) ,
        texte         Varchar (200) ,
        imageN        Varchar (35) ,
        idInscrit     Int ,
        PRIMARY KEY (idNouvelle )
)ENGINE=InnoDB;

CREATE TABLE Partenaire(
        idPartenaire          Int NOT NULL auto_increment ,
        nomPartenaire         Varchar (25) ,
        descriptionPartenaire Varchar (400) ,
        imageP                Varchar (35) ,
        idInscrit             Int ,
        PRIMARY KEY (idPartenaire )
)ENGINE=InnoDB;

CREATE TABLE Inscrit(
        idInscrit       Int NOT NULL auto_increment ,
        nom             Varchar (25) ,
        prenom          Varchar (25) ,
        mail            Varchar (40) ,
        telephone       Varchar (25) ,
        adresse         Varchar (100) ,
        statut          Varchar (25) ,
        dateAdhesion    Date ,
        dateInscription Date ,
        nbrPartieJouees Int ,
        motDePasse      Varchar (25) ,
        PRIMARY KEY (idInscrit )
)ENGINE=InnoDB;

CREATE TABLE Aller(
        idEvenement Int NOT NULL ,
        idInscrit   Int NOT NULL ,
        PRIMARY KEY (idEvenement ,idInscrit )
)ENGINE=InnoDB;

CREATE TABLE Participer(
        idInscrit Int NOT NULL ,
        idPartie  Int NOT NULL ,
        PRIMARY KEY (idInscrit ,idPartie )
)ENGINE=InnoDB;

ALTER TABLE Creneau ADD CONSTRAINT FK_Creneau_idEvenement FOREIGN KEY (idEvenement) REFERENCES Evenement(idEvenement);
ALTER TABLE Creneau ADD CONSTRAINT FK_Creneau_idPartie FOREIGN KEY (idPartie) REFERENCES Partie(idPartie);
ALTER TABLE Creneau ADD CONSTRAINT FK_Creneau_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Evenement ADD CONSTRAINT FK_Evenement_idCreneau FOREIGN KEY (idCreneau) REFERENCES Creneau(idCreneau);
ALTER TABLE Partie ADD CONSTRAINT FK_Partie_idCreneau FOREIGN KEY (idCreneau) REFERENCES Creneau(idCreneau);
ALTER TABLE Partie ADD CONSTRAINT FK_Partie_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Partie ADD CONSTRAINT FK_Partie_idInscrit_1 FOREIGN KEY (idInscrit_1) REFERENCES Inscrit(idInscrit);
ALTER TABLE Nouvelle ADD CONSTRAINT FK_Nouvelle_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Partenaire ADD CONSTRAINT FK_Partenaire_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Aller ADD CONSTRAINT FK_Aller_idEvenement FOREIGN KEY (idEvenement) REFERENCES Evenement(idEvenement);
ALTER TABLE Aller ADD CONSTRAINT FK_Aller_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Participer ADD CONSTRAINT FK_Participer_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Participer ADD CONSTRAINT FK_Participer_idPartie FOREIGN KEY (idPartie) REFERENCES Partie(idPartie);


