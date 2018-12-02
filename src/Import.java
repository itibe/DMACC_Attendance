import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class Import {
	
	//Ian Tibe
	//data fields
	String studentFile = "StudentList"; // student file name
	
	
	//constructor
	public Import()
	{
		
	}
	
	//helper
	public void importfile(File fileToImport, String clas) throws IOException, ClassDoesNotExistException, InvalidDmaccNumberException
	{
		//create addstudent instance for later use
		AddStudent studentmethos = new AddStudent();
		
		//setup scanner to read file
		Scanner readImport = new Scanner(fileToImport);
				
		//setup data files to write to
		AddClass instance = new AddClass();
		int classId = instance.classid(clas);
		
		//build file name to write to
		String fileNameToWrite = studentFile + "." + classId;
		File fileToWrite = new File(fileNameToWrite);
		FileWriter writeData = new FileWriter(fileToWrite, true);
		
		// check if student file exists
		if (fileToWrite.exists() == false) {
				fileToWrite.createNewFile();
			}
		
		//loop through import file verify id number and write to data file
		while(readImport.hasNextLine())
		{
			String readLine = readImport.nextLine();
			
			//setup new Scanner to parse line
			Scanner parseInput = new Scanner(readLine);
			parseInput.useDelimiter(",");
			String firstName = parseInput.next();
			String lastName = parseInput.next();
			
			//file contains seperate field for first and last name. We will concatinate to one field 
			String fullName = firstName + " " + lastName;
			
			//next line is added to skip the next field in import file. It is user name and we don't need it
			parseInput.next();
			String idNumber = parseInput.next();
			
			//we now validdate id number from import file. This way we can confirm id number in in correct
			//format and we can identify if wrong file format is used for import
			if(studentmethos.validateid(idNumber) == false)
			{
				parseInput.close();
				throw new InvalidDmaccNumberException("Invalid student id number. Possible wrong file format");
			}
			
			//check for duplicates
			
			
			//now we write data to data files
			writeData.append(idNumber + "," + fullName);
			writeData.append(System.lineSeparator());
			writeData.flush();
			
			
		}
		
		writeData.close();
		readImport.close();
	}

}
