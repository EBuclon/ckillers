package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partenaire;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/partenaires")
public class PartenairesServlet extends GenericServlet {

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
        context.setVariable("partenaires", Service.getInstance().listPartenaires());
        templateEngine.process("partenaires", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nomPartenaire = req.getParameter("nomPartenaire");
        String descriptionPartenaire = req.getParameter("descriptionPartenaire");
        String identifiantUtilisateur = (String) req.getSession().getAttribute("utilisateur");

        System.out.println(nomPartenaire+descriptionPartenaire);

        Partenaire partenaire = new Partenaire(nomPartenaire,descriptionPartenaire,new Inscrit(Service.getInstance().getIdParMail(identifiantUtilisateur)));

        if (nomPartenaire.length() <= 25 && descriptionPartenaire.length() <= 400) {
            Service.getInstance().ajouterPartenaire(partenaire);
        }
        resp.sendRedirect("partenaires");
        return;
    }

}
