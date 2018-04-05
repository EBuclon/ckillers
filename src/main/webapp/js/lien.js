function lienPartie(idPartie) {
    document.location.href="/detailPartie?idPartie="+idPartie;
}

function lienEvent(idEvent) {
    document.location.href="/detailEvenement?idEvenement="+idEvent;
}

function confirmSuppression(idCreneau) {
    //console.log(idCreneau);
    if (confirm("Voulez-vous vraiment supprimer ce creneau ?")) {
        // Clic sur OK
        document.location.href="/supprimerCreneau?idCreneau="+idCreneau;
    }
}

function validerPartie(idPartie) {
    document.location.href="/validerPartie?idPartie="+idPartie;

}

function lienCreneau(idCreneau) {
    document.location.href="/ajouterPartie?idCreneau="+idCreneau;
}
