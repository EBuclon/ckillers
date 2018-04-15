package fr.ck.servlet.requetes;

import fr.ck.Service.Service;
import fr.ck.servlet.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimerNouvelle")
public class SupprimerNouvelleServlet extends GenericServlet{
    /**
     * Permet de supprimer un créneau de la page d'accueil
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!req.getSession().getAttribute("statut").equals("admin") && !req.getSession().getAttribute("statut").equals("moderateur")){
            resp.sendRedirect("accueil");
        }
        Integer idNouvelle = Integer.parseInt(req.getParameter("idNouvelle"));
        Service.getInstance().supprimerNouvelle(idNouvelle);
        resp.sendRedirect("/accueil");
        return;
    }

}
