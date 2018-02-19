package fr.ck.servlet.requetes;

import com.google.gson.Gson;
import fr.ck.Service.Service;
import fr.ck.entite.Creneau;
import fr.ck.servlet.GenericServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajouterCreneau")
public class AjouterCreneauServlet extends GenericServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lieu = req.getParameter("Lieu");
        String date = req.getParameter("date");
        String heure = req.getParameter("heure");

        Creneau creneau = new Creneau(0, date, heure, lieu,1);
        Service.getInstance().ajouterCreneau(creneau);
        /*Gson parserJson = new Gson();
        String creneauJson = parserJson.toJson(creneau);

        PrintWriter writer = resp.getWriter();
        writer.write(creneauJson);*/
    }
}
