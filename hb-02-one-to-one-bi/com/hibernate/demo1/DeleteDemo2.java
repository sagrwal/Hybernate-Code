package com.hibernate.demo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;
import com.hibernate.demo1.entity.*;

public class DeleteDemo2 {

	public static void main(String[] args) {
		
		//create session factory
		//SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();//default
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			
			
			
			//start a transaction
			session.beginTransaction();
			
			//get instructor by primary key/id
			int theId=3;
			Instructor tempInstructor =session.get(Instructor.class, theId);
			
			System.out.println("Found instructor:"+tempInstructor);
			
			//delete the instructors
			if(tempInstructor !=null) {
				System.out.println("Deleting: "+tempInstructor);
				//Note:will Also delete associate "details" object because of CascadeType.All
				session.delete(tempInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!..s");
			
			
		}finally {
			factory.close();
			session.close();
		}
		

	}

}
