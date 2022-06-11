package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Niveau;

@Repository
public interface INiveauDAO extends JpaRepository<Niveau, Long> {
	
	Niveau getByIdNiveau(Long idNiveau);
}
