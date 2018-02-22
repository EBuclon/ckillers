package fr.ck.servlet.templates;

import fr.ck.Service.Service;
import fr.ck.servlet.GenericServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profil")
public class ProfilServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        String utilisateur = (String) req.getSession().getAttribute("utilisateur");

        if(utilisateur == null || utilisateur.equals("")){
            resp.sendRedirect("connexion");
        }else {
            context.setVariable("inscrit", Service.getInstance().getInscritParMail(utilisateur));

            templateEngine.process("profil", context, resp.getWriter());
        }
    }
}
