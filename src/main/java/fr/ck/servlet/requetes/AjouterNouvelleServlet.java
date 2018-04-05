package fr.ck.servlet.requetes;

import fr.ck.Service.Service;
import fr.ck.entite.Nouvelle;
import fr.ck.servlet.GenericServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajouterNouvelle")
public class AjouterNouvelleServlet extends GenericServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String titre = req.getParameter("Titre");
        String texte = req.getParameter("texte");

        String identifiantUtilisateur = (String) req.getSession().getAttribute("utilisateur");

        Nouvelle nouvelle = new Nouvelle(titre, texte, Service.getInstance().getInscritParMail(identifiantUtilisateur).getIdInscrit());
        Service.getInstance().ajouterNouvelle(nouvelle);
    }
}
