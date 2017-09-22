package router;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entite.Auteur;
import services.AuteurService;
import services.AuteurServiceJPA;

@WebServlet("/AuteurDelete/*")
public class AuteurDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuteurService service = null;
   
    public AuteurDelete() {
    	service = new AuteurServiceJPA();
    }
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_Auteur = Integer.valueOf(request.getPathInfo().replaceAll("/", ""));

		Auteur auteur = service.findById(id_Auteur);
		if (auteur != null) {
			//JSONObject auteurJson = new JSONObject(auteur);
		System.out.println(auteur);
			service.delete(auteur);
		}else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
//			response.setContentType("application/json");
//			response.getWriter().print(auteurJson.toString());
	}

}
