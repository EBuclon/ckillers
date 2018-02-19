package fr.ck.servlet.requetes;

import com.google.gson.Gson;
import fr.ck.Service.Service;
import fr.ck.entite.Creneau;
import fr.ck.servlet.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/liste")
public class ListeCreneauLibresServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Creneau> Creneaux = Service.getInstance().listCreneauxLibres();
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        Gson jsonResponse = new Gson();
        out.append(jsonResponse.toJson(Creneaux));
    }
}
