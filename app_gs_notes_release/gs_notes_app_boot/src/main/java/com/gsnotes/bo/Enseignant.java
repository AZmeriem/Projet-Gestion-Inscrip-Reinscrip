package com.gsnotes.bo;



import javax.persistence.Entity;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * Represente un enseignant.
 * 
 * Un enseignant est un cas spéciale de l'Utilisateur
 * 
 * @author T. BOUDAA
 *
 */


@Entity
@Table(name = "enseignant")
@PrimaryKeyJoinColumn(name = "idEnseighant")
public class Enseignant extends Utilisateur {


	
	private String specialite;


	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}





}