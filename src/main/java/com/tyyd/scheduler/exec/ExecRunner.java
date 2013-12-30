package com.tyyd.scheduler.exec;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ExecRunner {
	
	public static final char PARAM_SPLIT = '\u0001';
	
	/** Win NT/2K/MEPro require cmd.exe to run programs * */
	private static final String WINDOWS_NT_2000_COMMAND_1 = "cmd.exe";

	/** Win NT/2K/MEPro require the /C to specify what to run * */
	private static final String WINDOWS_NT_2000_COMMAND_2 = "/C";

	/** Win 9X/MEHome require cmd.exe to run programs * */
	private static final String WINDOWS_9X_ME_COMMAND_1 = "command.exe";

	/** Win 9X/MEHome require the /C to specify what to run * */
	private static final String WINDOWS_9X_ME_COMMAND_2 = "/C";

	/** String to send to STDERR if program exceeds max run time * */
//	private static final String MAX_RUN_TIME_EXCEEDED_STRING = "MAX_RUN_TIME_EXCEEDED";

	/** String to capture STDOUT * */
	private String out = StringUtils.EMPTY;

	/** String to capture STDERR * */
	private String err = StringUtils.EMPTY;

	/** Default max run time (in seconds) * */
	private int maxRunTimeSecs = Integer.MAX_VALUE;

	/** Flag to indicate if we've exceeded max run time * */
	private boolean maxRunTimeExceeded = false;

	/**
	 * Basic ExecRunner constructor.
	 * 
	 */
	public ExecRunner() {
		super();
	}

	/**
	 * ExecRunner constructor which also conveniently runs exec(String).
	 * 
	 * @param command
	 *            The program or command to run
	 * @throws ExceptionInInitializerError
	 *             thrown if a problem occurs
	 */
	public ExecRunner(String command) throws ExceptionInInitializerError {
		this();
		try {
			exec(command);
		} catch (IOException ioe) {
			throw new ExceptionInInitializerError(ioe.getMessage());
		} catch (InterruptedException inte) {
			throw new ExceptionInInitializerError(inte.getMessage());
		}
	}

	/**
	 * We override the <code>clone</code> method here to prevent cloning of our
	 * class.
	 * 
	 * @throws CloneNotSupportedException
	 *             To indicate cloning is not allowed
	 * @return Nothing ever really returned since we throw a
	 *         CloneNotSupportedException
	 */
	public final Object clone() throws CloneNotSupportedException {

		throw new java.lang.CloneNotSupportedException();

	}

	//第4
	public int exec(String command) throws IOException, InterruptedException {

		StringWriter swOut = new StringWriter();
		PrintWriter pwOut = new PrintWriter(swOut, true);

		StringWriter swErr = new StringWriter();
		PrintWriter pwErr = new PrintWriter(swErr, true);

		int rc = exec(command, pwOut, pwErr);

		out = swOut.toString();
		err = swErr.toString();

		return rc;

	}
	
	//第三
	public int exec(String command, OutputStream stdoutStream,
			OutputStream stderrStream) throws IOException, InterruptedException {

		PrintWriter pwOut = new PrintWriter(stdoutStream, true);
		PrintWriter pwErr = new PrintWriter(stderrStream, true);

		return exec(command, pwOut, pwErr);

	}
	
	//第2
	public int exec(String command, PrintWriter stdoutWriter,
			PrintWriter stderrWriter) throws IOException, InterruptedException {

		int exitVal = 1;

		Process proc = getProcess(command);

		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), stdoutWriter);
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), stderrWriter);
		exitVal = exec(proc, outputGobbler, errorGobbler);

		stdoutWriter.flush();
		stderrWriter.flush();
		
		return exitVal;

	}
	
	
	protected int exec(Process proc,Thread stdout,Thread stderr) throws IOException, InterruptedException {

		int exitVal = 1;
//		Date startTime = new Date();
//		long startTimeMs = startTime.getTime();
//		long maxTimeMs = startTimeMs + (maxRunTimeSecs * 1000);

		stdout.start();
		stderr.start();

		while (true) {

			try {
				exitVal = proc.exitValue();
				break;
			} catch (IllegalThreadStateException e) {
				Thread.sleep(10);
//				if (maxRunTimeSecs > 0) {
//
//					Date endTime = new Date();
//					long endTimeMs = endTime.getTime();
//					if (endTimeMs > maxTimeMs) {
//						// Time's up - kill the process and the gobblers and
//						// return
//						proc.destroy();
//						maxRunTimeExceeded = true;
//						stderrWriter.println(MAX_RUN_TIME_EXCEEDED_STRING);
//						outputGobbler.quit();
//						errorGobbler.quit();
//						return exitVal;
//
//					} else {
//						Thread.sleep(POLL_DELAY_MS);
//					}
//
//				}

			}

		}

		while (stdout.isAlive() || stderr.isAlive()) {
			Thread.sleep(10);
		}

		return exitVal;

	}
	
	//第一
	private Process getProcess(String command) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process proc;
		List<String> cmd = new ArrayList<String>(10);

		String osName = System.getProperty("os.name");
		if (osName.equals("Windows NT") || osName.equals("Windows 2000")) {
			cmd.add(WINDOWS_NT_2000_COMMAND_1);
			cmd.add(WINDOWS_NT_2000_COMMAND_2);
		} else if (osName.equals("Windows 95") || osName.equals("Windows 98")
				|| osName.equals("Windows ME")) {
			cmd.add(WINDOWS_9X_ME_COMMAND_1);
			cmd.add(WINDOWS_9X_ME_COMMAND_2);
		} else {
//			cmd.add("/bin/sh");
//			cmd.add("-c");
		}
		
		String[] cms = StringUtils.split(command, PARAM_SPLIT);
		
		if (cms != null && cms.length > 0) {
			for (int i = 0;i < cms.length;i ++) {
				cmd.add(cms[i]);
			}
		}
		// Execute the command and start the two output gobblers
		if (cmd != null && cmd.size() > 0) {
			proc = rt.exec(cmd.toArray(new String[]{}));
		} else {
			throw new IOException("Insufficient commands!");
		}
		
		return proc;
	}
	
	/**
	 * Returns the error string if exec(String) was invoked.
	 * 
	 * @return The error string if exec(String) was invoked.
	 */
	public String getErrString() {
		return err;
	}

	/**
	 * Returns whether the maximum runtime was exceeded or not.
	 * 
	 * @return boolean indicating whether the maximum runtime was exceeded or
	 *         not.
	 */
	public boolean isMaxRunTimeExceeded() {
		return maxRunTimeExceeded;
	}

	/**
	 * Returns the maximum run time in seconds for this object.
	 * 
	 * @return the maximum run time in seconds for this object.
	 */
	public int getMaxRunTimeSecs() {
		return maxRunTimeSecs;
	}

	/**
	 * Returns the output string if exec(String) was invoked.
	 * 
	 * @return The output string if exec(String) was invoked.
	 */
	public String getOutString() {
		return out;
	}

	private final void readObject(ObjectInputStream in) throws IOException {

		throw new IOException("Object cannot be deserialized");

	}

	/**
	 * Sets the maximum run time in seconds. If you do not want to limit the
	 * executable's run time, simply pass in a 0 (which is also the default).
	 * 
	 * @param max
	 *            Maximim number of seconds to let program run
	 */
	public void setMaxRunTimeSecs(int max) {

		maxRunTimeSecs = max;

	}

	private final void writeObject(ObjectOutputStream out) throws IOException {

		throw new IOException("Object cannot be serialized");

	}
}
