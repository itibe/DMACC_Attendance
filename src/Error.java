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

	// error messages
	private String one = "One or more inputs are missing. Please check you have provided all necessary information.";

	// constructor
	/**
	 * default constructor
	 */
	public Error() {

	}

	/**
	 * generates error string given error number
	 * 
	 * @param number
	 *            error number
	 * @return String containing error message
	 */
	public String generatemessage(int number) {
		String message = "";
		switch (number) {
		case 1:
			message = one;
			break;
		}
		return message;
	}

}
