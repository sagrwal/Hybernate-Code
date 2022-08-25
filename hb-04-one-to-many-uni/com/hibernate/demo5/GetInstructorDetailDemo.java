package com.hibernate.demo5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo1.entity.*;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create session factory
		//SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();//default
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			
			//start a transaction
			session.beginTransaction();
			
			//get the instructor detail object
			int theId=2;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class,theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail:"+tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("the associate instructor:"+ tempInstructorDetail.getInstructor());
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!..s");
			
			
		}
		   catch(Exception e) {
			   e.printStackTrace();
		   }
		
		finally {
			//handle connection leak issue by closing session object
			session.close();
			factory.close();
		}
		

	}

}
