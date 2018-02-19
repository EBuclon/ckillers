function ajouterCreneau() {
    var requeteAjouterCreneau = new XMLHttpRequest();
    requeteAjouterCreneau.open("POST","ajouterCreneau",true);
    requeteAjouterCreneau.responseType="json";

    requeteAjouterCreneau.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var lieu = document.querySelector("#lieuC").value;
    var date = document.querySelector("#dateC").value;
    var heure = document.querySelector("#heureC").value;

    requeteAjouterCreneau.onload = function () {
        document.getElementById("messageC").textContent = "Creneau ajoute";
        document.getElementById("ajouterCreneau").reset();
    }

    requeteAjouterCreneau.send("Lieu="+lieu+"&date="+date+"&heure="+heure);

}


window.onload = function () {
    document.getElementById("ajouterCreneau").onsubmit = function () {
        ajouterCreneau();
        return false;
    }
};