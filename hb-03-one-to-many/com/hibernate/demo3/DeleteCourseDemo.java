package com.hibernate.demo3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo2.entity.InstructorDetail;
import com.hibernate.demo2.entity.Course;
import com.hibernate.demo2.entity.*;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		
		//create session factory
		//SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();//default
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			
			
			//start a transaction
			session.beginTransaction();
			
			//get a course
			int theId =10;
			Course tempCourse = session.get(Course.class,theId);
			
			//delete course
			System.out.println("Deleting course: " +tempCourse);
			
			session.delete(tempCourse);
			
			session.getTransaction().commit();
			
			System.out.println("Done!..s");
			
			
		}finally {
			
			session.close();
			factory.close();
			
		}
		

	}

}
