package fr.ck.servlet.requetes;

import fr.ck.Service.Service;
import fr.ck.servlet.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimerEvenement")
public class SupprimerEvenementServlet extends GenericServlet {
    /**
     * Permet de supprimer un évènement depuis son detail
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!req.getSession().getAttribute("statut").equals("admin") && !req.getSession().getAttribute("statut").equals("moderateur")){
            resp.sendRedirect("accueil");
        }
        Integer idEvenement = Integer.parseInt(req.getParameter("idEvenement"));
        Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
        Service.getInstance().supprimerEvenement(idEvenement,idCreneau);
        resp.sendRedirect("/accueil");
        return;
    }

}


