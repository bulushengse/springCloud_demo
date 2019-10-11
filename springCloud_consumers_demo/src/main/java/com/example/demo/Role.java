package com.example.demo;

public class Role {

	private String role_id;
	
	private String role_name;
	
	private String rights;
	
	private String remark;
	
	public Role() {}
	
	public Role(String role_id) {
		super();
		this.role_id=role_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name + ", rights=" + rights + ", remark=" + remark
				+ "]";
	}
	
	
}
