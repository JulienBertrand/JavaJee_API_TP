package entite;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Livre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Livre;
	@Column(name = "titre")
	private String titre;
	@Column(name = "date_publication")
	private LocalDate dateDePublication;
	@Column(name = "description")
	private String description;
	@Column(name = "categorie")
	private String categorie;
	
	//@Column(name = "Auteur")
	@OneToOne
	@JoinColumn(name="id_Aut")
	private Auteur auteur;
	@Column(name = "nombre_explemplaires")
	private int nombreEx;
	@Column(name = "nombre_explemplaires_disponibles")
	private int nombreExDispo;
	
	
	/**
	 * 
	 */
	public Livre() {
	}
	/**
	 * @param titre
	 * @param dateDePublication
	 * @param description
	 * @param categorie
	 * @param auteur
	 * @param nombreEx
	 * @param nombreExDispo
	 */
	public Livre(String titre, LocalDate dateDePublication, String description, String categorie, Auteur auteur,
			int nombreEx, int nombreExDispo) {
		this.titre = titre;
		this.dateDePublication = dateDePublication;
		this.description = description;
		this.categorie = categorie;
		this.auteur = auteur;
		this.nombreEx = nombreEx;
		this.nombreExDispo = nombreExDispo;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre
	 *            the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the dateDePublication
	 */
	public LocalDate getDateDePublication() {
		return dateDePublication;
	}

	/**
	 * @param dateDePublication
	 *            the dateDePublication to set
	 */
	public void setDateDePublication(LocalDate dateDePublication) {
		this.dateDePublication = dateDePublication;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the auteur
	 */
	public Auteur getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur
	 *            the auteur to set
	 */
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	/**
	 * @return the nombreEx
	 */
	public int getNombreEx() {
		return nombreEx;
	}

	/**
	 * @param nombreEx
	 *            the nombreEx to set
	 */
	public void setNombreEx(int nombreEx) {
		this.nombreEx = nombreEx;
	}

	/**
	 * @return the nombreExDispo
	 */
	public int getNombreExDispo() {
		return nombreExDispo;
	}

	/**
	 * @param nombreExDispo
	 *            the nombreExDispo to set
	 */
	public void setNombreExDispo(int nombreExDispo) {
		this.nombreExDispo = nombreExDispo;
	}
	/**
	 * @return the id_Livre
	 */
	public int getId_Livre() {
		return id_Livre;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Livre [id_Livre=" + id_Livre + ", titre=" + titre + ", dateDePublication=" + dateDePublication
				+ ", description=" + description + ", categorie=" + categorie + ", auteur=" + auteur + ", nombreEx="
				+ nombreEx + ", nombreExDispo=" + nombreExDispo + "]";
	}

}
