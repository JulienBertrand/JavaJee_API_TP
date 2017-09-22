package services;

import java.util.List;

import entite.Auteur;

public interface AuteurService {
	void save(Auteur Auteur);

	void update(Auteur auteur);

	void delete(Auteur auteur);

	List<Auteur> findAll();

	Auteur findById(Integer id_Auteur);
}
