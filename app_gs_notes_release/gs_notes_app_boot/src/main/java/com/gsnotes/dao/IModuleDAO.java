package com.gsnotes.dao;

import com.gsnotes.bo.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModuleDAO extends JpaRepository<Module, Long> {

}
