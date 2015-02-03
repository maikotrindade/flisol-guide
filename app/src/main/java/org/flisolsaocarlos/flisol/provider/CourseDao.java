package org.flisolsaocarlos.flisol.provider;

import org.flisolsaocarlos.flisol.model.Course;

import java.util.List;

public interface CourseDao {

        public List<Course> findAll();

        public List<Course> findByEditionYear(int year);

        public Course findById(int id);

        public Course create(Course course);
    
}
