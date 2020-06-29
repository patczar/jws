package rest;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/ejb")
@Stateless
public class EBean implements Serializable {
	private static final long serialVersionUID = -2627946805995054930L;
	
	private AtomicInteger licznik = new AtomicInteger();
	
	public EBean() {
		System.out.println("EBean konstruktor");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("EBean @PostConstruct");
	}
	
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("EBean @PreDestroy");
	}
	
	@GET
	public String get() {
		return "Hello EJB. Licznik = " + licznik.incrementAndGet();
	}
	
}
