package com.hibernate.demo6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo5.entity.Instructor;
import com.hibernate.demo5.entity.Review;
import com.hibernate.demo5.entity.*;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		//create session factory
		//SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();//default
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			
			
			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course tempCourse = new Course("Hibernate- Many to Many Relation");
			
			
			//add some reviews
					tempCourse.addReview(new Review("Great course ... loved it!"));
					tempCourse.addReview(new Review("Wonderful Explination!"));
					tempCourse.addReview(new Review("Its cool!"));
			
			//save the course ... and leverage the cascade all :-
					System.out.println("Saving the course");
					session.save(tempCourse);
					System.out.println(tempCourse.getReviews());
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!..s");
			
			
		}finally {
			
			session.close();
			factory.close();
			
		}
		

	}

}
