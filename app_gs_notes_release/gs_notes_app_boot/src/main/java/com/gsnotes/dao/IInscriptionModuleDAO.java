package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.InscriptionModule;

@Repository
public interface IInscriptionModuleDAO extends JpaRepository<InscriptionModule, Long> {

}
