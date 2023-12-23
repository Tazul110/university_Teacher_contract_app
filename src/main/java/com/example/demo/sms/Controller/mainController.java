package com.example.demo.sms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import javax.sql.rowset.serial.SerialException;
import java.sql.Blob;
import java.sql.SQLException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.sms.Model.Teacher;
import com.example.demo.sms.Repository.TeacherRepository;
import com.example.demo.sms.Service.TeacherService;


@Controller
public class mainController {
	
	@RequestMapping("/home")
	public String HomePage()
	{
		return "homePage.html";
	}
	
	
	@RequestMapping("/createAccount")
	public String Account(Model model)
	{
		return "account.html";
		
	}
	
	@Autowired
	private TeacherService teacherService;
	@PostMapping("/Created_teacher_account")
    public String addImagePost(@RequestParam("teacherName") String name, 
    		@RequestParam("department") String dept,
    		@RequestParam("email") String email,
    		@RequestParam("phone") String phone,
    		
    		@RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException
    {
		 byte[] bytes = file.getBytes();
	     Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        Teacher teacher = new Teacher();
        teacher.settEmail(email);
        teacher.settName(name);
        teacher.settDept(dept);
        teacher.settPhone(phone);
        
        teacher.settImage(blob);
        teacherService.create(teacher);
        return "redirect:/home";
    }
	
	//start 2 ....................................................................................   
	 @GetMapping("/Show_all_Teachers")
//	 public String TestPage()
//		{
//			return "test.html";
//		}
	    public String showTeachers(Model model) {
	        List<Teacher> teachers = teacherService.getAllTeachers();
	        model.addAttribute("teachers", teachers);
	        return "teachers_demo.html";
	    }
	 
	 @GetMapping("/display")
	    public ResponseEntity<byte[]> displayImage(@RequestParam("email") String email) throws IOException, SQLException
	    {
	        Teacher image=teacherService.viewById(email);
	        byte [] imageBytes = null;
	        imageBytes = image.gettImage().getBytes(1,(int) image.gettImage().length());
	        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	    }
//end 2.......................................................................................	
	    
	    
	  //start 4....................................................................................	
	    @GetMapping("/name")
		public String SearchByNmae()
		{
			return "namePage.html";
		}
	   
	    @Autowired
	    private TeacherRepository tr;
	    @GetMapping("/findbyname")
	    public String searchByName(@RequestParam("name") String name, Model model) {
			List<Teacher> p = tr.findBytName(name);
	     	model.addAttribute("programmer",p);
	      	return "searched_Teacher.html";
	        
	    }
	  //end 4.......................................................................................
	
}
