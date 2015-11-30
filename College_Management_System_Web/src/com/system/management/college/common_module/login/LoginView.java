package com.system.management.college.common_module.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView {
	
	public boolean signIn(String userId,String pwd,int privilegeLevel){

		userId = userId.toUpperCase();
		//create object of LoginModel
		LoginModel login = new LoginModel();
		login.setUserId(userId);
		login.setPwd(pwd);
		login.setPrivilege_level(privilegeLevel);
		
		// pass the object of LoginModel to LoginProcess and validate the user
		LoginProcess process = new LoginProcess();
		process.validateUser(login);
		
		//check if the user exists
		if(login.isStatus())
			return true;
		else{
			System.out.println(login.getMessage());
			return false;
		}		
	}
	
	public boolean signUp(String userId,int privilegeLevel){
		
		userId = userId.toUpperCase();
		String oldPwd,pwd = null;
		int passChoice;
		boolean signUpStatus = false;
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your choice:\n\t");
		System.out.println("\n\t1.Signing up for the first time or \n\t2.Change current password\n\tYour choice: ");
		try {
			passChoice = Integer.parseInt(bin.readLine());
			if(passChoice == 1)
				System.out.println("Enter the password provided by the admin: ");
			else if(passChoice == 2)
				System.out.println("Enter your old password: ");
			
			oldPwd = bin.readLine();
	
			signUpStatus = signIn(userId, oldPwd, privilegeLevel);
			
			while(!signUpStatus){
				
				System.out.println("Do you wish to\n\t" +
						"1.try again or \n\t2.quit\n\tEnter your choice: ");
				passChoice = Integer.parseInt(bin.readLine());
				if(passChoice == 1){
					System.out.println("Enter the password provided by the admin: ");
					oldPwd = bin.readLine();
					signUpStatus = signIn(userId, oldPwd, privilegeLevel);
				}
				else{
					break;
				}
					
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(signUpStatus)
		{
			System.out.println("Enter new password: ");
			try {
				pwd = bin.readLine();
				
			} catch (IOException e) {			
				e.printStackTrace();
			}
			
			LoginModel login = new LoginModel();
			login.setUserId(userId);
			login.setPwd(pwd);
			login.setPrivilege_level(privilegeLevel);
			
			// pass the object of LoginModel to LoginProcess and validate the user
			LoginProcess process = new LoginProcess();
			process.insertUser(login);
			
			if(login.isStatus())
				return true;
			else{
				System.out.println("Trouble signing up..please try again later");
				return false;
			}
		}
		else
			return false;
		
		

	}
	
	public boolean checkUserExistence(String userId, int userPrivilege)
	{
		LoginModel login = new LoginModel();
		LoginProcess loginProcess = new LoginProcess();
		login.setUserId(userId);
		login.setPrivilege_level(userPrivilege);
		if(loginProcess.checkUserExistence(login))
			return true;
		else
			return false;		
	}

}
