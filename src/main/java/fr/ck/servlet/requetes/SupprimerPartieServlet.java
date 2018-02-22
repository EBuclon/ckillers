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
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Integer idPartie = Integer.parseInt(req.getParameter("idPartie"));
            Integer idCreneau = Integer.parseInt(req.getParameter("idCreneau"));
            Service.getInstance().supprimerPartie(idPartie,idCreneau);
            resp.sendRedirect("/creneauPartie");

            TemplateEngine templateEngine = this.createTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("Partie",Service.getInstance().getPartie(idPartie));

            //templateEngine.process("supprimerCreneau", context, resp.getWriter());

        }

    }


