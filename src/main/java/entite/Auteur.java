package entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Auteur {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id_Auteur;
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="langue")
	private String langue;
	
	//@Column(name="Liste de livres")
	@OneToMany(mappedBy = "auteur")
	private List<Livre> listeLivre;
	
	
	
	/**
	 * 
	 */
	public Auteur() {
	}
	public Auteur(String nom, String prenom, String langue) {
		this.nom = nom;
		this.prenom = prenom;
		this.langue = langue;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}
	/**
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	/**
	 * @return the listeLivre
	 */
	public List<Livre> getListeLivre() {
		return listeLivre;
	}
	/**
	 * @param listeLivre the listeLivre to set
	 */
	public void setListeLivre(List<Livre> listeLivre) {
		this.listeLivre = listeLivre;
	}
	/**
	 * @return the id_Auteur
	 */
	public int getId_Auteur() {
		return id_Auteur;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Auteur [id_Auteur=" + id_Auteur + ", nom=" + nom + ", prenom=" + prenom + ", langue=" + langue + "]";
	}

}
