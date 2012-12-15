package org.redqueen.dao;

import org.redqueen.model.Course;
import org.redqueen.model.Student;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class SchoolService {
	static {
		ObjectifyService.register(Student.class);
		ObjectifyService.register(Course.class);
	}
}
