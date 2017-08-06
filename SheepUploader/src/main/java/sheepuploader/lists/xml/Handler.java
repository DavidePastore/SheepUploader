/**
 * 
 */
package sheepuploader.lists.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sheepuploader.lists.FileSizeLimit;
import sheepuploader.lists.FileSizeLimitBuilder;
import sheepuploader.lists.Host;
import sheepuploader.lists.HostBuilder;
import sheepuploader.lists.HostsList;
import sheepuploader.log.SULogger;

/**
 * This class will handle the parsing process.
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Handler extends DefaultHandler{
	
	private List<Host> hostList = new ArrayList<Host>();
	private Logger logger = SULogger.getLogger();
	
	//FSL stay for File Size Limit
	private boolean foundSheepUploader = false;
	private boolean foundHosts = false;
	private boolean foundHost = false;
	private boolean foundName = false;
	private boolean foundAddress = false;
	private boolean foundPrivate = false;
	private boolean foundLogin = false;
	private boolean foundTOS = false;
	private boolean foundFSL = false;
	private boolean foundFSLNotLoggedIn = false;
	private boolean foundFSLLoggedIn = false;
	
	/**
	 * Host info.
	 */
	private boolean hostSupport;
	private String hostName;
	private String hostAddress;
	private boolean hostPrivateSupport;
	private boolean hostLoginSupport;
	private String hostTOSAddress;
	private long hostFSL;
	private long hostFSLNotLoggedIn;
	private long hostFSLLoggedIn;
	
	
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {
		if(qName == XMLElements.SHEEP_UPLOADER){
			foundSheepUploader = true;
		}
		
		if(foundSheepUploader){
			if(qName == XMLElements.HOSTS){
				foundHosts = true;
			}
		}
		
		if(foundHosts){
			if(qName == XMLElements.HOST){
				foundHost = true;
				hostSupport = Boolean.parseBoolean(attributes.getValue(XMLElements.SUPPORT));
			}
		}
		
		if(foundHost){
			if(qName == XMLElements.NAME){
				foundName = true;
			}
			
			if(qName == XMLElements.ADDRESS){
				foundAddress = true;
			}
			
			if(qName == XMLElements.PRIVATE){
				foundPrivate = true;
				hostPrivateSupport = Boolean.parseBoolean(attributes.getValue(XMLElements.SUPPORT));
			}
			
			if(qName == XMLElements.LOGIN){
				foundLogin = true;
				hostLoginSupport = Boolean.parseBoolean(attributes.getValue(XMLElements.SUPPORT));
			}
			
			if(qName == XMLElements.TOS){
				foundTOS = true;
				hostTOSAddress = attributes.getValue(XMLElements.ADDRESS);
			}
			
			if(qName == XMLElements.FILE_SIZE_LIMIT){
				foundFSL = true;
			}
		}
		
		if(foundFSL){
			if(qName == XMLElements.FILE_SIZE_LIMIT_NOT_LOGGED_IN){
				foundFSLNotLoggedIn = true;
			}
			
			if(qName == XMLElements.FILE_SIZE_LIMIT_LOGGED_IN){
				foundFSLLoggedIn = true;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		if(qName == XMLElements.SHEEP_UPLOADER){
			foundSheepUploader = false;
		}
		
		if(foundSheepUploader){
			if(qName == XMLElements.HOSTS){
				foundHosts = false;
			}
		}
		
		if(foundHosts){
			if(qName == XMLElements.HOST){
				foundHost = false;
				
				//Create FileSizeLimit object
				FileSizeLimitBuilder fslBuilder = new FileSizeLimitBuilder();
				FileSizeLimit fsl = fslBuilder
					.setLoggedIn(hostFSLLoggedIn)
					.setNotLoggedIn(hostFSLNotLoggedIn)
					.build();
				
				//Create Host object
				HostBuilder hostBuilder = new HostBuilder();
				Host host = hostBuilder
					.setAddress(hostAddress)
					.setFileSizeLimit(fsl)
					.setLoginSupport(hostLoginSupport)
					.setName(hostName)
					.setPrivateSupport(hostPrivateSupport)
					.setSupported(hostSupport)
					.setTOSAddress(hostTOSAddress)
					.build();
				
				HostsList.HostsList.add(host);
				resetHost();
			}
		}
		
		if(foundHost){
			if(qName == XMLElements.NAME){
				foundName = false;
			}
			
			if(qName == XMLElements.ADDRESS){
				foundAddress = false;
			}
			
			if(qName == XMLElements.PRIVATE){
				foundPrivate = false;
			}
			
			if(qName == XMLElements.LOGIN){
				foundLogin = false;
			}
			
			if(qName == XMLElements.TOS){
				foundTOS = false;
			}
			
			if(qName == XMLElements.FILE_SIZE_LIMIT){
				foundFSL = false;
			}
		}
		
		if(foundFSL){
			if(qName == XMLElements.FILE_SIZE_LIMIT_NOT_LOGGED_IN){
				foundFSLNotLoggedIn = false;
			}
			
			if(qName == XMLElements.FILE_SIZE_LIMIT_LOGGED_IN){
				foundFSLLoggedIn = false;
			}
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(foundName){
			logger.debug("Name: "+new String(ch, start, length));
			hostName = new String(ch, start, length);
		}
		
		if(foundAddress){
			logger.debug("Address: "+new String(ch, start, length));
			hostAddress = new String(ch, start, length);
		}
		
		if(foundFSLNotLoggedIn){
			logger.debug("Filesize limit not loggedin: "+new String(ch, start, length));
			hostFSLNotLoggedIn = Long.decode(new String(ch, start, length));
		}
		
		if(foundFSLLoggedIn){
			logger.debug("Filesize limit loggedin: "+new String(ch, start, length));
			hostFSLLoggedIn = Long.decode(new String(ch, start, length));
		}
	}
	
	/**
	 * It will reset all variables of the host.
	 */
	private void resetHost(){
		hostSupport = false;
		hostName = null;
		hostAddress = null;
		hostPrivateSupport = false;
		hostLoginSupport = false;
		hostTOSAddress = null;
		hostFSL = Long.MAX_VALUE;
		hostFSLNotLoggedIn = Long.MAX_VALUE;
		hostFSLLoggedIn = Long.MAX_VALUE;
	}
	

}
