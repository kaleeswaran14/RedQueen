package org.redqueen.dao;

import java.util.List;

import org.redqueen.model.Course;
import org.redqueen.model.Student;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.ObjectifyService;

public class StudentCRUD {
	static {
		ObjectifyService.register(Student.class);
		ObjectifyService.register(Course.class);
	}
	/**
	 * Put a student
	 */
	public static void saveStudent(Student student) {
		ofy().save().entity(student).now();
	}

	/**
	 * query a student, returns Student object
	 */
	public static Student getStudent(String name) {
		return ofy().load().type(Student.class).filter("name", name).first()
				.get();
	}

	/**
	 * Delete student
	 */
	public static void deleteStudent(Student student) {
		ofy().delete().type(Student.class).id(student.getId()).now();
	}

	/**
	 * Get Titles
	 */
	public static List getStudents() {
		return ofy().load().type(Student.class).list();
	}
}
