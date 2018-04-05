function confirmSuppression() {
    var idCreneau = document.getElementById("idCreneau").textContent;
    var idEvenement = document.getElementById("idEvenement").textContent;
    if (confirm("Voulez-vous vraiment supprimer cet évènement ?")) {
        // Clic sur OK
        document.location.href="/supprimerEvenement?idEvenement="+idEvenement+"&idCreneau="+idCreneau;
    }
}

function aller() {
    var requeteParticipation = new XMLHttpRequest();
    requeteParticipation.open("POST","aller",true);
    requeteParticipation.responseType="json";

    requeteParticipation.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var idEvenement = document.getElementById("idEvenement").textContent;
    requeteParticipation.send("idEvenement="+idEvenement);
    document.location.href="/detailEvenement?idEvenement="+idEvenement;
}

function annulerAller() {
    var requeteAnnulerParticipation = new XMLHttpRequest();
    requeteAnnulerParticipation.open("POST","annulerAller",true);
    requeteAnnulerParticipation.responseType="json";

    requeteAnnulerParticipation.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var idEvenement = document.getElementById("idEvenement").textContent;
    requeteAnnulerParticipation.send("idEvenement="+idEvenement);
    document.location.href="/detailEvenement?idEvenement="+idEvenement;
}

window.onload = function () {

    document.getElementById("aller").onclick=function() {
        aller();
        return false;
    };

    document.getElementById("annulerAller").onclick=function() {
        annulerAller();
        return false;
    };

    document.getElementById("suppression").onclick=function () {
        confirmSuppression();
        return false;
    };
};