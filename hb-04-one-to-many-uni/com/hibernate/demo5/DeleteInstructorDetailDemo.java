package com.hibernate.demo5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo1.entity.*;

public class DeleteInstructorDetailDemo {

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
			int theId=13;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class,theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail:"+tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("the associate instructor:"+ tempInstructorDetail.getInstructor());
			
		
			//now let's delete the instructor detail
			System.out.println("Deleting tempInstructorDetail:"+ tempInstructorDetail);
			
			//remove the associated object reference 
			//break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			session.getTransaction().commit();
			
			System.out.println("Done!..");
			
			
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
