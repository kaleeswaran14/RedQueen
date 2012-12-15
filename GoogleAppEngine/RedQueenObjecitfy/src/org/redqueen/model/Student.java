package org.redqueen.model;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Student {
	@Id
    public Long id;
	public String name;
	@Index
	public Key<Course> course;
    
	public Key<Course> getCourse() {
		return course;
	}

	public void setCourse(Key<Course> course) {
		this.course = course;
	}

	public Student() {
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
