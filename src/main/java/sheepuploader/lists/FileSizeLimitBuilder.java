/**
 * 
 */
package sheepuploader.lists;

/**
 * This is a builder for the file size limit.
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class FileSizeLimitBuilder {
	
	private long notLoggedIn;
	private long loggedIn;
	
	/**
	 * 
	 * @param notLoggedIn number of bytes that an anonymous user can upload
	 * @return FileSizeLimitBuilder
	 */
	public FileSizeLimitBuilder setNotLoggedIn(long notLoggedIn){
		this.notLoggedIn = notLoggedIn;
		return this;
	}
	
	/**
	 * 
	 * @param loggedIn
	 * @return FileSizeLimitBuilder
	 */
	public FileSizeLimitBuilder setLoggedIn(long loggedIn){
		this.loggedIn = loggedIn;
		return this;
	}
	
	
	/**
	 * Build the FileSizeLimit object.
	 * @return FileSizeLimit object
	 */
	public FileSizeLimit build(){
		FileSizeLimit fsl = new FileSizeLimit();
		fsl.setNotLoggedIn(notLoggedIn);
		fsl.setLoggedIn(loggedIn);
		return fsl;
	}

}
