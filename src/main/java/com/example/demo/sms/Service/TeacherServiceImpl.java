package com.example.demo.sms.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.sms.Model.Teacher;
import com.example.demo.sms.Repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

	
	@Autowired
	private TeacherRepository teacherRepo;

	@Override
	public Teacher create(Teacher teacher) {
		return teacherRepo.save(teacher);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepo.findAll();
	}

	@Override
	public Teacher viewById(String email) {
		return teacherRepo.findById(email).get();
	}
	
//	public Teacher create(Teacher teacher)
//	{
//		return teacherRepo.save(teacher);
//	}
//	
//	public List<Teacher> getAllTeachers() {
//		// TODO Auto-generated method stub
//		return teacherRepo.findAll();
//	}
//	
//	public Teacher viewById(String email) {
//		// TODO Auto-generated method stub
//		return teacherRepo.findById(email).get();
//	}
}
