import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class TakeAttendance {
	
		//Ian Tibe
		//data fields
		String attendanceFile = "Attendance";	//base file name for attendance information
	
		//constructor
		/**
		 * default constructor
		 */
		public TakeAttendance()
		{
			
		}
	
	
	
		/**
		 * validates and stores attendance information
		 * @param id student id
		 * @param clas text of class name
	 * @throws IOException
		 * @throws ClassDoesNotExistException 
		 * @throws StudentNotInClassException 
		 * @throws DuplicateAttendanceEntryException 
		 */
	public void takeattendance(String id, String clas) throws IOException, ClassDoesNotExistException, StudentNotInClassException, DuplicateAttendanceEntryException {
			LocalDate date = LocalDate.now();
			LocalTime time = LocalTime.now();
			
			//validate class
			//done in add class class
			AddClass instance = new AddClass();
			// need to handle class not found
			// put class id in variable to use latter
			int classid = instance.classid(clas);
			//check if id is part of class
			AddStudent student = new AddStudent();
			if(student.validatestudentid(id, clas) == false)
			{
				throw new StudentNotInClassException("student not in class");
			}
			
			//open file stream 
			String filename = attendanceFile + "." + classid; 
			File file = new File(filename);
			
			//check if file exists, if not create
			if (file.exists() == false) {
				file.createNewFile();
			}
			
			//check if student has allready done attendance
			Scanner in  = new Scanner(file);
			
			while(in.hasNextLine())
			{
				String line = in.nextLine();
				Scanner parse = new Scanner(line);
				parse.useDelimiter(",");
				String item = parse.next();
				item.trim();
				String fileDate = parse.next();
				fileDate.trim();
				//if id number in file equals input id number and date in file in file equals
				//todays date, student has taken attendance allready
				if(item.equals(id))
				{
					//important keep date.toString intact. This will convert date from LocalDate to String so if statement will work
					if(fileDate.equals(date.toString()))
					{
					parse.close();
					in.close();
					throw new DuplicateAttendanceEntryException("Student has taken attendance");
					}
				}
				
				parse.close();
			}
			
			in.close();
			
			//write attendance data to file
			FileWriter output = new FileWriter(filename, true);
			
			output.append(id + "," + date + "," + time + "," + InetAddress.getLocalHost().getHostAddress());
			output.append(System.lineSeparator());
			output.close();
			
		}
			

}
