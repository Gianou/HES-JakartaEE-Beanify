package ch.hevs.managedbeans;

import javax.annotation.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.beanifyservice.Populate;

@ManagedBean
public class WelcomeBean
{
	private Populate populate;
	
    public void populate(){
		try {
    	InitialContext ctx = new InitialContext();
		populate = (Populate) ctx.lookup("java:global/Beanify-0.0.1-SNAPSHOT/PopulateBean!ch.hevs.beanifyservice.Populate");
		populate.populate();			
		}catch(NamingException e) {
			e.printStackTrace();
		}

    }
}


