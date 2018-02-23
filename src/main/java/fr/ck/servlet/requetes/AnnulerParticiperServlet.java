package fr.ck.servlet.requetes;

import fr.ck.Service.Service;
import fr.ck.servlet.GenericServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/annulerParticiper")
public class AnnulerParticiperServlet extends GenericServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer idPartie = Integer.parseInt(req.getParameter("idPartie"));
        Integer idInscrit = Service.getInstance().getIdParMail((String) req.getSession().getAttribute("utilisateur"));

        Service.getInstance().annulerParticiper(idPartie,idInscrit);
    }
}
