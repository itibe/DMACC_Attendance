import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	FileWriter output; // class to write files
	File input; // class to read files
	int nextClassId; // contains next available class id
	Scanner in; // Scanner to parse a line from text file

	// constructors

	/**
	 * Default constructor
	 * @throws IOException
	 */
	public AddClass() throws IOException {
		//puts next available class id number in nextClassId variable
				
		nextClassId = this.getnextid();
	}

	// helper method

	/**
	 * Adds class to file
	 * @param name name of class
	 * @throws IOException
	 */
	public void addclass(String name) throws IOException {
		
		// open file
		output = new FileWriter(classFile, true);

		// append data and new line
		output.append(nextClassId + "," + name);
		output.append(System.lineSeparator());

		// close file
		output.close();
	}

	/**
	 * Scans file containing class list and determines the next available class id number using
	 * data from the file
	 * @return next available class id number, as integer
	 * @throws IOException
	 */
	public int getnextid() throws IOException

	{
		// open files
		input = new File(classFile);
		
		in = new Scanner(input);

		// create linked list to hold class numbers
		LinkedList<Integer> idList = new LinkedList<Integer>();
		String line = "";
		// read each line
		while (in.hasNextLine()) {
			// store next line in file into line variable
			line = in.nextLine();
			System.out.println(line);
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			// get integer part of each line, the first item on the line and put in Linked
			// List
			int number = parse.nextInt();
			System.out.println(number);
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
	
	public int classid(String clas) throws FileNotFoundException
	{
		input = new File(classFile);
		in = new Scanner(input);
		String line = "";
		int classid = 0;
		String Class = "";
		int results = 0;
		while(in.hasNextLine())
		{
			line = in.nextLine();
			Scanner parse = new Scanner(line);
			parse.useDelimiter(",");
			classid = parse.nextInt();
			Class = parse.next();
			
			if(clas.equals(Class))
			{
				results = classid;
			}
		}
		
		in.close();
		
		if(results == 0)
		{
			throw new IllegalArgumentException("Class does not exist");
		}
		return results;
	}
	
	//public String[] classlist()
	//{
		
	//}

}
