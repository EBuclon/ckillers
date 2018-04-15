package fr.ck.servlet.requetes;

import fr.ck.Service.Service;
import fr.ck.servlet.GenericServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifierStatut")
public class ModifierStatutServlet extends GenericServlet {
    /**
     * Methode pour modifier un statut
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Service.getInstance().modifierStatutInscrit(Integer.parseInt(req.getParameter("id")),req.getParameter("statut"));
    }
}
