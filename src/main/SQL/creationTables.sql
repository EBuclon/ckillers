CREATE TABLE Creneau(
        idCreneau   Int NOT NULL AUTO_INCREMENT,
        dateCreneau Date ,
        heure       Varchar (25) ,
        lieu        Varchar (25) ,
        idEvenement Int ,
        idPartie    Int ,
        idInscrit   Int ,
        PRIMARY KEY (idCreneau )
)ENGINE=InnoDB;

CREATE TABLE Evenement(
        idEvenement Int NOT NULL AUTO_INCREMENT,
        titre       Varchar (25) ,
        contenu     Varchar (25) ,
        imageE      Varchar (25) ,
        idCreneau   Int ,
        idInscrit   Int ,
        PRIMARY KEY (idEvenement )
)ENGINE=InnoDB;

CREATE TABLE Partie(
        idPartie      Int NOT NULL AUTO_INCREMENT,
        nomScenario   Varchar (25) ,
        nomJeu        Varchar (25) ,
        nombreMin     Int ,
        nombreMax     Int ,
        desUtil       Varchar (25) ,
        typeSoiree    Varchar (25) ,
        genre         Varchar (25) ,
        typeJ         Varchar (25) ,
        ton           Varchar (25) ,
        inspiration   Varchar (25) ,
        niveauAttendu Varchar (25) ,
        presentation  Varchar (25) ,
        image         Varchar (25) ,
        valider		  boolean,
        idCreneau     Int ,
        idInscrit     Int ,
        idInscrit_1   Int ,
        PRIMARY KEY (idPartie )
)ENGINE=InnoDB;

CREATE TABLE Nouvelle(
        idNouvelle    Int NOT NULL AUTO_INCREMENT ,
        titreNouvelle Varchar (25) ,
        texte         Varchar (25) ,
        idInscrit     Int ,
        PRIMARY KEY (idNouvelle )
)ENGINE=InnoDB;

CREATE TABLE Partenaire(
        idPartenaire          Int NOT NULL AUTO_INCREMENT ,
        nomPartenaire         Varchar (25) ,
        descriptionPartenaire Varchar (25) ,
        idInscrit             Int ,
        PRIMARY KEY (idPartenaire )
)ENGINE=InnoDB;

CREATE TABLE Inscrit(
        idInscrit       Int NOT NULL AUTO_INCREMENT ,
        nom             Varchar (25) ,
        prenom          Varchar (25) ,
        mail            Varchar (25) ,
        telephone       Varchar (25) ,
        adresse         Varchar (25) ,
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
ALTER TABLE Evenement ADD CONSTRAINT FK_Evenement_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Partie ADD CONSTRAINT FK_Partie_idCreneau FOREIGN KEY (idCreneau) REFERENCES Creneau(idCreneau);
ALTER TABLE Partie ADD CONSTRAINT FK_Partie_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Partie ADD CONSTRAINT FK_Partie_idInscrit_1 FOREIGN KEY (idInscrit_1) REFERENCES Inscrit(idInscrit);
ALTER TABLE Nouvelle ADD CONSTRAINT FK_Nouvelle_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Partenaire ADD CONSTRAINT FK_Partenaire_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Aller ADD CONSTRAINT FK_Aller_idEvenement FOREIGN KEY (idEvenement) REFERENCES Evenement(idEvenement);
ALTER TABLE Aller ADD CONSTRAINT FK_Aller_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Participer ADD CONSTRAINT FK_Participer_idInscrit FOREIGN KEY (idInscrit) REFERENCES Inscrit(idInscrit);
ALTER TABLE Participer ADD CONSTRAINT FK_Participer_idPartie FOREIGN KEY (idPartie) REFERENCES Partie(idPartie);


