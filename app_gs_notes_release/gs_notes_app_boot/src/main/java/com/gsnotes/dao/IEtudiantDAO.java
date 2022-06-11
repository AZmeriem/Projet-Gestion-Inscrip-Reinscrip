package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.Etudiant;

@Repository
public interface IEtudiantDAO extends JpaRepository<Etudiant, Long> {
	
	Etudiant getByIdEtudiant(Long idEtudiant);
}
