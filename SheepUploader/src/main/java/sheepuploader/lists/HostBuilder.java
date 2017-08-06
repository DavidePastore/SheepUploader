/**
 * 
 */
package sheepuploader.lists;

/**
 * This is a builder for the host.
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class HostBuilder {
	
	private boolean supported;
	private String name;
	private String address;
	private boolean privateSupport;
	private boolean loginSupport;
	private String TOSAddress;
	private FileSizeLimit fileSizeLimit;
	
	/**
	 * 
	 * @param supported the host is supported?
	 * @return HostBuilder
	 */
	public HostBuilder setSupported(boolean supported){
		this.supported = supported;
		return this;
	}
	
	/**
	 * 
	 * @param name name of the host
	 * @return HostBuilder
	 */
	public HostBuilder setName(String name){
		this.name = name;
		return this;
	}
	
	/**
	 * 
	 * @param address address of the main page of the host
	 * @return HostBuilder
	 */
	public HostBuilder setAddress(String address){
		this.address = address;
		return this;
	}
	
	/**
	 * 
	 * @param privateSupport private file support?
	 * @return HostBuilder
	 */
	public HostBuilder setPrivateSupport(boolean privateSupport){
		this.privateSupport = privateSupport;
		return this;
	}
	
	
	/**
	 * 
	 * @param loginSupport login is supported?
	 * @return HostBuilder
	 */
	public HostBuilder setLoginSupport(boolean loginSupport){
		this.loginSupport = loginSupport;
		return this;
	}
	
	/**
	 * 
	 * @param TOSAddress address of the Terms Of Services
	 * @return HostBuilder
	 */
	public HostBuilder setTOSAddress(String TOSAddress){
		this.TOSAddress = TOSAddress;
		return this;
	}
	
	/**
	 * 
	 * @param fileSizeLimit file size limit
	 * @return HostBuilder
	 */
	public HostBuilder setFileSizeLimit(FileSizeLimit fileSizeLimit){
		this.fileSizeLimit = fileSizeLimit;
		return this;
	}
	
	/**
	 * Build the Host object.
	 * @return Host object
	 */
	public Host build(){
		Host host = new Host();
		host.setAddress(address);
		host.setFileSizeLimit(fileSizeLimit);
		host.setLoginSupport(loginSupport);
		host.setName(name);
		host.setPrivateSupport(privateSupport);
		host.setSupported(supported);
		host.setTOSAddress(TOSAddress);
		return host;
	}

}
