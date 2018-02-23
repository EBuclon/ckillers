package fr.ck.servlet.requetes;

import fr.ck.entite.Creneau;
import fr.ck.servlet.GenericServlet;
import fr.ck.Service.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimerCreneau")
public class SupprimerCreneauServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
        Service.getInstance().supprimerCreneau(idCreneau);
        resp.sendRedirect("/creneauPartie");
        return;
    }

}
