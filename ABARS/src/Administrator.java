/**
 * 
 * Author: Alejandro Baza
 * @date 2014-25-11
 * Description: This file contains the methods that provide an Administrator with privileges
 * 
 */

/**
 * 
 * @author Alejandro Baza
 * This class contains the administrator information in order to login as an administrator
 * 
 */

public class Administrator {
	//Set variables
	private String Administratorusername; 
	private String Administratorpassword;

	public Administrator(String Administratorusername, String Administratorpassword){
		this.Administratorusername = Administratorusername;
		this.Administratorpassword = Administratorpassword;
	}
	/**
	 * @author Alejandro Baza
	 * @return Administrator's login username
	 */
	public String getAdministratorusername() {
		return Administratorusername;
	}
	
	/**
	 * @author Alejandro Baza
	 * @return Administrator's login password
	 */
	public String getAdministratorpassword() {
		return Administratorpassword;
	}
}
