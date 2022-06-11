package com.gsnotes.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsnotes.bo.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Long> {

}
