package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajouterCrEvNews")
public class AjouterCrEvNewsServlet extends GenericServlet {
    /**
     * Lance la page d'ajout des creneaux, nouvelles et evenements avec les paramètres necessaires
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        String identifiantUtilisateur = (String) req.getSession().getAttribute("utilisateur");
        String statutUtil = (String) req.getSession().getAttribute("statut");
        if(identifiantUtilisateur == null || "".equals(identifiantUtilisateur) || "adherent".equals(statutUtil) || "inscrit".equals(statutUtil) ) {
            resp.sendRedirect("accueil");
        }else{
            context.setVariable("inscrit", new Inscrit(identifiantUtilisateur,statutUtil));
            templateEngine.process("ajouterEvent", context, resp.getWriter());
        }
    }
}
