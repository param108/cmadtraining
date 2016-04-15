package user;

public class UserDetails implements java.io.Serializable{  
	/**
	 * Added a version ID
	 */
	private static final long serialVersionUID = 1L;
	private int id;  
	private String name;  
	  
	public UserDetails(){}  
	  
	public void setId(int id){this.id=id;}  
	  
	public int getId(){return id;}  
	  
	public void setName(String name){this.name=name;}  
	  
	public String getName(){return name;}  
	  
}
