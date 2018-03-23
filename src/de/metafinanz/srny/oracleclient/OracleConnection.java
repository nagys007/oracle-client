package de.metafinanz.srny.oracleclient;

import java.sql.Connection;
import java.sql.DriverManager;

/**
* @throws ClassNotFoundException  
*/

class OracleConnection {
	private String serverName;
	private String portNumber;
    private String sid;
    private String userName;
    private String password;
    private String url;
    private boolean verbose;
    //
    public OracleConnection(String serverName, Integer portNumber, String sid, String userName, String password, boolean verbose) {
    	this.serverName = serverName;
    	this.portNumber = Integer.toString(portNumber);
    	this.sid = sid;
    	//
    	url = "jdbc:oracle:thin:@" + this.serverName + ":" + this.portNumber + ":" + this.sid;
    	//
    	this.userName = userName;
    	this.password = password;
    	//
    	this.verbose = verbose;        
	}
    //
	void check() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
        //connect to database
		System.out.println("using URL "+ url);
    	System.out.println("as user "+ userName +" ...");
    	if ( verbose )
    		System.out.println("and password "+ password +" ...");
        Connection conn = DriverManager.getConnection(url, userName, password);
        //
        boolean reachable = conn.isValid(10);// 10 sec
        if ( reachable )
          System.out.println("INFO: connected");
        else
          System.out.println("ERROR: problems connecting, timout after 10 sec");
    }
}