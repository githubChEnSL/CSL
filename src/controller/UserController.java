package controller;

import entity.user;
import jdbc.DatabaseUser;

public class UserController {
	//ÓÃ»§µÇÂ½
	public Boolean UserLogin(String username,String password) {
		boolean flag=false;
		DatabaseUser database=new DatabaseUser();
		try {
			user getObject=database.getUserForId(username);
			if(getObject.getPassword()!=null) {
				if(getObject.getPassword().equals(password)) {
					flag=true;
				}else {
					flag=false;
				}
			}else {
				flag= false;
			}
		} catch (Exception e) {
			flag= false;
		}
		database.CloseDatabase();
		return flag;
	}
}
