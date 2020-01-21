package com.app.dao;

import com.app.pojos.Branch;
import com.app.pojos.Preference;
import com.app.pojos.User;

public interface IuserDao {
	int registerUser(User user);
	int generateOtp();
	public User login(User user);
	Integer addPreference(Preference pref);
	User getUserByEmail(String email);
	
	User getUserById(int id);

}
