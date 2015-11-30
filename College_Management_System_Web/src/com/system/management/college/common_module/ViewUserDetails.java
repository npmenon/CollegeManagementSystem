package com.system.management.college.common_module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.system.management.college.faculty_module.FacultyPersonalModel;
import com.system.management.college.faculty_module.FacultyView;

public class ViewUserDetails {

	public void showDetails(String departmentId, String departmentName) {
		
		String facultyId = null;
		FacultyView facultyView = new FacultyView(facultyId);
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<FacultyPersonalModel> facultyList = facultyView.getFacultyList(departmentId);
		
		int i = 1;
		System.out.println("Choose the faculty of "+ departmentName +":\n\nFacultyId\tFirst Name\tLast Name ");
		for(FacultyPersonalModel faculty: facultyList){
			
			System.out.println(i+") "+faculty.getFacultyId()+"\t"+ faculty.getFirstName()
					+"\t\t"+faculty.getLastName()+"\n");			
		}
		System.out.println("\n\nEnter the faculty id whose profile needs to be viewed: ");
		try {
			facultyId = bin.readLine();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		facultyView.setFacultyId(facultyId);
		facultyView.viewProfile();
	}

	
	
}
