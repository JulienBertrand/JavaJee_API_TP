package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.annotation.WebServlet;

import config.ConnectionEm;
import entite.Livre;

public class LivreServiceJPA implements LivreService{
	//EntityManager em=ConnectionEm.getInstance();
	
	
	public void save(Livre livre) {
		EntityManager em = ConnectionEm.getInstance();
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(livre);
		em.getTransaction().commit();
	}

	public void update(Livre livre) {
		EntityManager em = ConnectionEm.getInstance();
		// TODO Auto-generated method stub
	Livre livreUpdate=findById(livre.getId_Livre());
	if(livreUpdate != null) {
		livreUpdate.setTitre(livre.getTitre());
		livreUpdate.setDateDePublication(livre.getDateDePublication());
		livreUpdate.setDescription(livre.getDescription());
		livreUpdate.setCategorie(livre.getCategorie());
		livreUpdate.setAuteur(livre.getAuteur());
		livreUpdate.setNombreEx(livre.getNombreEx());
		livreUpdate.setNombreExDispo(livre.getNombreExDispo());
		em.persist(livreUpdate);
	}
	else {
		save(livre);
	}

	}

	public void delete(Livre livre) {
		EntityManager em = ConnectionEm.getInstance();
		//Livre livre = em.find(Livre.class, id_Livre);
		em.getTransaction().begin();		
		em.remove(livre);
		em.getTransaction().commit();
	}

	public List<Livre> findAll() {
		EntityManager em = ConnectionEm.getInstance();
		TypedQuery<Livre> query = em.createQuery("from Livre", Livre.class);
		return query.getResultList();
		
	}

	public Livre findById(Integer id_Livre) {
		EntityManager em = ConnectionEm.getInstance();
		Livre livre=em.find(Livre.class, id_Livre);
		return livre;
		
	}

	
	

}
