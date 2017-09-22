package router;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import config.ConnectionEm;
import entite.Auteur;
import services.AuteurService;

/**
 * Servlet implementation class RouterBibliotheque
 */
@WebServlet(urlPatterns = { "/Auteur" })
public class AuteurServletListe extends HttpServlet {
	public static EntityManager em = ConnectionEm.getInstance();
	private static final long serialVersionUID = 1L;
	AuteurService service=null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuteurServletListe() {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		JSONArray jsonArray = new JSONArray();

		TypedQuery<Auteur> query = em.createQuery("from Auteur", Auteur.class);
		List<Auteur> listeAuteurs = new ArrayList<Auteur>(query.getResultList());
		for (int i = 0; i < listeAuteurs.size(); i++) {
			jsonArray.put(listeAuteurs.get(i));

		}
		System.out.println(jsonArray);
		JSONObject objetJSon = new JSONObject();
		objetJSon.put("listeAuteurs", jsonArray);
		response.setContentType("application/json");
		response.getWriter().print(objetJSon.toString());
		System.out.println(objetJSon.toString());

		// em.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
