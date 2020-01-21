package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IPreferenceDao;
import com.app.dao.IuserDao;
import com.app.pojos.Preference;
import com.app.pojos.User;

@RestController
@RequestMapping("/checkPreference/{uid}")
public class PreferenceController 
{
	@Autowired
	IPreferenceDao prefDao;
	
	@Autowired
	IuserDao dao;
	
	User user=new User();
	List<Preference> prefs =new ArrayList<Preference>();
	Preference allocatedClg=new Preference();
	
	public PreferenceController() {
	}
	
	
	public void getPrefByUserId()
	{
		System.out.println("in get user id");
	
		
	
	}
	
	@GetMapping
	public String isAllocated(@PathVariable int uid)
	{
		System.out.println("in Is Allocated");
		user=dao.getUserById(uid);
		System.out.println(user);
		prefs= prefDao.getPrefByUserId(user);
		System.out.println(prefs);
		
		
		boolean flag=false;
		for (Preference preference : prefs)
		{
			if(preference.getBranch().getCriteria()<= user.getCet() && preference.getBranch().getAvailable_seats()>0)
			{
				System.out.println(preference.getBranch().getCriteria());
				flag=true;
				
				
				int var=preference.getBranch().getAllocated_seats()+1;
				preference.getBranch().setAllocated_seats(var);
				
				var=preference.getBranch().getAvailable_seats()-1;
				preference.getBranch().setAvailable_seats(var);
				allocatedClg=preference;
				
				prefDao.updatePref(preference.getBranch());
				
				break;				
				
			}
			
		}
		if(flag)
			return ("you are allocated to"+allocatedClg.getBranch().getCollege().getCollegeName());
		else
			return "sorry.. you are not alloted to any college";
		
	}
}
