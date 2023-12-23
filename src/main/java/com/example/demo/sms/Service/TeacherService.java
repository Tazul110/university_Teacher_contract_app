package com.example.demo.sms.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.sms.Model.Teacher;

@Service
public interface TeacherService {

	Teacher create(Teacher teacher);
	List<Teacher> getAllTeachers();
	Teacher viewById(String email);
}
