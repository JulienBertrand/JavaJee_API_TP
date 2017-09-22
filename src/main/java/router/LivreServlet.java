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
import entite.Livre;
import services.AuteurService;
import services.AuteurServiceJPA;
import services.LivreService;
import services.LivreServiceJPA;

@WebServlet("/Livre/*")
public class LivreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LivreService service = null;
	private AuteurService serviceAuteur = null;

	/**
	 * Cette servlet permet de sélectionner un livre par son ID, mais également
	 * d'ajouter un nouveau livre, d'en modifier un et enfin d'en supprimer un.
	 * 
	 * L'affichage de la liste des livres est dans une seconde servlet nommée
	 * "LivreServletListe" et accessible à l'URL "/Livres
	 */
	public LivreServlet() {
		service = new LivreServiceJPA();
		serviceAuteur = new AuteurServiceJPA();
	}

	/**
	 * Methode GET permettant de récupérer un livre par son ID
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_Livre = Integer.valueOf(request.getPathInfo().replaceAll("/", ""));

		Livre livre = service.findById(id_Livre);
		if (livre != null) {
			response.setContentType("application/json");
			JSONObject livreJson = new JSONObject();
			livreJson.put("id_Lvre", livre.getId_Livre());
			livreJson.put("titre", livre.getTitre());
			livreJson.put("datePublication", livre.getDateDePublication());
			livreJson.put("description", livre.getDescription());
			livreJson.put("categorie", livre.getCategorie());
			livreJson.put("exemplaires", livre.getNombreEx());
			livreJson.put("exemplairesDispo", livre.getNombreExDispo());
			Auteur auteur = livre.getAuteur();
			JSONObject auteurJson = new JSONObject();
			auteurJson.put("id", auteur.getId_Auteur());
			auteurJson.put("nom", auteur.getNom());
			auteurJson.put("prenom", auteur.getPrenom());
			auteurJson.put("langue", auteur.getLangue());
			livreJson.put("auteur", auteurJson);
			response.getWriter().append(livreJson.toString());
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("{\"raison\" : \"le livre demande n'existe pas, veuillez recommencer\"}");
		}

	}

	/**
	 * Methode POST permettant d'ajouter un nouvel livre par son ID
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JSONObject jsonObject = new JSONObject(body);
		Auteur auteur = serviceAuteur.findById(Integer.valueOf(jsonObject.getInt("id_Aut")));
		Livre nouveauLivre = new Livre(jsonObject.getString("titre"), jsonObject.getString("date_publication"),
				jsonObject.getString("description"), jsonObject.getString("categorie"), auteur,
				jsonObject.getInt("nombre_exemplaires"), jsonObject.getInt("nombre_exemplaires_disponibles"));
		System.out.println(nouveauLivre);
		service.save(nouveauLivre);
	}

	/**
	 * Methode PUT permettant de modifier un livre existant
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		int id_Livre = Integer.valueOf(request.getPathInfo().replaceAll("/", ""));
		Livre livreUpdate = service.findById(id_Livre);
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JSONObject jsonObject = new JSONObject(body);
		String titre = null;
		try {
			titre = jsonObject.getString("titre");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append(
					"{\"raison\":\"probleme lors de la recuperation du titre du livre, veuillez recommencer\"}");
		}

		Auteur auteur = null;
		try {
			auteur = serviceAuteur.findById(Integer.valueOf(jsonObject.getInt("id_Aut")));
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append(
					"{\"raison\":\"probleme lors de la recuperation de l'auteur du livre, veuillez recommencer\"}");
		}

		String dateDePublication = null;
		try {
			dateDePublication = jsonObject.getString("date_publication");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.append("{\"raison\":\"probleme lors de la recuperation de la date de publication du livre\"}");
		}

		String description = null;
		try {
			description = jsonObject.getString("description");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"probleme lors de la recuperation de la description du livre\"}");
		}

		String categorie = null;
		try {
			categorie = jsonObject.getString("categorie");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"probleme lors de la recuperation de la categorie du livre\"}");
		}

		int nombreEx = 0;
		try {
			nombreEx = jsonObject.getInt("nombre_exemplaires");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.append("{\"raison\":\"probleme lors de la recuperation du nombre d'exemplaires du livre\"}");
		}

		int nombreExDispo = 0;
		try {
			nombreExDispo = jsonObject.getInt("nombre_exemplaires_disponibles");
		} catch (JSONException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append(
					"{\"raison\":\"probleme lors de la recuperation du nombre d'exemplaires disponibles du livre\"}");

		}
		if (titre.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"Vous devez saisir un nouveau titre, veuillez recommencer\"}");
		}
		if (auteur.equals(null)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append("{\"raison\":\"Vous devez saisir un nouvel auteur, veuillez recommencer\"}");
		}
		if (dateDePublication.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append(
					"{\"raison\":\"Vous devez saisir une nouvelle date de publication, veuillez recommencer\"}");
			if (description.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter()
						.append("{\"raison\":\"Vous devez saisir une nouvelle description, veuillez recommencer\"}");
			}
			if (categorie.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter()
						.append("{\"raison\":\"Vous devez saisir une nouvelle categorie, veuillez recommencer\"}");
			}
			if (nombreEx == 0) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().append(
						"{\"raison\":\"Vous devez saisir un nouveau nombre d'exemplaire, veuillez recommencer\"}");
			}
			if (nombreExDispo == 0) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().append(
						"{\"raison\":\"Vous devez saisir un nouveau nombre d'exemplaire disponibles, veuillez recommencer\"}");
			}

		} else {
			livreUpdate.setTitre(titre);
			livreUpdate.setAuteur(auteur);
			livreUpdate.setDateDePublication(dateDePublication);
			livreUpdate.setDescription(description);
			livreUpdate.setCategorie(categorie);
			livreUpdate.setNombreEx(nombreEx);
			livreUpdate.setNombreExDispo(nombreExDispo);

			service.update(livreUpdate);
			System.out.println(livreUpdate);
		}
	}

	/**
	 * Methode DELETE permettant de supprimer un livre par son ID
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_Livre = Integer.valueOf(request.getPathInfo().replaceAll("/", ""));

		Livre livre = service.findById(id_Livre);
		if (livre != null) {
			// JSONObject auteurJson = new JSONObject(auteur);
			System.out.println(livre);
			service.delete(livre);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("{\"raison\":\"le livre demande n'existe pas\"}");
		}
	}
}
