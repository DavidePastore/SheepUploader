/**
 * 
 */
package sheepuploader.lists;

/**
 * File size limit.
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class FileSizeLimit {
	
	private long notLoggedIn;
	private long loggedIn;
	
	/**
	 * GET methods
	 */
	
	/**
	 * Get the number of bytes that an anonymous user can upload.
	 * @return number of bytes that an anonymous user can upload
	 */
	public long getNotLoggedIn(){
		return this.notLoggedIn;
	}
	
	/**
	 * Get the number of bytes that a logged user can upload.
	 * @return number of bytes that a logged user can upload
	 */
	public long getLoggedIn(){
		return this.loggedIn;
	}
	
	
	/**
	 * SET methods
	 */
	
	/**
	 * Set the number of bytes that an anonymous user can upload.
	 * @param notLoggedIn number of bytes that an anonymous user can upload
	 */
	public void setNotLoggedIn(long notLoggedIn){
		this.notLoggedIn = notLoggedIn;
	}
	
	
	/**
	 * Set the number of bytes that a logged user can upload.
	 * @param notLoggedIn number of bytes that a logged user can upload
	 */
	public void setLoggedIn(long loggedIn){
		this.loggedIn = loggedIn;
	}

}
