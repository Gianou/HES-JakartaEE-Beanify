package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.beanifyservice.Populate;

public class WelcomeBean
{
	private Populate populate;
	
    public void populate(){
		try {
    	InitialContext ctx = new InitialContext();
		populate = (Populate) ctx.lookup("java:global/Beanify-0.0.1-SNAPSHOT/PopulateBean!ch.hevs.beanifyservice.Populate");
		
		populate.populate();

		System.out.println("Database populated");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}

    }
}


