package fr.ck.servlet.templates;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deconnexion")
public class DeconnexionServlet extends HttpServlet {
    /**
     * Retire loes attributs de session et renvoie a l'accueil
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("utilisateur");
        req.getSession().removeAttribute("statut");
        resp.sendRedirect("accueil");
    }
}
