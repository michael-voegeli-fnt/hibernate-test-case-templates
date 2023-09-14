package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...

		var task = new TaskEntity();
		task.setId(123);
		entityManager.persist(task);

		var lType = new LocalizedLabel("task:type", Locale.GERMANY,"test-type");
		var lName = new LocalizedLabel("task:name", Locale.GERMANY,"test-name");

		task.getName().add(lName);
		task.getType().add(lType);

		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		entityManager.clear();

		var taskAfterFirstCommit = entityManager.find(TaskEntity.class,123);

		Assert.assertEquals(taskAfterFirstCommit.getName().size(),1);
		Assert.assertEquals(taskAfterFirstCommit.getType().size(),1);

		taskAfterFirstCommit.getType().clear();

		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		entityManager.clear();

		var taskAfterSecondCommit = entityManager.find(TaskEntity.class,123);
		Assert.assertEquals(taskAfterSecondCommit.getName().size(),1);
		Assert.assertEquals(taskAfterSecondCommit.getType().size(),0);


		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
