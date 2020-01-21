package com.app.pojos;

public class IdBranch {
	private int branch_id;
	private int userId;
	
	public IdBranch() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public IdBranch(int branch_id, int userId) {
		super();
		this.branch_id = branch_id;
		this.userId = userId;
	}



	public int getBranch_id() {
		return branch_id;
	}



	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "IdBranch [branch_id=" + branch_id + ", userId=" + userId + "]";
	}
	
	
	
		
	

}
