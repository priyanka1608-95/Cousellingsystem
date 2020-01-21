package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.College;


@Repository
@Transactional
public class CollegeDaoImpl implements ICollegeDao
{
	@Autowired
	private SessionFactory sf;

	@Override
	public List<College> getAllCollege() {
		System.out.println("inside get method of dao");
		String jpql="select c from College c";
		return sf.getCurrentSession().createQuery(jpql,College.class).getResultList();
	}

}
