package fr.ck.servlet.requetes;

import com.google.gson.Gson;
import fr.ck.Service.Service;
import fr.ck.entite.Partie;
import fr.ck.servlet.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/dateAvecPartie")
public class DateAvecPartieServlet extends GenericServlet {
    /**
     * Methode pour trouver les jours disposant d'une partie dans un mois
     * Utilisée dans le calendrier.js
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        List<Integer> jours = Service.getInstance().listeDateAvecPartie(req.getParameter("Mois"),req.getParameter("annee"));
        resp.setCharacterEncoding("UTF8");
        PrintWriter out = resp.getWriter();
        Gson jsonResponse = new Gson();
        out.append(jsonResponse.toJson(jours));
    }
}
