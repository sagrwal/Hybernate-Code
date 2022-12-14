package com.hibernate.demo4.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hibernate.demo4.entity.Course;
import com.hibernate.demo4.entity.InstructorDetail;



@Entity
@Table(name="instructor")
public class Instructor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	//** Set up mapping to InstructorDetail entity
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetail instructorDetail1;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="instructor", cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Course> courses;
	public Instructor() {
		
	}

	public Instructor(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail1;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail1 = instructorDetail;
	}

	
	public InstructorDetail getInstructorDetail1() {
		return instructorDetail1;
	}

	public void setInstructorDetail1(InstructorDetail instructorDetail1) {
		this.instructorDetail1 = instructorDetail1;
	}

	public List<Course> getCourse() {
		return courses;
	}

	public void setCourse(List<Course> course) {
		this.courses = course;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail2=" + instructorDetail1 + "]";
	}


	
	//add convenience methods for bi-directional relationship
	
	public void add(Course tempCourse) {
		if(courses == null) {
			courses = new ArrayList<>();
		}
		courses.add(tempCourse);
		
		tempCourse.setInstructor(this);
	}
	
	

	
	
}
