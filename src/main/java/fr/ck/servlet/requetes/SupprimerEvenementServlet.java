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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idEvenement = Integer.parseInt(req.getParameter("idEvenement"));
        Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
        Service.getInstance().supprimerEvenement(idEvenement,idCreneau);
        resp.sendRedirect("/accueil");
        return;
    }

}


