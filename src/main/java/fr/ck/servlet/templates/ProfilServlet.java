package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.entite.Partie;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profil")
public class ProfilServlet extends GenericServlet {
    /**
     * Lance la page de profil avec les informations necessaires
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        String utilisateur = (String) req.getSession().getAttribute("utilisateur");
        Integer idInscrit = Service.getInstance().getIdParMail(utilisateur);

        if(utilisateur == null || utilisateur.equals("")){
            resp.sendRedirect("connexion");
        }else {
            context.setVariable("inscrit", Service.getInstance().getInscritParMail(utilisateur));
            List<Partie> parties = Service.getInstance().listePartieParJoueur(idInscrit);
            String format = "yyyy-MM-dd";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
            java.util.Date date = new java.util.Date();
            String dateJour = formater.format(date);
            List<Partie> partiesF = new ArrayList<>();
            List<Partie> partiesP = new ArrayList<>();
            for(Integer i=0;i<parties.size();i++){
                if(Integer.parseInt(parties.get(i).getCreneau().getDate().split("-")[0])<Integer.parseInt(dateJour.split("-")[0])){
                    partiesP.add(parties.get(i));
                }else{
                    if(Integer.parseInt(parties.get(i).getCreneau().getDate().split("-")[0])==Integer.parseInt(dateJour.split("-")[0]) &&
                            Integer.parseInt(parties.get(i).getCreneau().getDate().split("-")[1])<Integer.parseInt(dateJour.split("-")[1])){
                        partiesP.add(parties.get(i));
                    }else{
                        if(Integer.parseInt(parties.get(i).getCreneau().getDate().split("-")[0])==Integer.parseInt(dateJour.split("-")[0]) &&
                                Integer.parseInt(parties.get(i).getCreneau().getDate().split("-")[1])==Integer.parseInt(dateJour.split("-")[1]) &&
                                Integer.parseInt(parties.get(i).getCreneau().getDate().split("-")[2])<Integer.parseInt(dateJour.split("-")[2])){
                            partiesP.add(parties.get(i));
                        }else{
                            partiesF.add(parties.get(i));
                        }
                    }
                }
            }
            context.setVariable("partiesFuture",partiesF);
            context.setVariable("partiesPassee",partiesP);

            templateEngine.process("profil", context, resp.getWriter());
        }
    }
}
