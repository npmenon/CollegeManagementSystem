package com.system.management.college.faculty_module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.system.management.college.student_module.StudentCurrAcademicModel;

public class FacultyView {
	
	private String facultyId;
	@SuppressWarnings("unused")
	private ArrayList<String> listProfile = null;
	private BufferedReader bin = null;
	private FacultyPersonalModel facultyPersonalObj = null;
	private FacultyProcess facultyProcess = null;
	private StudentCurrAcademicModel studentAcademicObj = null;
	
	public FacultyView(String facultyId) {
		this.facultyId = facultyId;
	}

	
	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}


	public ArrayList<FacultyPersonalModel> getFacultyList(String departmentId){
		
		ArrayList<FacultyPersonalModel> listOfFaculty = new ArrayList<FacultyPersonalModel>();
		facultyProcess = new FacultyProcess();
		facultyPersonalObj = new FacultyPersonalModel();
		
		facultyPersonalObj.setMessage("listOffaculties:"+departmentId);
		//call facultyProcess and take the list of faculties
		try {
			listOfFaculty = facultyProcess.viewPersonalProfile(facultyPersonalObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("exception");
		}
		
		return listOfFaculty;
	}
	
	
	public void viewProfile()
	{
		//show faculty profile
		facultyPersonalObj = new FacultyPersonalModel();
		facultyPersonalObj.setFacultyId(facultyId);
		facultyPersonalObj.setMessage("viewingPersonalProfile");
		//facultyPersonalObj.setFacultyId(facultyId);
		ArrayList<FacultyPersonalModel> listDet = facultyProcess.viewPersonalProfile(facultyPersonalObj);
		if(listDet.isEmpty())
			System.out.println("No personal details saved in the database..please enter your personal details and" +
						" then try again!");
		else
		{
			System.out.println("====================================================================");
			System.out.println("successful retrieval of personal details from database!!!!");
			System.out.println("\nPersonal Details of faculty: " + facultyId + "\n");
			for(FacultyPersonalModel obj : listDet){
				System.out.println("Faculty id: " + obj.getFacultyId());
				System.out.println("First Name: " + obj.getFirstName());
				System.out.println("Last Name: " + obj.getLastName());
				System.out.println("Email id: " + obj.getEmailId());
				System.out.println("Contact: " + obj.getContact());
				System.out.println("Hire date: " + obj.getHire_date());
				System.out.println("Date of birth: " + obj.getDateOfBirth());
			}
			System.out.println("====================================================================");
		}	
		
		//Viewing Current academic details 
		FacultyProfModel facultyProfModel = new FacultyProfModel();
		facultyProfModel.setFacultyId(facultyId);
		
		ArrayList<FacultyProfModel> listDet2 = facultyProcess.viewProfessionalProfile(facultyProfModel);
		if(listDet2.isEmpty())
			System.out.println("\nNo current professional details saved in the database for user: !" + facultyId);
		else{
			System.out.println("successful retrieval of professional details from database!!!!");
			System.out.println("\nProfessional Details of Faculty: " + facultyId + "\n");
			for(FacultyProfModel obj : listDet2){
				System.out.println("Faculty id: " + obj.getFacultyId());
				System.out.println("Department id: " + obj.getDepartmentId());
				System.out.println("Graduation Institution: " + obj.getGradInstit());
				System.out.println("Graduation marks: " + obj.getGradMarks());
				System.out.println("Post graduation institution: " + obj.getPostGradInstit());
				System.out.println("Post graduation marks: " + obj.getPostGradMarks());
				System.out.println("Current designation: " + obj.getCurrentDesign());
			}
			System.out.println("====================================================================\n");
		}			
	}
	
	public void updatePersonalProfile() throws IOException
	{

		bin = new BufferedReader(new InputStreamReader(System.in));		
		
		int choice=0;
		String updatedItem = null;
		boolean flag;
		do{
			
			System.out.println("Select the field to be added or updated:\n\t1.First Name\n\t2.Last Name\n\t3.Email id" +
					"\n\t4.Contact\n\t5.Hire date\n\t6.Date of birth\n\t7.Return back to the previous menu\n\t" +
					"Enter your choice: ");
			choice = Integer.parseInt(bin.readLine());
			
			flag = true;
			switch (choice) {
			case 1:
				
				System.out.println("Enter the First name: ");						
				String firstName = bin.readLine();
				facultyPersonalObj.setFirstName(firstName);
				updatedItem = "First Name";
				break;
				
			case 2:
				
				System.out.println("Enter the Last name: ");
				String lastName = bin.readLine();
				facultyPersonalObj.setLastName(lastName);
				updatedItem = "Last Name";
				break;
				
			case 3:
				
				System.out.println("Enter the Email id: ");
				String emailId = bin.readLine();
				facultyPersonalObj.setEmailId(emailId);
				updatedItem = "Email-id";
				break;
				
			case 4:
				
				System.out.println("Enter contact no: ");
				String contact = bin.readLine();
				facultyPersonalObj.setContact(contact);
				updatedItem = "Contact no";
				break;
				
			case 5:
				
				System.out.println("Enter hire date in dd-MM-yyyy: ");
				String hireDate = bin.readLine();
				hireDate = (checkDateFormat(hireDate));
				if(!hireDate.equals("notProperFormat"))
					facultyPersonalObj.setHire_date(hireDate);
				else{
					System.out.println("Enter the date in proper format");
					flag = false;	
				}
				facultyPersonalObj.setHire_date(hireDate);
				updatedItem = "hire date";
				break;

			case 6:
				
				System.out.println("Enter date of birth in dd-MM-yyyy: ");
				String dob = bin.readLine();
				dob = (checkDateFormat(dob));
				if(!dob.equals("notProperFormat"))
					facultyPersonalObj.setHire_date(dob);
				else{
					System.out.println("Enter the date in proper format");
					flag = false;	
				}
				facultyPersonalObj.setDateOfBirth(dob);
				updatedItem = "date of birth";
				break;

				
			default:
				choice=7;
				flag=false;
				break;
			}
			
			if(flag){
				
			//	if(facultyProcess.updatePersonalProfile(facultyPersonalObj,choice))
			//		System.out.println(updatedItem+" of faculty with id: " + facultyPersonalObj.getFacultyId()
			//				+ "successfully updated in the database");
			//	else
			//		System.out.println("Trouble updating "+ updatedItem +" details in the database");		
					
			}				
			
		}while(choice!=7);	
			
	}
	
	private String checkDateFormat(String joinDate) {
		// TODO Auto-generated method stub
		
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date2 = formatter1.parse(joinDate);
			if(!formatter1.format(date2).toString().equals(joinDate))
				joinDate = "notProperFormat";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			joinDate = "notProperFormat";
		}
		
		if(!joinDate.equals("notProperFormat")){
			StringTokenizer tokens = new StringTokenizer(joinDate,"[/-]");
			
			int date = Integer.parseInt(tokens.nextToken());
			int month = Integer.parseInt(tokens.nextToken());
			int year = Integer.parseInt(tokens.nextToken());
			
			Calendar cal = Calendar.getInstance();
			cal.set(year, month-1, date);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date1 = formatter.format(cal.getTime()).toString();
			return date1;
		}
		else
			return joinDate;

	}
	
	public void updateStudentAcademicProfile() throws IOException
	{
		Scanner scanObj = new Scanner(System.in);
		System.out.println("Enter student id to be updated: ");
		String studentId = scanObj.nextLine();
		
		//Creating an object of StudentCurrAcademicModel and call method in facultyProcess
		studentAcademicObj.setStudentId(studentId);		
		bin = new BufferedReader(new InputStreamReader(System.in));		
		
		
		//check whether the faculty and student belong to the same department		
		if(facultyProcess.checkDept(studentAcademicObj, facultyId))
		{
			
			int choice=0;
			String updatedItem = null;
			boolean flag;
			do{
				
				System.out.println("Select the field to be added or updated:\n\t1.Semester 1 marks\n\t" +
						"2.Semester 2 marks\n\t3.Semester 3 marks\n\t4.Semester 4 marks\n\t5.Current Semester\n\t" +
						"6.Return to previous menu\n\tEnter your choice: ");
				choice = Integer.parseInt(bin.readLine());
				
				flag = true;
				switch (choice) {
				case 1:
					
					System.out.println("Enter the Semester 1 marks: ");						
					float marksSem1 = Float.parseFloat(bin.readLine());
					studentAcademicObj.setSem1Marks(marksSem1);
					updatedItem = "Semester 1 marks";
					break;
					
				case 2:
					
					System.out.println("Enter the Semester 2 marks: ");						
					float marksSem2 = Float.parseFloat(bin.readLine());
					studentAcademicObj.setSem2Marks(marksSem2);
					updatedItem = "Semester 2 marks";
					break;
					
				case 3:
					
					System.out.println("Enter the Semester 3 marks: ");						
					float marksSem3 = Float.parseFloat(bin.readLine());
					studentAcademicObj.setSem3Marks(marksSem3);
					updatedItem = "Semester 3 marks";
					break;
					
				case 4:
					
					System.out.println("Enter the Semester 4 marks: ");						
					float marksSem4 = Float.parseFloat(bin.readLine());
					studentAcademicObj.setSem4Marks(marksSem4);
					updatedItem = "Semester 4 marks";
					break;
					
				case 5:
					
					System.out.println("Enter the Current Semester: ");
					int currentSem = Integer.parseInt(bin.readLine());
					studentAcademicObj.setCurrentSem(currentSem);
					updatedItem = "Current semester";
					break;

					
				default:
					choice=6;
					flag=false;
					break;
				}
				
		/*		if(flag){
					
					if(facultyProcess.updateStudentAcademicProfile(studentAcademicObj,choice))
						System.out.println(updatedItem+" of student with id: " + studentAcademicObj.getStudentId()
								+ "successfully updated in the database");
					else
						System.out.println("Trouble updating "+ updatedItem +" details in the database");		
						
				}	*/			
				
			}while(choice!=6);		
			
		}
		
	}
	
	public void mainFacultyFunc()
	{
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		facultyPersonalObj = new FacultyPersonalModel();
		facultyPersonalObj.setFacultyId(facultyId);
		facultyProcess = new FacultyProcess();
		studentAcademicObj = new StudentCurrAcademicModel();
		int choice = 0;
		do{
			System.out.println("\n\tEnter your choice: \n\t1.View Profile\n\t2.Modify personal details: \n\t3.Update Student academic profile" +
					"\n\t4.Return to the main menu\n\tEnter your choice: ");
			try {
				choice = Integer.parseInt(bin.readLine());
			} catch (NumberFormatException e1) {
				
				e1.printStackTrace();
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}

			
			if(choice == 1){			
				viewProfile();
			}			
			else if(choice == 2){
				
				try {
					updatePersonalProfile();				
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
			
			else if(choice == 3){
				
				try {
					updateStudentAcademicProfile();				
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}while(choice!=4);

	}
}
