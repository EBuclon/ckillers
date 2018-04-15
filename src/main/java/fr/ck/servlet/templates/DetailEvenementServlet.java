package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Inscrit;
import fr.ck.entite.Evenement;
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
import java.util.List;

@WebServlet("/detailEvenement")
@MultipartConfig
public class DetailEvenementServlet extends GenericServlet {
    /**
     * Lance la page de detail d'un evenement avec les paramètres necessaires
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Integer idEvenement = Integer.parseInt(req.getParameter("idEvenement"));
        Evenement evenement = Service.getInstance().getEvenement(idEvenement);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String identifiantUtilisateur = (String) req.getSession().getAttribute("utilisateur");
        String statutUtil = (String) req.getSession().getAttribute("statut");

        List<Inscrit> participants = Service.getInstance().listeAllants(idEvenement);
        Boolean joueur = false;
        for(int i=0;i<participants.size();i++){
            if(participants.get(i).getMail().equals(identifiantUtilisateur)){
                joueur=true;
            }
        }
        context.setVariable("joueur",joueur);

        if(identifiantUtilisateur == null || "".equals(identifiantUtilisateur)) {
            context.setVariable("inscrit", new Inscrit("Visiteur"));
        }else{
            context.setVariable("inscrit", new Inscrit(identifiantUtilisateur,statutUtil));
        }
        context.setVariable("evenement", evenement);
        context.setVariable("participants",participants);

        templateEngine.process("detailEvenement", context, resp.getWriter());
    }

    /**
     * Permet aux modérateurs de changer l'image de l'évènement
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idEvenement = Integer.parseInt(req.getParameter("idEvenement"));

        Part image = req.getPart("image");

        try {
            Service.getInstance().ajouterImageE(idEvenement, image);
            resp.sendRedirect("detailEvenement?idEvenement="+idEvenement);
        }catch (IllegalArgumentException e){
            resp.sendRedirect("accueil");
        }

    }
}
