package com.hibernate.demo3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo2.entity.Instructor;
import com.hibernate.demo2.entity.*;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		
		//create session factory
		//SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();//default
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			
			
			//start a transaction
			session.beginTransaction();
			
			//get the instructor from db
			int theId =1;
			Instructor tempInstructor =session.get(Instructor.class, theId);
			
			
			System.out.println("Instructor:"+tempInstructor);
			
			//get course for the instructor
			System.out.println("Courses:" + tempInstructor.getCourse());
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!..s");
			
			
		}finally {
			
			session.close();
			factory.close();
			
		}
		

	}

}
