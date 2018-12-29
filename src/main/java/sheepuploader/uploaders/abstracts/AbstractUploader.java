/**
 * 
 */
package sheepuploader.uploaders.abstracts;

/**
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 * This is an abstract uploader.
 */
public abstract class AbstractUploader implements Runnable {
	
	/**
	 * Non-instantiable class.
	 */
	private AbstractUploader(){
	}
	
	/**
	 * The hostName.
	 */
	protected static String hostName;
	
	/**
	 * The hostAddress.
	 */
	protected static String hostAddress;
	
	/**
	 * Max file size uploadable.
	 */
	protected static long maxFileSize;
	
	/**
	 * Min file size uploadable.
	 */
	protected static long minFileSize;
	
}
