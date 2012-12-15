package org.redqueen.dao;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.redqueen.model.Course;
import org.redqueen.model.Student;

import com.googlecode.objectify.ObjectifyService;

public class CourseCRUD {
	public CourseCRUD() {
		ObjectifyService.register(Student.class);
		ObjectifyService.register(Course.class);
	}
	/**
	 * Put a course
	 */
	public static void saveCourse(Course course) {
		ofy().save().entity(course).now();
	}

	/**
	 * query a course, returns Course object
	 */
	public static Course getCourse(String name) {
		return ofy().load().type(Course.class).filter("branch", "ss1").first()
				.get();
	}

	/**
	 * Delete course
	 */
	public static void deleteCourse(Course course) {
		ofy().delete().type(Course.class).id(course.getId()).now();
	}

	/**
	 * Get Titles
	 */
	public static List getCourses() {
		return ofy().load().type(Course.class).list();
	}
}
