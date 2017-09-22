package services;

import java.util.List;

import entite.Livre;

public interface LivreService {
	void save(Livre Livre);

	void update(Livre livre);

	void delete(Livre livre);

	List<Livre> findAll();

	Livre findById(Integer id_Livre);
}
