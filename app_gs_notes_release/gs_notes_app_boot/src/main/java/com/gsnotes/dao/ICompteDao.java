package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.Compte;

@Repository
public interface ICompteDao extends JpaRepository<Compte, Long> {
	public Compte getCompteByLogin(String username);

}
