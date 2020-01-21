package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Branch;

@Service
@Transactional
public class BranchDaoImpl implements IBranchDao {

	@Autowired
	private SessionFactory sf;
	
	
	@Override
	public Branch getBranchById(int id) {
		
		return sf.getCurrentSession().get(Branch.class,id);
		
		/*String jpql="select b from Branch b where branch_id=:bid";
		return sf.getCurrentSession().createQuery(jpql,Branch.class).setParameter("bid",id).getSingleResult();*/
	}

}
