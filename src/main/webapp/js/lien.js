function lienPartie(idPartie) {
    console.log(idPartie);

    document.location.href="/detailPartie?idPartie="+idPartie;

}

function lienCreneau(idCreneau) {
    console.log(idCreneau);

    document.location.href="/ajouterPartie?idCreneau="+idCreneau;
}