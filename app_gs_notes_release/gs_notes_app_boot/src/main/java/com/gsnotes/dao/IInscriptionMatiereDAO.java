package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.InscriptionMatiere;

@Repository
public interface IInscriptionMatiereDAO extends JpaRepository<InscriptionMatiere, Long> {

}
