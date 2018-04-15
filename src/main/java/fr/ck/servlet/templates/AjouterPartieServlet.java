package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Creneau;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Partie;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/ajouterPartie")
@MultipartConfig
public class AjouterPartieServlet extends GenericServlet {
    /**
     * Lance la page d'ajout de partie avec les paramètres necessaires
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
        Creneau creneau = Service.getInstance().getCreneau(idCreneau);
        if (creneau == null) {
            resp.sendRedirect("accueil");
            return;
        }

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        String identifiantUtilisateur = (String) req.getSession().getAttribute("utilisateur");
        String statutUtil = (String) req.getSession().getAttribute("statut");
        if(identifiantUtilisateur == null || "".equals(identifiantUtilisateur)) {
            resp.sendRedirect("connexion");
            return;
        }
        context.setVariable("inscrit", new Inscrit(identifiantUtilisateur,statutUtil));
        context.setVariable("creneau", creneau);

        templateEngine.process("ajouterPartie", context, resp.getWriter());
    }

    /**
     * Lance la fonction d'ajout de partie avec les données rentrées dans le formulaire
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomScenario = req.getParameter("nomScenario");
        String nomJeu = req.getParameter("nomJeu");
        Integer nbMin = Integer.valueOf(req.getParameter("nbMin"));
        Integer nbMax = Integer.valueOf(req.getParameter("nbMax"));
        String desUtil = req.getParameter("desUtil");
        String typeSoiree = req.getParameter("typeSoiree");
        String genre = req.getParameter("genre");
        String type = req.getParameter("type");
        String ton = req.getParameter("ton");
        String inspiration = req.getParameter("inspiration");
        String niveauAttendu = req.getParameter("niveauAttendu");
        String presentation = req.getParameter("presentation");

        if(nomScenario.length()>25 || nomJeu.length()>25 || desUtil.length()>25 || genre.length()>25 || type.length()>25 || ton.length()>25 || inspiration.length()>50 || niveauAttendu.length()>30 || presentation.length()>400){
            resp.sendRedirect("ajouterPartie?idCreneau="+req.getParameter("idCreneau"));
        }else{
            Part image = req.getPart("image");

            Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
            Creneau creneau = new Creneau(idCreneau);
            Inscrit inscrit = Service.getInstance().getInscritParMail((String) req.getSession().getAttribute("utilisateur"));

            Partie partie = new Partie(0, nomScenario, nomJeu, nbMin, nbMax, desUtil, typeSoiree, genre,
                    type, ton, inspiration, niveauAttendu, presentation, creneau, inscrit);

            try {
                Service.getInstance().ajouterPartie(partie,image);
                resp.sendRedirect("accueil");
            }catch (IllegalArgumentException e){
                resp.sendRedirect("creneauPartie");
            }
        }


    }
}