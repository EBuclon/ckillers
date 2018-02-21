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

@WebServlet("/connexion")
public class ConnexionServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String identifiantUtilisateur = (String) req.getSession().getAttribute("utilisateur");
        if(identifiantUtilisateur == null || "".equals(identifiantUtilisateur)) {
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            context.setVariable("inscrit", new Inscrit("Visiteur"));

            templateEngine.process("connexion", context, resp.getWriter());
        }else{
            resp.sendRedirect("profil");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        String motDePasse = req.getParameter("mdp");
        try{
            String mdp = Service.getInstance().getConnexion(mail);
            if(mdp.length()!=0 && mdp.equals(motDePasse)){
                req.getSession().setAttribute("utilisateur", mail);
            }
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Erreur lors de la connexion", e);
        }
        resp.sendRedirect("connexion");
    }
}
