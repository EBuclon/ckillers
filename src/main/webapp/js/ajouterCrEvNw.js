function ajouterCreneau() {
    var requeteAjouterCreneau = new XMLHttpRequest();
    requeteAjouterCreneau.open("POST","ajouterCreneau",true);
    requeteAjouterCreneau.responseType="json";

    requeteAjouterCreneau.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var lieu = document.querySelector("#lieuC").value;
    var date = document.querySelector("#dateC").value;
    var heure = document.querySelector("#heureC").value;

    requeteAjouterCreneau.onload = function () {
        document.getElementById("messageC").textContent = "Créneau ajouté";
        document.getElementById("messageN").textContent = "";
        document.getElementById("messageE").textContent = "";
        document.getElementById("ajouterCreneau").reset();
    };

    requeteAjouterCreneau.send("Lieu="+lieu+"&date="+date+"&heure="+heure);

}

function ajouterEvent() {
    var requeteAjouterEvent = new XMLHttpRequest();
    requeteAjouterEvent.open("POST","ajouterEvent",true);
    requeteAjouterEvent.responseType="json";

    requeteAjouterEvent.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var titre = document.querySelector("#titreE").value;
    var contenu = document.querySelector("#contenu").value;
    var lieu = document.querySelector("#lieuE").value;
    var date = document.querySelector("#dateE").value;
    var heure = document.querySelector("#heureE").value;

    requeteAjouterEvent.onload = function () {
        document.getElementById("messageE").textContent = "Evènement ajouté";
        document.getElementById("messageN").textContent = "";
        document.getElementById("messageC").textContent = "";
        document.getElementById("ajouterEvent").reset();
    };

    requeteAjouterEvent.send("Lieu="+lieu+"&date="+date+"&heure="+heure+"&contenu="+contenu+"&titre="+titre);

}

function ajouterNouvelle() {
    var requeteAjouterNouvelle = new XMLHttpRequest();
    requeteAjouterNouvelle.open("POST","ajouterNouvelle",true);
    requeteAjouterNouvelle.responseType="json";

    requeteAjouterNouvelle.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var titre = document.querySelector("#titre").value;
    var texte = document.querySelector("#texte").value;

    requeteAjouterNouvelle.onload = function () {
        document.getElementById("messageN").textContent = "Nouvelle ajoutée";
        document.getElementById("messageC").textContent = "";
        document.getElementById("messageE").textContent = "";
        document.getElementById("ajouterNouvelle").reset();
    };

    requeteAjouterNouvelle.send("Titre="+titre+"&texte="+texte);

}


window.onload = function () {
    document.getElementById("ajouterCreneau").onsubmit = function () {
        ajouterCreneau();
        return false;
    };
    document.getElementById("ajouterEvent").onsubmit = function () {
        ajouterEvent();
        return false;
    };
    document.getElementById("ajouterNouvelle").onsubmit = function () {
        ajouterNouvelle();
        return false;
    }
};