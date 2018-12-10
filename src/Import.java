import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class Import {

	// Ian Tibe
	// data fields
	private String studentFile = "StudentList"; // student file name
	private boolean duplicateNumber = false;

	private int imported = 0;	//number of files imported
	private int notImported = 0;	//number of files not imported

	// constructor
	/**
	 * default constructor
	 */
	public Import() {

	}

	// getter
	/**
	 * @return the imported
	 */
	public int getImported() {
		return imported;
	}

	/**
	 * @return the notImported
	 */
	public int getNotImported() {
		return notImported;
	}

	// helper
	/**
	 * imports file
	 * 
	 * @param fileToImport
	 *            file to import
	 * @param clas
	 *            class name
	 * @throws IOException
	 * @throws ClassDoesNotExistException
	 * @throws InvalidDmaccNumberException
	 */
	public void importfile(File fileToImport, String clas)
			throws IOException, ClassDoesNotExistException, InvalidDmaccNumberException {
		// create addstudent instance for later use
		AddStudent studentmethos = new AddStudent();

		// setup scanner to read import file
		Scanner readImport = new Scanner(fileToImport);

		// setup data files to write to
		AddClass instance = new AddClass();
		int classId = instance.classid(clas);

		// build file name to write to
		String fileNameToWrite = studentFile + "." + classId;
		File fileToWrite = new File(fileNameToWrite);
		FileWriter writeData = new FileWriter(fileToWrite, true);

		// check if student file exists
		if (fileToWrite.exists() == false) {
			fileToWrite.createNewFile();
		}

		// set up Scanner to read data from student file to check for duplicates
		Scanner readStudentFile = new Scanner(fileToWrite);
		LinkedList<String> studentNumbers = new LinkedList<String>();

		// put current student numbers in lined list to compare to imported ones
		while (readStudentFile.hasNextLine()) {
			String lineinStudentFile = readStudentFile.nextLine();
			Scanner parseLineinStudentFile = new Scanner(lineinStudentFile);
			parseLineinStudentFile.useDelimiter(",");
			String input = parseLineinStudentFile.next();
			studentNumbers.add(input);
		}
		readStudentFile.close();
		// loop through import file verify id number and write to data file
		while (readImport.hasNextLine()) {
			String readLine = readImport.nextLine();

			// setup new Scanner to parse line
			Scanner parseInput = new Scanner(readLine);
			parseInput.useDelimiter(",");
			String firstName = parseInput.next();
			String lastName = parseInput.next();

			// file contains seperate field for first and last name. We will concatinate to
			// one field
			String fullName = firstName + " " + lastName;

			// next line is added to skip the next field in import file. It is user name and
			// we don't need it
			parseInput.next();
			String idNumber = parseInput.next();

			// we now validdate id number from import file. This way we can confirm id
			// number in in correct
			// format and we can identify if wrong file format is used for import
			if (studentmethos.validateid(idNumber) == false) {
				parseInput.close();
				throw new InvalidDmaccNumberException("Invalid student id number. Possible wrong file format");
			}

			for (int index = 0; index < studentNumbers.size(); index++) {
				if (idNumber.equals(studentNumbers.get(index))) {
					duplicateNumber = true;
					notImported++;
				}
			}

			if (duplicateNumber == false) {
				imported++;
				writeData.append(idNumber + "," + fullName);
				writeData.append(System.lineSeparator());
				writeData.flush();
			}
			duplicateNumber = false;

		}

		writeData.close();
		readImport.close();
	}

}
