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
import entite.Livre;
import services.LivreService;

/**
 * Cette servlet permet d'afficher la liste des livres
 * 
 * La sélection d'un livre par son ID, l'ajout d'un nouveau livre, sa modification et sa suppression se trouve dans une seconde servlet nommée
 * "LivreServlet" et accessible à l'URL "/Livre.
 */
@WebServlet(urlPatterns = { "/Livre" })
public class LivreServletListe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static EntityManager em = ConnectionEm.getInstance();
	LivreService service=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivreServletListe() {
     
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		
		TypedQuery<Livre> query = em.createQuery("from Livre", Livre.class);
		List<Livre> listeAuteurs = new ArrayList<Livre>(query.getResultList());
		for (int i = 0; i < listeAuteurs.size(); i++) {
			jsonArray.put(listeAuteurs.get(i));
	}
		System.out.println(jsonArray);
		JSONObject objetJSon = new JSONObject();
		objetJSon.put("listeLivres", jsonArray);
		response.setContentType("application/json");
		response.getWriter().print(objetJSon.toString());
		System.out.println(objetJSon.toString());
	}
}
