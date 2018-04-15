package fr.ck.servlet.requetes;

import fr.ck.entite.Creneau;
import fr.ck.servlet.GenericServlet;
import fr.ck.Service.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimerCreneau")
public class SupprimerCreneauServlet extends GenericServlet{
    /**
     * Supprimer un creneau horraire depuis la page lien.js
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!req.getSession().getAttribute("statut").equals("admin") && !req.getSession().getAttribute("statut").equals("moderateur")){
            resp.sendRedirect("accueil");
        }
        Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
        Service.getInstance().supprimerCreneau(idCreneau);
        resp.sendRedirect("/creneauPartie");
        return;
    }

}
