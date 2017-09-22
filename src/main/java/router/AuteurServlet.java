package router;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import entite.Auteur;
import services.AuteurService;
import services.AuteurServiceJPA;

@WebServlet("/Auteur/*")
public class AuteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuteurService service = null;

	/**
	 * Cette servlet permet de sélectionner un auteur par son ID,
	 * mais également d'ajouter un nouvel auteur, d'en modifier un 
	 * et enfin d'en supprimer un.
	 * 
	 * L'affichage de la liste des auteurs est dans une seconde servlet nommée "AuteurServletListe" et accessible à l'URL "/Auteurs
	 */
	public AuteurServlet() {
		service = new AuteurServiceJPA();
	}

	/**
	 * Methode GET permettant de récupérer un auteur par son ID
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Il manque l'ajout de la liste des livre pour un auteur.
		int id_Auteur = Integer.valueOf(request.getPathInfo().replaceAll("/", ""));

		Auteur auteur = service.findById(id_Auteur);
		if (auteur != null) {

			JSONObject auteurJson = new JSONObject();
			auteurJson.put("id", auteur.getId_Auteur());
			auteurJson.put("nom", auteur.getNom());
			auteurJson.put("prenom", auteur.getPrenom());
			auteurJson.put("langue", auteur.getLangue());

			response.setContentType("application/json");
			response.getWriter().print(auteurJson.toString());
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("{\"raison\":\"l'auteur demande n'existe pas, veuillez recommencer\"}");
		}

	}

	/**
	 * Methode POST pour ajouter un nouvel auteur
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//Les codes d'erreurs ont été fait pour la methode PUT, je n'ai pas eu le temps de le faire pour la methode POST
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		JSONObject jsonObject = new JSONObject(body);

		Auteur nouvelAuteur = new Auteur(jsonObject.getString("nom"), jsonObject.getString("prenom"),
				jsonObject.getString("langue"));
		System.out.println(nouvelAuteur);
		//
		service.save(nouvelAuteur);

	}
	/**
	 * Methode PUT pour modifier un auteur existant
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		int id_Auteur = Integer.valueOf(request.getPathInfo().replaceAll("/", ""));
		Auteur auteurUpdate = service.findById(id_Auteur);

		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		JSONObject jsonObject = new JSONObject(body);

		String nom = null;
		try {
			nom = jsonObject.getString("nom");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"Vous avez oublié rentrer le nom, veuillez recommencer\"}");
		}

		String prenom = null;

		try {
			prenom = jsonObject.getString("prenom");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"Vous avez oublié rentrer le prenom, veuillez recommencer\"}");
		}
		String langue = null;

		try {
			langue = jsonObject.getString("langue");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"Vous avez oublié rentrer la langue, veuillez recommencer\"}");
		}
		
		
		
		if (nom.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"Vous devez saisir un nom, veuillez recommencer\"}");
		}
		if (prenom.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"Vous devez saisir un prenom, veuillez recommencer\"}");
		}
		if (langue.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"Vous devez saisir une langue, veuillez recommencer\"}");
		}else {
		auteurUpdate.setNom(nom);
		auteurUpdate.setPrenom(prenom);
		auteurUpdate.setLangue(langue);

		service.update(auteurUpdate);

		System.out.println(auteurUpdate);
		}
	}
	/**
	 * Methode DELETE pour supprimer un auteur
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_Auteur = Integer.valueOf(request.getPathInfo().replaceAll("/", ""));

		Auteur auteur = service.findById(id_Auteur);
		if (auteur != null) {
			// JSONObject auteurJson = new JSONObject(auteur);
			System.out.println(auteur);
			service.delete(auteur);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append(
					"{\"raison\":\"l'auteur demande n'existe pas\"}");
		}
	}

}
