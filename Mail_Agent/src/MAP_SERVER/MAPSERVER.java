package MAP_SERVER;

import java.net.Socket;
import java.util.*;

import javax.naming.NamingException;

import CLIENT.MAILAGENT;

public class MAPSERVER extends SMTPprocess{
	private List<String> hostList;

	// store received MAP commands
	public MAPSERVER(List<String> MAP_CMD) {
		From = MAP_CMD.get(0);
		To = MAP_CMD.get(1);
		Subject = MAP_CMD.get(2);
		Content = MAP_CMD.get(3);
	}

	// check if port 25 is available
	public static void checkPort() {
		try {
			Socket socket = new Socket("mx3.qq.com", 25);
			socket.setSoTimeout(5000);
			socket.close();
		} catch (Exception e) {
			SERVERREPLY.RE_PORT_BLOCKED();
		}
	}

	// perform DNS query
	public void dnsQuery() throws NamingException {
		String hostName = To.split("@")[1];
		try {
			hostList = new ArrayList<>();
			for (String mailHost : DNS.lookupMailHosts(hostName)) {
				hostList.add(mailHost);
			}
		} catch (NamingException e) {
			SERVERREPLY.RE_DNS_FAIL(hostName);
		}
	}

	// begin mail transmission
	public void Run() {
		CMD_SMTP_CONN(hostList);
		if (MAILAGENT.enterMAPSV != true)
			return;

		CMD_SMTP_HELO();
		if (MAILAGENT.enterMAPSV != true)
			return;

		CMD_SMTP_FROM();
		if (MAILAGENT.enterMAPSV != true)
			return;
		
		CMD_SMTP_TO();
		if (MAILAGENT.enterMAPSV != true)
			return;

		CMD_SMTP_DATA();
		if (MAILAGENT.enterMAPSV != true)
			return;
		
		CMD_SMTP_QUIT();
		if (MAILAGENT.enterMAPSV != true)
			return;
	}


}
