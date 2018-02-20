package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partie;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validerPartie")
public class ValiderPartieServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Integer idPartie = Integer.parseInt(req.getParameter("idPartie"));
        Partie partie = Service.getInstance().getPartie(idPartie);
        if (partie == null) {
            resp.sendRedirect("accueil");
            return;
        }

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        context.setVariable("partie", partie);

        templateEngine.process("validerPartie", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomScenario = req.getParameter("nomScenario");
        String nomJeu = req.getParameter("nomJeu");
        Integer nbMin = Integer.valueOf(req.getParameter("nbMin"));
        Integer nbMax = Integer.valueOf(req.getParameter("nbMax"));
        String deUtil = req.getParameter("deUtil");
        String typeSoiree = req.getParameter("typeSoiree");
        String genre = req.getParameter("genre");
        String type = req.getParameter("type");
        String ton = req.getParameter("ton");
        String inspiration = req.getParameter("inspiration");
        String niveauAttendu = req.getParameter("niveauAttendu");
        String presentation = req.getParameter("presentation");

        Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
        Creneau creneau = new Creneau(idCreneau); //Service.getInstance().getCreneau(idCreneau);
        Inscrit inscrit = new Inscrit(1);

        //Part image = req.getPart("image");

        Partie partie = new Partie(0, nomScenario, nomJeu, nbMin, nbMax, deUtil, typeSoiree, genre,
                type, ton, inspiration, niveauAttendu, presentation, creneau, inscrit);

        try {
            //Service.getInstance().validerPartie(partie);
            resp.sendRedirect("accueil");
        }catch (IllegalArgumentException e){
            resp.sendRedirect("accueil");
        }

    }
}