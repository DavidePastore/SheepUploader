/**
 * 
 */
package sheepuploader;

import sheepuploader.lists.HostsList;
import sheepuploader.ui.SheepUploaderFrame;

/**
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 * Here starts the application.
 */
public class SheepUploaderMain {
	
	private static String version = "0.0.1";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Here we should control for update the software
		
		//Here we should load all supported host
		HostsList.loadSupportedHost();
		new SheepUploaderFrame(version);
	}

}
