package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Inscrit;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accueil")
public class AccueilServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        String identifiantUtilisateur = (String) req.getSession().getAttribute("utilisateur");
        String statutUtil = (String) req.getSession().getAttribute("statut");
        if(identifiantUtilisateur == null || "".equals(identifiantUtilisateur)) {
            context.setVariable("inscrit", new Inscrit("Visiteur"));
        }else{
            context.setVariable("inscrit", new Inscrit(identifiantUtilisateur,statutUtil));
        }
        context.setVariable("parties", Service.getInstance().listPartiesEvenements());
        templateEngine.process("accueil", context, resp.getWriter());

    }
}
