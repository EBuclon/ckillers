function confirmSuppression() {
    var idCreneau = document.getElementById("idCreneau").textContent;
    var idPartie = document.getElementById("idPartie").textContent;
    if (confirm("Voulez-vous vraiment supprimer cette partie ?")) {
        // Clic sur OK
        document.location.href="/supprimerPartie?idPartie="+idPartie+"&idCreneau="+idCreneau;
    }
}

function participer() {
    var requeteParticipation = new XMLHttpRequest();
    requeteParticipation.open("POST","participer",true);
    requeteParticipation.responseType="json";

    requeteParticipation.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var idPartie = document.getElementById("idPartie").textContent;
    requeteParticipation.send("idPartie="+idPartie);
    document.location.href="/detailPartie?idPartie="+idPartie;
}

function annulerParticiper() {
    var requeteAnnulerParticipation = new XMLHttpRequest();
    requeteAnnulerParticipation.open("POST","annulerParticiper",true);
    requeteAnnulerParticipation.responseType="json";

    requeteAnnulerParticipation.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var idPartie = document.getElementById("idPartie").textContent;
    requeteAnnulerParticipation.send("idPartie="+idPartie);
    document.location.href="/detailPartie?idPartie="+idPartie;
}

window.onload = function () {

    console.log(document.getElementById("joueur"));

    document.getElementById("participer").onclick=function() {
        participer();

        //
        return false;
    };

    document.getElementById("annulerParticiper").onclick=function() {
        annulerParticiper();
        //
        return false;
    };

    document.getElementById("suppression").onclick=function () {
        confirmSuppression();
        return false;
    };
};