/**
 * 
 */
package sheepuploader.log;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;


/**
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 * The logger.
 */
public class SULogger {
	
	private static Logger logger = LogManager.getLogger(SULogger.class);
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
		System.out.println("File URI: " + file.toURI());
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		context.setConfigLocation(file.toURI());
    }

}
