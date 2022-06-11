package com.gsnotes;

import javax.transaction.Transactional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gsnotes.bo.Compte;
import com.gsnotes.dao.ICompteDao;

@SpringBootApplication
public class ApplicationLauncher  implements ApplicationContextAware {
	
	private static ApplicationContext context;
	private static ICompteDao compteDAO;
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationLauncher.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationLauncher.class, args);
		
		ApplicationLauncher.updatePasswords();
	}
	
	@Transactional
	private static void updatePasswords() {
		BCryptPasswordEncoder passwordEncoder = ApplicationLauncher.getBean(BCryptPasswordEncoder.class);
		ICompteDao compteDAO = ApplicationLauncher.getBean(ICompteDao.class);
		
		String password = passwordEncoder.encode("admin");
		
		Compte admin = compteDAO.getCompteByLogin("admin");
		Compte prof = compteDAO.getCompteByLogin("prof");
		Compte student = compteDAO.getCompteByLogin("student");
		Compte cadreadmin = compteDAO.getCompteByLogin("cadre_admin");
		Compte biblio = compteDAO.getCompteByLogin("biblio");
		
		admin.setPassword(password);
		prof.setPassword(password);
		student.setPassword(password);
		cadreadmin.setPassword(password);
		biblio.setPassword(password);
		
		compteDAO.save(admin);
		compteDAO.save(prof);
		compteDAO.save(student);
		compteDAO.save(cadreadmin);
		compteDAO.save(biblio);
	}
	
	public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> bean) {
        return getContext().getBean(bean);
    }
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	public static ICompteDao getCompteDAO() {
        return compteDAO;
    }
	
	public static void getCompteDAO(ICompteDao icompteDAO) {
        compteDAO = icompteDAO;
    }
}
