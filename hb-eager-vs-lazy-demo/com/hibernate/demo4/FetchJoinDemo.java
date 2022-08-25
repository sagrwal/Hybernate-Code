package com.hibernate.demo4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.demo3.entity.Course;
import com.hibernate.demo3.entity.Instructor;
import com.hibernate.demo3.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		//create session factory
		//SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();//default
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			
			
			//start a transaction
			session.beginTransaction();
			
			//hibernate query  with HQL
			
			
			//get the instructor from db
			int theId =1;
			Query<Instructor> query= session.createQuery("select i from Instructor i "+"JOIN FETCH i.courses "+ "where i.id=:theInstructorId",Instructor.class);
			
			//set parameter on query
			query.setParameter("theInstructorId",theId);
			
			//execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("luv2code :Instructor:"+tempInstructor);
			
		
			
			//commit transaction
			session.getTransaction().commit();
			
			session.close();
			
			//To avoid lazy loading issue call getter method while session is open
			System.out.println("luv2code: Courses:" + tempInstructor.getCourse());
			
			System.out.println("luv2code: Done!..s");
			
			
		}finally {
			
			session.close();
			factory.close();
			
		}
		

	}

}
