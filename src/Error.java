import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * Generates error message from error number
 * 
 * @author Ian Tibe
 *
 */
public class Error {
	// Ian Tibe
	// data fields
	Map<Integer,String> map;

	// error messages
	
	private String one = "General File I/O error. Users who manually edit data storage file will receive this error";
	private String two = "Class Allready exists";
	private String three = "Unable to generate class list";
	private String four = "Class does not exist";
	private String five = "Student allready defined in class";
	private String six = "Class list empty";
	private String seven = "Encryption Error";
	private String eight = "Missing password file";
	private String nine = "Student ID not found";
	private String ten = "Missing Password";
	private String eleven = "Incorrect Old Password. Please re-enter Old Password";
	private String twelve = "Password is too short. Password must be atleast 8 characters long";
	private String thirteen = "Password Allready exists";
	private String fourteen = "Incorrrect Password. Please re-enter";
	private String fifteen = "Invalid Dmacc number";
	private String sixteen = "User must agree to terms and check box";
	private String seveenteen = "Student not defined in selected class";
	private String eighteen = "Student has all ready entered attendance for above class";
	private String ninteen = "No student are in selected class";
	private String twenty = "No attendance data found for class";

	// constructor
	/**
	 * default constructor
	 */
	public Error() {
		 map = new HashMap<Integer,String>();
		 map.put(1,one);
		 map.put(2,two);
		 map.put(3, three);
		 map.put(4, four);
		 map.put(5, five);
		 map.put(6, six);
		 map.put(7, seven);
		 map.put(8, eight);
		 map.put(9, nine);
		 map.put(10, ten);
		 map.put(11, eleven);
		 map.put(12, twelve);
		 map.put(13, thirteen);
		 map.put(14, fourteen);
		 map.put(15, fifteen);
		 map.put(16, sixteen);
		 map.put(17, seveenteen);
		 map.put(18, eighteen);
		 map.put(19, ninteen);
		 map.put(20, twenty);
				
	}

	/**
	 * generates error string given error number
	 * 
	 * @param number
	 *            error number
	 * @return String containing error message
	 */
	public String generatemessage(int number) {
		String message = map.get(number);
		return message;
	}

}
