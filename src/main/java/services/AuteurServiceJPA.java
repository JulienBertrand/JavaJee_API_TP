package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import config.ConnectionEm;
import entite.Auteur;

public class AuteurServiceJPA implements AuteurService{

	public void save(Auteur auteur) {
		EntityManager em = ConnectionEm.getInstance();
		em.getTransaction().begin();
		em.persist(auteur);
		em.getTransaction().commit();

	}

	public void update(Auteur auteur) {
		EntityManager em = ConnectionEm.getInstance();
		Auteur auteurUpdate = findById(auteur.getId_Auteur());
		if (auteurUpdate != null) {
			auteurUpdate.setNom(auteur.getNom());
			auteurUpdate.setPrenom(auteur.getPrenom());
			auteurUpdate.setLangue(auteur.getLangue());
			em.persist(auteurUpdate);
		}
		else {
			save(auteur);
		}
	}

	public void delete(Auteur auteur) {
		EntityManager em = ConnectionEm.getInstance();
		//Auteur auteurDelete = em.find(Auteur.class, auteur.getId_Auteur());
		em.getTransaction().begin();
		em.remove(auteur);
		em.getTransaction().commit();

	}

	public List<Auteur> findAll() {
		EntityManager em = ConnectionEm.getInstance();
		TypedQuery<Auteur> query = em.createQuery("from Auteur", Auteur.class);
		return query.getResultList();
	}

	 public Auteur findById(Integer id_Auteur) {
		 EntityManager em = ConnectionEm.getInstance();
		Auteur auteur = em.find(Auteur.class, id_Auteur);
		return auteur;
	}

}
