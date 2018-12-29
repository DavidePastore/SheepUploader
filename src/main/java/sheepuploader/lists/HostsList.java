/**
 * 
 */
package sheepuploader.lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import sheepuploader.lists.xml.Handler;
import sheepuploader.log.SULogger;

/**
 * This will contain all hosts.
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 * 
 */
public class HostsList {
	
	
	public static List<Host> HostsList = new ArrayList<Host>();
	private static String fileName = "data/supportedHosts.xml";
	private static File xmlFile = new File(fileName);
	private static SAXParserFactory factory = SAXParserFactory.newInstance();
	private static SAXParser saxParser;
	private static Logger logger = SULogger.getLogger();
	
	/**
	 * Non-instantiable
	 */
	private HostsList(){	
	}
	
	public static void loadSupportedHost(){
		
		try {
			saxParser = factory.newSAXParser();
			
			DefaultHandler handler = new Handler();
			
			InputStream inputStream = new FileInputStream(xmlFile);
			Reader reader = new InputStreamReader(inputStream,"UTF-8");
			 
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");
			
			saxParser.parse(is, handler);
			
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
