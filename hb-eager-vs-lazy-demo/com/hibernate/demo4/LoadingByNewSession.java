package com.hibernate.demo4;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.demo3.entity.Course;
import com.hibernate.demo3.entity.Instructor;
import com.hibernate.demo3.entity.InstructorDetail;

public class LoadingByNewSession {
	
	public static void main(String[] args) {
		
		
		//create session factory
		
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		
		Session session =factory.getCurrentSession();
		
		try {
			//start a transcation
			session.beginTransaction();
			
			//get the instructor from db
			int theId =1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("luv2code: Instructor: "+tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			System.out.println("\nluv2code: The session is now closed!\n");
			
			//Now Creating new session
			System.err.println("\n\nluv2Code: opening a new session \n");
			
			session =factory.getCurrentSession();
			
			session.beginTransaction();
			
			Query<Course> query =session.createQuery("select c from Course c " +" where c.instructor.id=:theInstructorId",Course.class);
			
			query.setParameter("theInstructorId",theId);
			
			List<Course> tempCourses =query.getResultList();
			
			System.out.println("tempCourses: "+tempCourses);
			
			tempInstructor.setCourse(tempCourses);
			 System.out.println("luv2code: Courses: " + tempInstructor.getCourse());
	          
			 session.getTransaction().commit();
	            
	            System.out.println("luv2code: Done!");
		}
		finally {
			
			//add clean up code
			session.close();
			
			factory.close();
		}
	}

}
