/**
 * 
 */
package sheepuploader.log;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 * The logger.
 */
public class SULogger {
	
	private static Logger logger = Logger.getLogger(SULogger.class);
	private static boolean config = false; //Already configure?
	
	//Non instantiable
    private SULogger() {
    }
    
    public static Logger getLogger(){
    	if(!config){
    		config();
    		config = true;
    	}
    	return logger;
    }
    
    private static void config(){
    	String filePath = "log/log4j.properties";
    	File file = new File(filePath);
    	System.out.println("Path: "+file.getPath());
    	PropertyConfigurator.configure(filePath);
    }

}
