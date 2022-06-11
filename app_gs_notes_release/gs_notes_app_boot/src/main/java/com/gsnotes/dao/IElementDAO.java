package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.Element;

@Repository
public interface IElementDAO extends JpaRepository<Element, Long> {

}
