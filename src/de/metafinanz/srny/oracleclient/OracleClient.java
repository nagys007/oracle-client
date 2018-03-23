package de.metafinanz.srny.oracleclient;

import java.util.Arrays;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class OracleClient {


	private static final String DEFAULT_HOST = "localhost";
	private static final String DEFAULT_PORT = "1521";
	private static final String DEFAULT_SID = "orcl";
	private static final String DEFAULT_USERNAME = "scott";
	private static final String DEFAULT_PASSWORD = "tiger";
	
	private static final String HOST_OPTION = "host";
	private static final String PORT_OPTION = "port";
	private static final String SID_OPTION = "sid";
	private static final String USERNAME_OPTION = "username";
	private static final String PASSWORD_OPTION = "password";
	private static final String VERBOSE_OPTION = "verbose";

	private Options options = new Options();

	private static Options defineOptions() {
		final Option hostOption = Option.builder("h").required(false).hasArg(true).longOpt(HOST_OPTION)
				.desc("host as reachable IP or (resolvable) hostname, default is localhost").build();
		final Option portOption = Option.builder("p").required(false).hasArg(true).longOpt(PORT_OPTION)
				.desc("port number, default is 1521").build();
		final Option sidOption = Option.builder("p").required(false).hasArg(true).longOpt(SID_OPTION)
				.desc("Oracle SID, default is orcl").build();
		final Option usernameOption = Option.builder("p").required(false).hasArg(true).longOpt(USERNAME_OPTION)
				.desc("database user name, default is scott").build();
		final Option passwordOption = Option.builder("p").required(false).hasArg(true).longOpt(PASSWORD_OPTION)
				.desc("password of the user, default is tiger").build();
		final Option verboseOption = Option.builder("v").required(false).hasArg(false).longOpt(VERBOSE_OPTION)
				.desc("show progress with verbosity").build();
		final Options options = new Options();
		options.addOption(hostOption);
		options.addOption(portOption);
		options.addOption(sidOption);
		options.addOption(usernameOption);
		options.addOption(passwordOption);
		options.addOption(verboseOption);
		return options;
	}

	private static CommandLine parseArgs(final Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		CommandLine commandLine = null;
		try {
			commandLine = parser.parse(options, args);
		} catch (ParseException parseException) {
			System.out.println("ERROR: Unable to parse command-line arguments " + Arrays.toString(args) + " due to: "
					+ parseException);
		}
		return commandLine;

	}

	public static void main(String args[]) throws ClassNotFoundException {

		final Options options = defineOptions();

		final CommandLine commandLine = parseArgs(options, args);

		final String serverName = commandLine.getOptionValue(HOST_OPTION, DEFAULT_HOST);
		final Integer portNumber = Integer.valueOf(commandLine.getOptionValue(PORT_OPTION, DEFAULT_PORT));
		final String sid = commandLine.getOptionValue(SID_OPTION, DEFAULT_SID);
		final String userName = commandLine.getOptionValue(USERNAME_OPTION, DEFAULT_USERNAME);
		final String password = commandLine.getOptionValue(PASSWORD_OPTION, DEFAULT_PASSWORD);
		
		final boolean verbose = commandLine.hasOption(VERBOSE_OPTION);
		System.out.println("verbosity is set to '" + verbose + "'.");

		OracleConnection oracleConnection = new OracleConnection(serverName, portNumber, sid, userName, password, verbose);
		System.out.println("Connecting to Oracle ...");
		try {
			oracleConnection.check();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
