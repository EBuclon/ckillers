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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idNouvelle = Integer.parseInt(req.getParameter("idNouvelle"));
        Service.getInstance().supprimerNouvelle(idNouvelle);
        resp.sendRedirect("/accueil");
        return;
    }

}
