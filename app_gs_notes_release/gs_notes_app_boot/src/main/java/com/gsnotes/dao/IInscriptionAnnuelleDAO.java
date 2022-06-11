package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.InscriptionAnnuelle;

@Repository
public interface IInscriptionAnnuelleDAO extends JpaRepository<InscriptionAnnuelle, Long> {

}
