package css.training.hibernate.domain;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;

/**
 * Explains many-to-many with cascade and inverse
 *
 */
public class Example02 {
	private static final SessionFactory factory = sessionFactory();

	@AfterClass
	public static void destroy() {
		if (factory != null) {
			factory.close();
		}
	}

	/**
	 * Many-to-Many Association with Join Table , no cascade no inverse. SQLs
	 * fired in following sequence. person and transport are mapped in join
	 * table person_transport
	 * 
	 * <pre>
	 * insert into transport (transport_type) values (?) 
	 * insert into transport  (transport_type) values (?) 
	 * insert into person (first_name, last_name)  values (?, ?) 
	 * insert into person_transport (person_id, transport_id)  values (?, ?) 
	 * insert into person_transport (person_id, transport_id)  values (?, ?)
	 * </pre>
	 */
	@Test
	public void testNoCascadeNoInverse() {

		Session ses = factory.openSession();
		ses.beginTransaction();

		Person person = new Person();
		person.setFirstName("nobitha");
		person.setLastName("nobi");
		Transport t1 = new Transport();
		t1.setType("Flying Machine");
		person.setTransports(new HashSet<Transport>());
		person.getTransports().add(t1);
		Transport t2 = new Transport();
		t2.setType("Time Machine");
		person.getTransports().add(t2);
		ses.persist(t1);
		ses.persist(t2);

		ses.persist(person);

		ses.getTransaction().commit();
		ses.close();
	}

	// Many-to-Many Association with Join Table , no cascade , inverse =true
	/**
	 * Add inverse="true" to <set name="transports"> tag in Person.hbm.xl <br/>
	 * person and transport are NOT mapped in join table person_transport
	 * 
	 * <pre>
	 * insert into transport (transport_type) values (?)
	 * insert into transport (transport_type) values (?)
	 * insert into person (first_name, last_name) values (?, ?)
	 * 
	 * <pre>
	 */
	@Test
	public void testNoCascadeInverseTrue() {
		Session ses = factory.openSession();
		ses.beginTransaction();

		Person person = new Person();
		person.setFirstName("nobitha");
		person.setLastName("nobi");
		Transport t1 = new Transport();
		t1.setType("Flying Machine");
		person.setTransports(new HashSet<Transport>());
		person.getTransports().add(t1);
		Transport t2 = new Transport();
		t2.setType("Time Machine");
		person.getTransports().add(t2);
		ses.persist(t1);
		ses.persist(t2);

		ses.persist(person);

		ses.getTransaction().commit();
		ses.close();
	}

	// Many-to-Many Association with Join Table , cascade=all , inverse =false
	/**
	 * Add inverse="false" cascade=all to <set name="transports"> tag in
	 * Person.hbm.xl<br/>
	 * you only persist person. transport saves via cascade, join table updates
	 * because of inverse=false
	 * 
	 * <pre>
	 *  insert into person (first_name, last_name) values (?, ?)
	 *  insert into transport (transport_type) values (?)
	 *  insert into transport (transport_type) values (?)
	 *  insert into person_transport (person_id, transport_id) values (?, ?)
	 *  insert into person_transport (person_id, transport_id) values (?, ?)
	 * </pre>
	 */

	@Test
	public void testCascadeAllInverseFalse() {
		Session ses = factory.openSession();
		ses.beginTransaction();

		Person person = new Person();
		person.setFirstName("nobitha");
		person.setLastName("nobi");
		Transport t1 = new Transport();
		t1.setType("Flying Machine");
		person.setTransports(new HashSet<Transport>());
		person.getTransports().add(t1);
		Transport t2 = new Transport();
		t2.setType("Time Machine");
		person.getTransports().add(t2);
		ses.persist(person);

		ses.getTransaction().commit();
		ses.close();
		factory.close();
	}

	// Many-to-Many Association with Join Table , cascade=all , inverse =true
	/**
	 * Add inverse="true" cascade=all to <set name="transports"> tag in
	 * Person.hbm.xl <br/>
	 * you only save person. <br/>
	 * person and transport are NOT mapped in join table person_transport
	 * 
	 * <pre>
	 *  insert into person (first_name, last_name) values (?, ?)
	 *  insert into transport (transport_type) values (?)
	 *  insert into transport (transport_type) values (?)
	 * </pre>
	 */
	@Test
	public void testCascadeAllInverseTrue() {
		Session ses = factory.openSession();
		ses.beginTransaction();

		Person person = new Person();
		person.setFirstName("nobitha");
		person.setLastName("nobi");
		Transport t1 = new Transport();
		t1.setType("Flying Machine");
		person.setTransports(new HashSet<Transport>());
		person.getTransports().add(t1);
		Transport t2 = new Transport();
		t2.setType("Time Machine");
		person.getTransports().add(t2);
		ses.persist(person);

		ses.getTransaction().commit();
		ses.close();
	}

	private static SessionFactory sessionFactory() {
		// return new
		// Configuration().configure().setNamingStrategy(ImprovedNamingStrategy.INSTANCE).buildSessionFactory();
		return new Configuration().configure().buildSessionFactory();
	}
}
