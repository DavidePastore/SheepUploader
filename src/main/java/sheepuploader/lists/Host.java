/**
 * 
 */
package sheepuploader.lists;

/**
 * Host.
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Host {
	
	private boolean supported;
	private String name;
	private String address;
	private boolean privateSupport;
	private boolean loginSupport;
	private String TOSAddress;
	private FileSizeLimit fileSizeLimit;
	
	
	/**
	 * GET methods
	 */
	
	/**
	 * Get the support status.
	 * @return the support status.
	 */
	public boolean getSupported(){
		return this.supported;
	}
	
	/**
	 * Get the name.
	 * @return the name.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Get the address.
	 * @return the address.
	 */
	public String getAddress(){
		return this.address;
	}
	
	/**
	 * Get private support.
	 * @return private support.
	 */
	public boolean getPrivateSupport(){
		return this.privateSupport;
	}
	
	/**
	 * Get login support.
	 * @return login support.
	 */
	public boolean getLoginSupport(){
		return this.loginSupport;
	}
	
	/**
	 * Get TOS address.
	 * @return TOS address.
	 */
	public String getTOSAddress(){
		return this.TOSAddress;
	}
	
	/**
	 * Get file size limit.
	 * @return file size limit.
	 */
	public FileSizeLimit getFileSizeLimit(){
		return this.fileSizeLimit;
	}
	
	
	/**
	 * SET methods
	 */
	
	/**
	 * Set supported status.
	 * @param supported supported status
	 */
	public void setSupported(boolean supported){
		this.supported = supported;
	}
	
	/**
	 * Set the name of the host.
	 * @param name the name of the host
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Set the address of the host.
	 * @param address the address of the host
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
	/**
	 * Set the private support.
	 * @param privateSupport the private support
	 */
	public void setPrivateSupport(boolean privateSupport){
		this.privateSupport = privateSupport;
	}
	
	/**
	 * Set the login support.
	 * @param loginSupport the login support
	 */
	public void setLoginSupport(boolean loginSupport){
		this.loginSupport = loginSupport;
	}
	
	/**
	 * Set the TOS address.
	 * @param TOSAddress the TOS address
	 */
	public void setTOSAddress(String TOSAddress){
		this.TOSAddress = TOSAddress;
	}
	
	/**
	 * Set FileSizeLimit object.
	 * @param fileSizeLimit FileSizeLimit object
	 */
	public void setFileSizeLimit(FileSizeLimit fileSizeLimit){
		this.fileSizeLimit = fileSizeLimit;
	}
	
}
