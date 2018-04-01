function surligne(champ, erreur) {
    if(erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}

function verifNom(champ) {
    if(champ.value.length < 2 || champ.value.length > 25)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifAdresse(champ) {
    if(champ.value.length < 2 || champ.value.length > 40)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifCP(champ) {
    var code_postal = /^(([0-8][0-9])|(9[0-5]))[0-9]{3}$/;
    if(!code_postal.test(champ.value))
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifMail(champ) {
    var regex = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
    if(!regex.test(champ.value))
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

/*function creerCompte() {
    var requeteCreerCompte = new XMLHttpRequest();
    requeteCreerCompte.open("POST","creerComptePost",true);
    requeteCreerCompte.responseType="json";

    requeteCreerCompte.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var nom = document.querySelector("#nom").value;
    var prenom = document.querySelector("#prenom").value;
    var mail = document.querySelector("#mail").value;
    var adresse = document.querySelector("#adresse").value;
    var cp = document.querySelector("#cp").value;
    var ville = document.querySelector("#ville").value;
    var mdp = document.querySelector("#mdp").value;
    var mdpbis = document.querySelector("#mdpbis").value;
    if(mdp!==mdpbis){
        surligne(document.getElementById("mdpb"),true);
        if(document.getElementById("mdpb").innerText===""){
            var msg = document.createElement("p");
            msg.textContent = "Mot de passe different";
            document.getElementById("mdpb").appendChild(msg);
        }
        requeteCreerCompte.abort();
    }
    else {
        if(verifMail(document.querySelector("#mail")) && verifNom(document.querySelector("#nom"))
            && verifNom(document.querySelector("#prenom")) && verifAdresse(document.querySelector("#adresse"))
            && verifAdresse(document.querySelector("#ville")) && verifCP(document.querySelector("#cp"))
            && verifNom(document.querySelector("#mdp"))){
            requeteCreerCompte.send("nom=" + nom + "&prenom=" + prenom + "&mail=" + mail + "&adresse=" + adresse + "&cp=" + cp + "&ville=" + ville + "&mdp=" + mdp);
        }
    }
}


window.onload = function () {
   ocument.getElementById("creerComptePost").onsubmit = function () {
       creerCompte();
       return false;
   }
}; */