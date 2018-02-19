package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Creneau;
import fr.ck.entite.Partie;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajouterPartie")
public class AjouterPartieServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
        Creneau creneau = Service.getInstance().getCreneau(idCreneau);
        if (creneau == null) {
            resp.sendRedirect("accueil");
            return;
        }

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        context.setVariable("creneau", creneau);

        templateEngine.process("ajouterPartie", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomScenario = req.getParameter("nomScenario");
        String nomJeu = req.getParameter("nomJeu");
        Integer nbMin = Integer.parseInt(req.getParameter("nbMin"));
        Integer nbMax = Integer.parseInt(req.getParameter("nbMax"));
        String deUtil = req.getParameter("deUtil");
        String typeSoiree = req.getParameter("typeSoiree");
        String genre = req.getParameter("genre");
        String type = req.getParameter("type");
        String ton = req.getParameter("ton");
        String inspiration = req.getParameter("inspiration");
        String niveauAttendu = req.getParameter("niveauAttendu");
        String presentation = req.getParameter("presentation");

        Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
        Creneau creneau = new Creneau(idCreneau,"","","",0); //Service.getInstance().getCreneau(idCreneau);

        //Part image = req.getPart("image");

        Partie partie = new Partie(0, nomScenario, nomJeu, nbMin, nbMax, deUtil, typeSoiree, genre,
                type, ton, inspiration, niveauAttendu, presentation, creneau, 1,1);

        try {
            Service.getInstance().ajouterPartie(partie);
            resp.sendRedirect("detailPartie?id="+"idPartie");
        }catch (IllegalArgumentException e){
            resp.sendRedirect("ajouter");
        }

    }
}