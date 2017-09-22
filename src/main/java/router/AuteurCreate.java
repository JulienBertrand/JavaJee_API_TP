package router;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import entite.Auteur;
import services.AuteurService;
import services.AuteurServiceJPA;

/**
 * Servlet implementation class AuteurCreate
 */
@WebServlet("/AuteurCreate/*")
public class AuteurCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuteurService service = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuteurCreate() {
		service = new AuteurServiceJPA();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_Auteur = Integer.valueOf(request.getPathInfo().replaceAll("/", ""));

		Auteur auteur = service.findById(id_Auteur);
		if (auteur != null) {
			JSONObject auteurJson = new JSONObject(auteur);
			response.setContentType("application/json");
			response.getWriter().print(auteurJson.toString());
		}else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		JSONObject jsonObject = new JSONObject(body);

		Auteur nouvelAuteur = new Auteur(jsonObject.getString("nom"), jsonObject.getString("prenom"),
				jsonObject.getString("langue"));
		System.out.println(nouvelAuteur);
		//
		service.save(nouvelAuteur);

	}
}
