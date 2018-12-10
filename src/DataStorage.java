import java.time.LocalDate;
import java.util.LinkedList;

/**
 * 
 */

/**
 * @author Ian Tibe
 *stores data from files to be displayed
 */
public class DataStorage {
	
	//Ian Tibe
	//data fields
	private String name;
	private String id;
	private LinkedList<String> date;
	
	/**
	 * default constructor
	 */
	public DataStorage()
	{
		date = new LinkedList<String>();
	}

	//getter and setter
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public LinkedList<String> getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LinkedList<String> date) {
		this.date = date;
	}

	
	
	
}
