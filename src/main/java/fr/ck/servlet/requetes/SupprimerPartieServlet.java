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

    @WebServlet("/supprimerPartie")
    public class SupprimerPartieServlet extends GenericServlet {
        /**
         * Permet de supprimer une partie depuis son detail
         * @param req
         * @param resp
         * @throws IOException
         */
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            if(!req.getSession().getAttribute("statut").equals("admin") && !req.getSession().getAttribute("statut").equals("moderateur")){
                resp.sendRedirect("accueil");
            }
            Integer idPartie = Integer.parseInt(req.getParameter("idPartie"));
            Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
            Service.getInstance().supprimerPartie(idPartie,idCreneau);
            resp.sendRedirect("/creneauPartie");
            return;
        }

    }


