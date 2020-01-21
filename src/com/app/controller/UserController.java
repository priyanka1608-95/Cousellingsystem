package com.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IBranchDao;
import com.app.dao.IuserDao;
import com.app.pojos.Branch;
import com.app.pojos.IdBranch;
import com.app.pojos.Preference;
import com.app.pojos.User;
import com.app.pojos.UserRole;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IuserDao dao;
	
	@Autowired
	IBranchDao branchDao;

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/register")
	public Integer register(@RequestBody User user) 
	{

		if (user != null)
		{
			user.setRole(UserRole.STUDENT);

			// hs.setAttribute("OTP", otp);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("One Time Password");
			mailMessage.setText("u r registered successfully");
			try {
				mailSender.send(mailMessage);
			} catch (MailException e) {
				System.out.println("inside mail exception");
				e.printStackTrace();
			}

		}
		return dao.registerUser(user);
		

	}	

	@PostMapping("/login")
	public User login(@RequestBody User user) {
		System.out.println(user);
		return dao.login(user);
	}

	
	
	@PostMapping("/preferencesOfUser")
	
	public Integer addPreference(@RequestBody IdBranch[] pref)
	{
		
		System.out.println("in adding preferences"+pref);
		
		for (IdBranch preference : pref)
		{
			User u=new User();
			u=dao.getUserById(preference.getUserId());
			
			System.out.println(u);
			
			Branch br=new Branch();
			br=branchDao.getBranchById(preference.getBranch_id());
			
			Preference newpref=new Preference();
			newpref.setBranch(br);
			newpref.setUser(u);
			System.out.println(newpref);
			dao.addPreference(newpref);
			
		}
		return pref.length;
		
		
	}
	
	
	
}
