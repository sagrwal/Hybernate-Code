package com.hibernate.demo5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo4.entity.Instructor;
import com.hibernate.demo4.entity.Review;
import com.hibernate.demo4.entity.*;

public class DeleteCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		//create session factory
		//SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();//default
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			
			//start a transaction
			session.beginTransaction();
			
			//get the course
			int theId =10;
			Course tempCourse =session.get(Course.class, theId);
			
			//print the course
			System.out.println("Deleting the course ...");
			System.out.println(tempCourse);
			
			//print the course review
			System.out.println(tempCourse.getReviews());
			
			//delete the course
			session.delete(tempCourse);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!..s");
			
			
		}finally {
			
			session.close();
			factory.close();
			
		}
		

	}

}
