package com.hibernate.demo4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo3.entity.InstructorDetail;
import com.hibernate.demo3.entity.Course;
import com.hibernate.demo3.entity.*;

public class EagerLazyDemo {

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
			
			
			System.out.println("luv2code :Instructor:"+tempInstructor);
			
			//get course for the instructor
			
			//This is to solve the lazy loading issue
			System.out.println("luv2code: Courses:" + tempInstructor.getCourse());
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
