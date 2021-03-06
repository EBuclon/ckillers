package fr.ck.servlet.requetes;

import fr.ck.Service.Service;
import fr.ck.servlet.GenericServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/imageP")
public class ImagePartenaireServlet extends GenericServlet {

    private Map<String, String> mimeTypes;

    /**
     * Dédoublement de code à mettre dans une classe générale image
     */
    @Override
    public void init() throws ServletException {
        mimeTypes = new HashMap<>();
        mimeTypes.put("jpg", "image/jpeg");
        mimeTypes.put("jpeg", "image/jpeg");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("gif", "image/gif");
    }

    /**
     * Methode pour voir l'image d'un partenaire de l'association
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer idPartenaire = Integer.parseInt(req.getParameter("idPartenaire"));
        Path cheminImage = Service.getInstance().getImagePartenaire(idPartenaire);

        String cheminFichierImage = cheminImage.getFileName().toString();
        String cheminFichierExtension = cheminFichierImage.substring(cheminFichierImage.lastIndexOf(".") + 1);
        String contentType = mimeTypes.get(cheminFichierExtension);

        resp.setContentType(contentType);
        Files.copy(cheminImage, resp.getOutputStream());
    }
}
