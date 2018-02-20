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

@WebServlet("/creerCompte")
public class CreerCompteServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("creerCompte", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String mail = req.getParameter("mail");
        String adresse = req.getParameter("adresse");
        String motDePasse = req.getParameter("mdp");

        String format = "yyyy-MM-dd";

        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();

        Inscrit inscrit = new Inscrit(1,nom,prenom,mail,"null",adresse,"inscrit","null",formater.format(date),0,motDePasse);
        Service.getInstance().ajouterInscrit(inscrit);

        resp.sendRedirect("/accueil");
    }
}
