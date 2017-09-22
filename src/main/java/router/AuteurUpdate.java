package router;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import entite.Auteur;

/**
 * Servlet implementation class AuteurUpdate
 */
public class AuteurUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuteurUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		JSONObject jsonObject = new JSONObject(body);

		Auteur nouvelAuteur = new Auteur(jsonObject.getString("nom"), jsonObject.getString("prenom"),
				jsonObject.getString("langue"));
		System.out.println(nouvelAuteur);
		
	}


}
