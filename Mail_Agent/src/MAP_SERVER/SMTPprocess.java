package MAP_SERVER;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class SMTPprocess {
	protected Socket socket;
	protected String response;
	protected BufferedReader br;
	protected OutputStream os;
	protected String command;

	protected String From;
	protected String To;
	protected String Subject;
	protected String Content;

	protected SMTPprocess() {

	}

	// establish connection with the remote SMTP server
	protected void CMD_SMTP_CONN(List<String> hostList) {
		try {
			socket = new Socket(hostList.get(0), 25);
			socket.setSoTimeout(5000);
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			os = socket.getOutputStream();
			response = br.readLine();
			System.out.println(response);
			if (!response.startsWith("220")) {
				checkRes(response);
			}
		} catch (Exception e) {
			SERVERREPLY.RE_SERVER_UNREACH();
		}
	}

	// Send HELO command and get server response
	protected void CMD_SMTP_HELO() {
		try {
			command = "HELO x\r\n";
			System.out.print(command);
			os.write(command.getBytes("US-ASCII"));
			response = br.readLine();
			System.out.println(response);
			if (!response.startsWith("250")) {
				checkRes(response);
			}
		} catch (Exception e) {
			SERVERREPLY.RE_SERVER_UNREACH();
		}
	}

	// Send MAIL FROM command
	protected void CMD_SMTP_FROM() {
		try {
			command = "MAIL FROM: <" + From + ">\r\n";
			System.out.print(command);
			os.write(command.getBytes("US-ASCII"));
			response = br.readLine();
			System.out.println(response);
			if (!response.startsWith("250")) {
				checkRes(response);
			}
		} catch (Exception e) {
			SERVERREPLY.RE_SERVER_UNREACH();
		}
	}

	// Send RCPT TO command
	protected void CMD_SMTP_TO() {
		try {
			command = "RCPT TO: <" + To + ">\r\n";
			System.out.print(command);
			os.write(command.getBytes("US-ASCII"));
			response = br.readLine();
			System.out.println(response);

			if (!response.startsWith("250")) {
				checkRes(response);
			}
		} catch (Exception e) {
			SERVERREPLY.RE_SERVER_UNREACH();
		}

	}

	// Send DATA command
	protected void CMD_SMTP_DATA() {
		try {
			command = "DATA\r\n";
			System.out.print(command);
			os.write(command.getBytes("US-ASCII"));
			response = br.readLine();
			System.out.println(response);
			if (!response.startsWith("354")) {
				checkRes(response);
			}
		} catch (Exception e) {
			SERVERREPLY.RE_SERVER_UNREACH();
		}

		try {
			// Send message data.
			os.write(("SUBJECT: " + Subject + "\r\n\r\n").getBytes("US-ASCII"));
			os.write((Content + "\r\n").getBytes("US-ASCII"));
			os.write(".\r\n".getBytes("US-ASCII"));

			response = br.readLine();
			System.out.println(response);
			if (!response.startsWith("250")) {
				checkRes(response);
			}
		} catch (Exception e) {
			SERVERREPLY.RE_SERVER_UNREACH();
		}
	}

	// Send QUIT command
	protected void CMD_SMTP_QUIT() {
		try {

			command = "QUIT\r\n";
			System.out.print(command);
			os.write(command.getBytes("US-ASCII"));
			response = br.readLine();
			System.out.println(response);
		} catch (Exception e) {
			SERVERREPLY.RE_SERVER_UNREACH();
		}
	}

	// check if the service is denied by the remote SMTP server
	protected void checkRes(String response) {
		if (response.startsWith("550")) {
			SERVERREPLY.RE_SERVICE_DENIED();
		} else {
			SERVERREPLY.RE_BADREPLY(response.substring(0, 3));
		}
	}
}
