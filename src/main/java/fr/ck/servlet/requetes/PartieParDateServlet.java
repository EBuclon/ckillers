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

@WebServlet("/partieParDate")
public class PartieParDateServlet extends GenericServlet {
    /**
     * Renvoie les données sur les parties du jour selectionné dans le calendrier
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        List<Partie> parties = Service.getInstance().listePartiesParJour(req.getParameter("Date"));
        resp.setCharacterEncoding("UTF8");
        PrintWriter out = resp.getWriter();
        Gson jsonResponse = new Gson();
        out.append(jsonResponse.toJson(parties));
    }
}
