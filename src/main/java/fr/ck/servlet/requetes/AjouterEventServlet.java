package fr.ck.servlet.requetes;

import fr.ck.Service.Service;
import fr.ck.entite.Creneau;
import fr.ck.entite.Evenement;
import fr.ck.servlet.GenericServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajouterEvent")
public class AjouterEventServlet extends GenericServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lieu = req.getParameter("Lieu");
        String date = req.getParameter("date");
        String heure = req.getParameter("heure");
        String titre = req.getParameter("titre");
        String contenu = req.getParameter("contenu");

        String identifiantUtilisateur = (String) req.getSession().getAttribute("utilisateur");

        Creneau creneau = new Creneau(0, date, heure, lieu, Service.getInstance().getInscritParMail(identifiantUtilisateur));
        Evenement evenement = new Evenement(titre, contenu, creneau);
        Service.getInstance().ajouterEvent(evenement);
    }
}
