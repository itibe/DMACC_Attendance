import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 */

/**
 * Adds students to a class room
 * 
 * @author Ian Tibe
 *
 */
public class AddStudent {
	// Ian Tibe
	// data fields
	String studentFile = "StudentList"; // student file name
	int idLength = 9; // student id length
	String dmaccIdStart = "9"; // number that DMACC id must start wtih

	// constructor
	/**
	 * default constructor
	 */
	public AddStudent() {

	}

	// helper method
	/**
	 * Add student to given class
	 * 
	 * @param student
	 *            student name
	 * @param clas
	 *            class name, as text, not class id
	 * @param id
	 *            id number of student
	 * @throws IOException
	 * @throws ClassDoesNotExistException
	 * @throws StudentIdNotFoundException
	 * @throws DuplicateStudentIdException
	 */
	public void addstudent(String student, String clas, String id)
			throws IOException, ClassDoesNotExistException, InvalidDmaccNumberException, DuplicateStudentIdException {

		// check if class exists
		AddClass instance = new AddClass();
		// need to handle class not found
		// put class id in variable to use later
		int classid = instance.classid(clas);

		// build student list file name for class
		String fileName = studentFile + "." + classid;

		// check if student file exists
		File file = new File(fileName);
		if (file.exists() == false) {
			file.createNewFile();
		}
		// add student to existing class file

		if (this.validateid(id) == false) {
			throw new InvalidDmaccNumberException("ID not Valid");
		}

		// check for duplicate id number in student file
		Scanner in = new Scanner(file);

		while (in.hasNextLine()) {

			String line = in.nextLine();
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			String idNum = parse.next();

			if (idNum.equals(id)) {
				parse.close();
				throw new DuplicateStudentIdException("duplicate id");
			}

		}

		// add info to file and close file to flush
		FileWriter writeinfo = new FileWriter(file, true);
		writeinfo.append(id + "," + student);
		writeinfo.append(System.lineSeparator());
		writeinfo.close();

	}

	/**
	 * validates the dmacc id number
	 * 
	 * @param id
	 * @return
	 */
	protected boolean validateid(String id) {
		boolean result = true;
		char letter;
		// check length of id
		if (id.length() == idLength) {

			// check if all characters are digits
			for (int index = 0; index < id.length(); index++) {
				letter = id.charAt(index);
				if (Character.isDigit(letter) == false) {
					result = false;
				}
			}
			// check to see if id starts with proper character
			if (id.substring(0, 1).equals(dmaccIdStart) == false) {
				result = false;
			}
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * returns name, given id number and class
	 * 
	 * @param id
	 *            student id number
	 * @param cla
	 *            class room in text form
	 * @return String of student name
	 * @throws IOException
	 * @throws ClassDoesNotExistException
	 * @throws StudentNotInClassException
	 * @throws StudentIdNotFoundException
	 */
	public String getstudentname(String id, String cla)
			throws IOException, ClassDoesNotExistException, StudentNotInClassException, StudentIdNotFoundException {

		// check if class exists
		AddClass instance = new AddClass();
		// need to handle class not found
		// put class id in variable to use latter
		int classid = instance.classid(cla);

		// build student list file name for class
		String fileName = studentFile + "." + classid;

		// check if student file exists
		File file = new File(fileName);
		if (file.exists() == false) {
			throw new StudentNotInClassException("no student defined for class");
		}

		// get student id
		Scanner in = new Scanner(file);

		String name = null;
		while (in.hasNextLine()) {

			String line = in.nextLine();
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			String idnum = parse.next();
			if (idnum.equals(id)) {
				name = parse.next();
			}
			parse.close();
		}

		if (name == null) {
			in.close();
			throw new StudentIdNotFoundException("ID not found");
		}
		in.close();
		return name;
	}

	/**
	 * returns true if student id is in given class, false otherwise
	 * 
	 * @param id
	 *            student id
	 * @param cla
	 *            class
	 * @return true if student id is in given class, false otherwise
	 * @throws IOException
	 * @throws ClassDoesNotExistException
	 */
	public boolean validatestudentid(String id, String cla) throws IOException, ClassDoesNotExistException {
		boolean result = false;
		// check if class exists
		AddClass instance = new AddClass();
		// need to handle class not found
		// put class id in variable to use latter
		int classid = instance.classid(cla);

		// build student list file name for class
		String fileName = studentFile + "." + classid;

		// check if student file exists
		File file = new File(fileName);
		if (file.exists() == false) {
			file.createNewFile();
			// throw new NoSuchElementException("empty class list");
		}

		// get student id
		Scanner in = new Scanner(file);

		while (in.hasNextLine()) {

			String line = in.nextLine();
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			String idnum = parse.next();
			if (idnum.equals(id)) {
				result = true;
			}
			parse.close();
		}

		return result;

	}

}
