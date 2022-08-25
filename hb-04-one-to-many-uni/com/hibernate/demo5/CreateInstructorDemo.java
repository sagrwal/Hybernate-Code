package com.hibernate.demo5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo2.entity.Instructor;
import com.hibernate.demo2.entity.*;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		//create session factory
		//SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();//default
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			
			Instructor tempInstructor = new Instructor("Susan","Public","susan.public@123.net");
			
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com","Gamer ");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
			//start a transaction
			session.beginTransaction();
			
			//save the instruction
			//Note: this will also save the details object because of CascadeType.All
			
			System.out.println("Saving instructor:"+tempInstructor);
			session.save(tempInstructor);
			
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!..s");
			
			
		}finally {
			
			session.close();
			factory.close();
			
		}
		

	}

}
