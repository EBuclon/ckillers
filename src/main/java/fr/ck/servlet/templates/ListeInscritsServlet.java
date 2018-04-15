package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Inscrit;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/listeInscrits")
public class ListeInscritsServlet extends GenericServlet {
    /**
     * Lance la page qui liste les inscrits
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

        if(!"admin".equals(statutUtil)) {
            resp.sendRedirect("accueil");
        }else{
            context.setVariable("inscrits", Service.getInstance().listInscrit());
            context.setVariable("inscrit", new Inscrit(identifiantUtilisateur,statutUtil));
            templateEngine.process("listeInscrit", context, resp.getWriter());
        }
    }
}
