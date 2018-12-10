import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Ian Tibe
 *
 */

public class AddClass {
	// Ian Tibe
	// data fields
	String classFile = "classList"; // name of output file
	int nextClassId; // contains next available class id

	// constructors
	/**
	 * Default constructor
	 * 
	 * @throws IOException
	 */
	public AddClass() throws IOException {
		// puts next available class id number in nextClassId variable

		File file = new File(classFile);

		if (file.exists() && file.length() != 0) {
			nextClassId = this.getnextid();
		} else if (file.exists() && file.length() == 0)

		{
			nextClassId = 1;
		} else {
			file.createNewFile();
			nextClassId = 1;
		}

	}

	// helper method

	/**
	 * Adds class to file
	 * 
	 * @param name
	 *            name of class
	 * @throws IOException
	 * @throws DuplicateClassException
	 */
	public void addclass(String name) throws IOException, DuplicateClassException {

		// open file
		FileWriter output = new FileWriter(classFile, true);

		// check for duplicate class
		File file = new File(classFile);
		Scanner in = new Scanner(file);

		while (in.hasNextLine()) {
			String line = in.nextLine();
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			String id = parse.next();
			String nameOfClass = parse.next();
			if (nameOfClass.equals(name)) {
				parse.close();
				in.close();
				throw new DuplicateClassException("Duplicate class");
			}
			
		
		}
		in.close();
		
		// append data and new line
		output.append(nextClassId + "," + name);
		output.append(System.lineSeparator());

		// close file
		output.close();
	}

	/**
	 * Scans file containing class list and determines the next available class id
	 * number using data from the file
	 * 
	 * @return next available class id number, as integer
	 * @throws IOException
	 */
	private int getnextid() throws IOException

	{
		// open files
		File input = new File(classFile);

		Scanner in = new Scanner(input);

		// create linked list to hold class numbers
		LinkedList<Integer> idList = new LinkedList<Integer>();
		String line = "";
		// read each line
		while (in.hasNextLine()) {
			// store next line in file into line variable
			line = in.nextLine();
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			// get integer part of each line, the first item on the line and put in Linked
			// List
			int number = parse.nextInt();
			idList.add(number);

		}

		// find largest number in list of class numbers in linked list
		int largest = idList.get(0);
		for (int index = 1; index < idList.size(); index++) {
			if (idList.get(index) > largest) {
				largest = idList.get(index);
			}
		}
		in.close();
		// return one plus highest class number to assgin to next class
		return largest + 1;
	}

	/**
	 * returns class id number, given string of class
	 * 
	 * @param clas
	 *            text of class
	 * @return class id number
	 * @throws FileNotFoundException
	 * @throws ClassDoesNotExistException
	 */
	public int classid(String clas) throws FileNotFoundException, ClassDoesNotExistException {
		File input = new File(classFile);
		Scanner in = new Scanner(input);
		String line = "";
		int classid = 0;
		String Class = "";
		int results = 0;
		// loop through file and get lines
		while (in.hasNextLine()) {
			line = in.nextLine();
			// parse lines and get needed info from lines
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			classid = parse.nextInt();
			Class = parse.next();

			// if input class equals class found in file, we have match
			if (clas.equals(Class)) {
				// set classid number to output in results
				results = classid;
			}
			parse.close();
		}

		in.close();

		if (results == 0) {
			throw new ClassDoesNotExistException("Class does not exist");
		}
		return results;
	}

	/**
	 * returns a string of available class rooms
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public String[] getclasslist() throws FileNotFoundException {
		File file = new File(classFile);
		Scanner in = new Scanner(file);
		LinkedList<String> list = new LinkedList<String>();
		while (in.hasNextLine()) {
			String line = in.nextLine();
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			String id = parse.next();
			String nameOfClass = parse.next();
			list.add(nameOfClass);

		}
		// get size of linkedlist and create corresponding array.
		// we need it in form of array for easy input into combo box
		String[] stringClassList = new String[list.size()];
		for (int index = 0; index < list.size(); index++) {
			stringClassList[index] = list.get(index);
		}

		return stringClassList;
	}

}
