package fr.ck.servlet.templates;

import fr.ck.Service.Service;
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
import java.util.List;

@WebServlet("/detailPartie")
public class DetailPartieServlet extends GenericServlet {
    /**
     * Lance la page de detail d'une partie avec les paramètres necessaires
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Integer idPartie = Integer.parseInt(req.getParameter("idPartie"));
        Partie partie = Service.getInstance().getPartie(idPartie);

        if(Service.getInstance().getInscritValideur(idPartie)==null){
            resp.sendRedirect("accueil");
            return;
        }

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        String mailUtilisateur = (String) req.getSession().getAttribute("utilisateur");
        String statutUtil = (String) req.getSession().getAttribute("statut");

        List<Inscrit> participants = Service.getInstance().listeParticipants(idPartie);
        Boolean joueur = false;
        for(int i=0;i<participants.size();i++){
            if(participants.get(i).getMail().equals(mailUtilisateur)){
                joueur=true;
            }
        }
        context.setVariable("joueur",joueur);

        if(mailUtilisateur == null || "".equals(mailUtilisateur)) {
            context.setVariable("inscrit", new Inscrit("Visiteur"));
        }else{
            context.setVariable("inscrit", new Inscrit(mailUtilisateur,statutUtil));
            context.setVariable("idInscrit", Service.getInstance().getIdParMail(mailUtilisateur));
        }
        context.setVariable("partie", partie);
        context.setVariable("participants",participants);
        context.setVariable("nombreParticipants",participants.size());

        templateEngine.process("detailPartie", context, resp.getWriter());
    }
}
