package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.Utilisateur;

@Repository
public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long> {

	public Utilisateur getUtilisateurByCin(String cin);

}
