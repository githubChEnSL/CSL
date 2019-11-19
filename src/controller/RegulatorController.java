package controller;

import entity.regulator;
import jdbc.DatabaseRegulator;

public class RegulatorController{
	//π‹¿Ì‘±µ«¬Ω
	public static boolean RegulatorLogin(String RegulatorName,String Password) {
		boolean flag=false;
		DatabaseRegulator database=new DatabaseRegulator();
		try {
			regulator getObject=database.GetRegulatorForId(RegulatorName);
			if(getObject.getRegulatorName()!=null) {
				if(getObject.getPassword().equals(Password)) {
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
