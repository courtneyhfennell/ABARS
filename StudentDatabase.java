import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.*;
import jxl.read.biff.BiffException;


/**
 * @author Matthew Alpert
 * @version 1.0
 * @created 16-Oct-2014 3:32:01 AM
 */
public class StudentDatabase {

	private ArrayList<Student> studentList;

	public StudentDatabase() throws BiffException, IOException{
		Workbook workbook = Workbook.getWorkbook(new File("Student Database.xls"));
		Sheet sheet = workbook.getSheet(0);
		CourseDatabase courseList = new CourseDatabase();
		Student newStudent;
		ArrayList<GradedCourse> coursesTaken = new ArrayList<GradedCourse>();
		int numID, numPoints;
		String password, username, name, address;
		studentList = new ArrayList<Student>();
		Cell[] topRow =  sheet.getRow(0);
		Cell[] cur = sheet.getRow(1);
		int countRow = 1;
		int i;
		
//		adding in all the students in the database
		while(!(cur[0].getContents().equals("end"))){
			
			username = cur[0].getContents();
			password = cur[1].getContents();
			numID = Integer.parseInt(cur[2].getContents());
			name = cur[3].getContents();
			address = cur[4].getContents();
			numPoints = Integer.parseInt(cur[5].getContents());
			i = 6;
			coursesTaken.clear();
			
			
			while(i < topRow.length && topRow[i].getType() != CellType.EMPTY){
				if(cur[i].getType() == CellType.LABEL){
					coursesTaken.add(addGradedCourse(courseList.getCourse(topRow[i].getContents()), cur[i].getContents()));
				}
				i++;
			}
			
			newStudent = new Student(coursesTaken, numID, numPoints, password, username, name, address);
			studentList.add(newStudent);
			countRow++;
			cur = sheet.getRow(countRow);
		}
	}


	private Student searchFor(String username){

		int i = 0;

		while(!(studentList.get(i).getUsername().equals(username)) && i<studentList.size()){
			i++;
		}

		if(i == studentList.size()){
			return null;
		}else{
			return studentList.get(i);
		}
	}

	/**
	 * Returns a validation to the Login class whether or not the user information
	 * will grant access.
	 */
	public Student Validate(String username, String password){
		Student user = searchFor(username);
		if(user != null && password.equals(user.getPassword())){
			return user;
		}else{
			return null;
		}
	}
	
	
	
	private GradedCourse addGradedCourse(Course taken, String grade){
		
		return new GradedCourse(taken.getCourseNum(), taken.getCredits(), taken.getCorequisite(), taken.getPrerequisites(), grade, taken.getTimeSlot(), taken.getDataColCourse(), grade);
		
	}
}//end StudentDatabase