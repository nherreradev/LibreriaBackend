package com.librosV2.Xrepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSession {
	
	//aca porque trae una session factory? - si se hizo @Bean de LocalSessionFactory.
			@Autowired
			private SessionFactory sessionFactory;
			
			protected Session obtenerSession(){
				
				return sessionFactory.getCurrentSession();
				
			}

}
