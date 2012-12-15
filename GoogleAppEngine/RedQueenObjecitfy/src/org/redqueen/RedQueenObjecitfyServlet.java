package org.redqueen;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redqueen.dao.CourseCRUD;
import org.redqueen.model.Course;
import org.redqueen.model.Student;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;


@SuppressWarnings("serial")
public class RedQueenObjecitfyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
//		CourseCRUD ccrud = new CourseCRUD();
//		Course course = new Course();
//		course.setName("kalees2");
//		course.setBranch("ss2");
//		ccrud.saveCourse(course);
//		System.out.println("Course added ");
//		
		
//		Course course2 = ofy().load().type(Course.class).filter("name", "kalees2").first().get();
//		System.out.println("Purse retrived => " + course2);
		
		
//		Course course2 = ccrud.getCourse("10");
//		System.out.println("Purse retrived => " + course2);
//		System.out.println("retrived => " + course2.getBranch());
//		System.out.println("retrived => " + course2.getId());
		
		
//		Objectify objectify = ObjectifyService.begin();
//		Course course = new Course();
//		course.setName("kalees");
//		course.setBranch("ss");
//		objectify.put(course);
//		
//		
//		//Lets create some students for our class
//	    Student student=new Student();
//	    student.setName("Murat");
//	    student.setCourse(new OKey<Course>(Course.class, course.getId());
//	    objectify.put(student);
		
		
		ObjectifyService.register(Course.class);
		ObjectifyService.register(Student.class);
//		
//		// physics
//		Course course = new Course();
//		course.setName("bscPhysics");
//		course.setBranch("physics");
//		Key<Course> courseKey = ofy().save().entity(course).now();
//		System.out.println("courseKey => " + courseKey);
////		
//		Student stu = new Student();
//		stu.setName("inzy123");
//		stu.setCourse(courseKey);
//		System.out.println("saving ");
//		ofy().save().entity(stu).now();
//		
//		// computer
//		Course course1 = new Course();
//		course1.setName("softwaresystems");
//		course1.setBranch("cs");
//		Key<Course> courseKey1 = ofy().save().entity(course1).now();
//		System.out.println("courseKey => " + courseKey1);
////		
//		Student stu1 = new Student();
//		stu1.setName("kalees");
//		stu1.setCourse(courseKey1);
//		System.out.println("saving ");
//		ofy().save().entity(stu1).now();
//		
//		// bio
//		Course course2 = new Course();
//		course2.setName("bioTech");
//		course2.setBranch("biology");
//		Key<Course> courseKey2 = ofy().save().entity(course2).now();
//		System.out.println("courseKey => " + courseKey2);
////		
//		Student stu2 = new Student();
//		stu2.setName("bionssss2");
//		Key<Course> key22course = Key.create(Course.class, 32);
//		stu2.setCourse(key22course);
//		System.out.println("saving ");
//		ofy().save().entity(stu2);
		
		
		
		Course retCourse = ofy().load().type(Course.class).filter("name", "bioTech").first().get();
		System.out.println("retrived course =>  " + retCourse.getName());
		System.out.println("retrived course =>  " + retCourse.getId());
		
//		System.out.println("courseKey => " + courseKey1);
//		Student stuForCourse = ofy().load().type(Student.class).filterKey("course", retCourse);
//		
		Key<Course> key2course = Key.create(Course.class, retCourse.getId());
		System.out.println("course2Key => " + key2course);
//		
		for (Student studentRet: ofy().load().type(Student.class).list()){
			System.out.println("Expecting inzy => " + studentRet.getName());
		}
		
		System.out.println("Before size => " + ofy().load().type(Student.class).count());
		
		System.out.println("key2course => " + key2course);
		System.out.println("After ==> " + ofy().load().type(Student.class).filter("course", key2course).count());
//		Key<Course> keygen2course = Key.create(Course.class, 22);
//		Query<Student> filter = ofy().load().type(Student.class).filter("name", "kalees");
//		List<Student> stuLIst = filter.list();
//		System.out.println("Size => " + stuLIst.size());
//		for (Student stuTemp : stuLIst){
//			System.out.println("stuTemp => Expecting inzy " + stuTemp.getName());
//		}
		
		
//		Student stuTemp = ofy().load().type(Student.class).id(retCourse.getId()).get();
//		System.out.println("stuTemp => Expecting inzy " + stuTemp.getName());
	}
}
