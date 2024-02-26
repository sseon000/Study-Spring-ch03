package com.fastcampus.ch3;

import java.util.Date;
import java.util.Objects;

public class User {
	private int num;
	private String id;
	private String pw;
	private String name;
	private String gender;
	private Date birthday;
	private String ph;
	private String acept;
	private String type;
	
	
	public User() {}
	
	public User(int num, String id, String pw, String name, String gender, Date birthday, String ph, String acept, String type) {
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.ph = ph;
		this.acept = acept;
		this.type = type;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getPh() {
		return ph;
	}
	
	public void setPh(String ph) {
		this.ph = ph;
	}
	
	public String getAcept() {
		return acept;
	}
	
	public void setAcept(String acept) {
		this.acept = acept;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acept, birthday, gender, id, name, num, ph, pw, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(acept, other.acept) && Objects.equals(birthday, other.birthday)
				&& Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && num == other.num && Objects.equals(ph, other.ph)
				&& Objects.equals(pw, other.pw) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "User [num=" + num + ", id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender
				+ ", birthday=" + birthday + ", ph=" + ph + ", acept=" + acept + ", type=" + type + "]";
	}
	
}
