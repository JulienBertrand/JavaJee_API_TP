package config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
public class ConnectionEm {
	public static EntityManager em;
	private ConnectionEm() {
		//Création d'un singleton de l'entityManager pour simplifier et n'en appeler qu'un.
		
		em = Persistence.createEntityManagerFactory("biblio").createEntityManager();
	}
	public static EntityManager getInstance() {
		if(em == null) new ConnectionEm();
		return em;
	}
}

	
