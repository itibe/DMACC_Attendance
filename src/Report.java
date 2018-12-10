import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class Report {

	// Ian Tibe
	// data fields
	private String studentFile = "StudentList"; // base file name for student information
	private String attendanceFile = "Attendance"; // base file name for attendance information
	private String notAttendText = "Not Attended"; // text to display when student has not attended

	// constructor
	/**
	 * default no-arg constructor
	 */
	public Report() {

	}

	// helper method

	/**
	 * generates class list with given class
	 * 
	 * @param cls
	 *            class to get student list
	 * @return 2-d array of class list
	 * @throws IOException
	 * @throws ClassDoesNotExistException
	 * @throws NoStudentInClassException
	 */
	public String[][] classlist(String cls) throws IOException, ClassDoesNotExistException, NoStudentInClassException {
		// put initial data in linked lists
		// parallel arrays
		LinkedList<String> idNumber = new LinkedList<String>();
		LinkedList<String> name = new LinkedList<String>();

		// get class id number
		AddClass classInstance = new AddClass();
		int classid = classInstance.classid(cls);

		// build student list file name for class
		String fileName = studentFile + "." + classid;

		// check if student file exists
		File file = new File(fileName);
		if (file.exists() == false) {
			throw new NoStudentInClassException("No student in the class");
		}
		// loop through file to get lines
		Scanner in = new Scanner(file);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			idNumber.add(parse.next());
			name.add(parse.next());
			parse.close();
		}

		in.close();
		// put items in 2-d array
		String[][] data = new String[idNumber.size()][2];

		for (int row = 0; row < data.length; row++) {

			data[row][0] = idNumber.get(row);
			data[row][1] = name.get(row);

		}

		return data;
	}

	public LinkedList<DataStorage> classattendance(String cls)
			throws IOException, ClassDoesNotExistException, NoStudentInClassException, NoAttendanceDataFoundException {

		DataStorage currentNode; // holds link to current DataStorage in dataHolder

		LinkedList<DataStorage> dataHolder = new LinkedList<DataStorage>(); // hold DataStorage objects

		// get class number
		AddClass classInstance = new AddClass();
		int classid = classInstance.classid(cls);

		// build student list file name for class
		String studentFileName = studentFile + "." + classid;

		// access proper student file
		// check if student file exists
		File studentFile = new File(studentFileName);
		if (studentFile.exists() == false) {
			throw new NoStudentInClassException("No student in the class");
		}

		String attendanceFileName = attendanceFile + "." + classid;
		File attendanceFile = new File(attendanceFileName);

		if (attendanceFile.exists() == false) {
			throw new NoAttendanceDataFoundException("No Attendance Data found");
		}

		// scan student file for name
		Scanner studentDataLine = new Scanner(studentFile);
		while (studentDataLine.hasNextLine()) {
			String studentLine = studentDataLine.nextLine();
			Scanner studentParse = new Scanner(studentLine);
			studentParse.useDelimiter(",");
			// get student name and number from student attendance files
			String studentNumber = studentParse.next();
			String studentName = studentParse.next();

			// create new data storage instance and add name and number to instance
			DataStorage item = new DataStorage();
			item.setId(studentNumber);
			item.setName(studentName);

			// set currentNode to current item and put in linked list
			currentNode = item;
			dataHolder.add(item);

			// scan attendance files for each student in class
			Scanner attendanceDataLine = new Scanner(attendanceFile);

			while (attendanceDataLine.hasNextLine()) {
				String attendanceline = attendanceDataLine.nextLine();
				Scanner attendanceParse = new Scanner(attendanceline);
				attendanceParse.useDelimiter(",");
				String studentNumberInAttendance = attendanceParse.next();
				// if student number in student list and student number in attendance list
				// match, get data from attendance file
				if (studentNumber.equals(studentNumberInAttendance)) {
					String studentDateInAttendance = attendanceParse.next();
					// convert string to LocalDate format
					// LocalDate studentDateInAttendanceLocalDate =
					// LocalDate.parse(studentDateInAttendance);
					// add to Date linked list in DataStorage
					currentNode.getDate().add(studentDateInAttendance);

				}

			}

		}
		studentDataLine.close();

		return dataHolder;
	}

	public String[][] dateoflastattendance(String cla)
			throws IOException, ClassDoesNotExistException, NoStudentInClassException, NoAttendanceDataFoundException { // put
																														// initial
																														// data
																														// in
																														// linked
																														// lists
																														// linkedlist
		LinkedList<LocalDate> date = new LinkedList<LocalDate>();
		LinkedList<String> name = new LinkedList<String>();

		LinkedList<LocalDate> findLargest = new LinkedList<LocalDate>();

		// get class number
		AddClass classInstance = new AddClass();
		int classid = classInstance.classid(cla);

		// build student list file name for class
		String studentFileName = studentFile + "." + classid;

		// access proper student file
		// check if student file exists
		File studentFile = new File(studentFileName);
		if (studentFile.exists() == false) {
			throw new NoStudentInClassException("No student in the class");
		}

		String attendanceFileName = attendanceFile + "." + classid;
		File attendanceFile = new File(attendanceFileName);

		// if not attendance file found, throw exception
		if (attendanceFile.exists() == false) {
			throw new NoAttendanceDataFoundException("No Attendance Data found");
		}

		// scan student file for name
		Scanner studentDataLine = new Scanner(studentFile);
		while (studentDataLine.hasNextLine()) {
			String studentLine = studentDataLine.nextLine();
			Scanner studentParse = new Scanner(studentLine);
			studentParse.useDelimiter(",");
			// get student name and number from student attendance files
			String studentNumber = studentParse.next();
			String studentName = studentParse.next();

			name.add(studentName);

			// scan attendance files for each student in class
			Scanner attendanceDataLine = new Scanner(attendanceFile);

			while (attendanceDataLine.hasNextLine()) {
				String attendanceline = attendanceDataLine.nextLine();
				Scanner attendanceParse = new Scanner(attendanceline);
				attendanceParse.useDelimiter(",");
				String studentNumberInAttendance = attendanceParse.next();
				// if student number in student list and student number in attendance list
				// match, get data from attendance file
				if (studentNumber.equals(studentNumberInAttendance)) {
					String studentDateInAttendance = attendanceParse.next();
					// convert string to LocalDate format
					LocalDate studentDateInAttendanceLocalDate = LocalDate.parse(studentDateInAttendance);
					// put date in findLargest linked list
					findLargest.add(studentDateInAttendanceLocalDate);

				}

			}

			// now find most recent date in findLargest and store for display
			// if findLargest is empty, student has not attended and we put in a date of
			// 1900-12-12. We will replace this date later on with a not attended.
			if (findLargest.isEmpty() == true) {
				date.add(LocalDate.parse("1900-12-12"));
				findLargest.clear();
			} else {
				LocalDate largest = findLargest.get(0);
				for (int index = 1; index < findLargest.size(); index++) {
					if (findLargest.get(index).isAfter(largest)) {
						largest = findLargest.get(index);
					}
				}

				date.add(largest);
				findLargest.clear();
			}
		}

		// now put data in array
		String[][] display = new String[name.size()][2];
		for (int row = 0; row < display.length; row++) {

			display[row][0] = name.get(row);
			if (date.get(row).equals(LocalDate.parse("1900-12-12"))) {
				display[row][1] = notAttendText;
			} else {
				display[row][1] = date.get(row).toString();
			}

		}

		return display;

	}

}
