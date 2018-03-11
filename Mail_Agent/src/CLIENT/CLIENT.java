package CLIENT;

import java.util.ArrayList;
import java.util.List;

public class CLIENT {
	// This list is used to transfer MAP commands from client
	// to the MAP server
	static List<String> MAP_CMD;

	// MAP commands
	protected String CMD_FROM;
	protected String CMD_TO;
	protected String CMD_SUBJECT;
	protected String CMD_CONTENT;

	
	// check if the inputs are empty and generate corresponding MAP reply
	public boolean emptyChecker() {
		StringBuilder tmp = new StringBuilder();

		if (!CMD_FROM.isEmpty() && !CMD_TO.isEmpty() && !CMD_SUBJECT.isEmpty() && !CMD_CONTENT.isEmpty()) {
			return true;
		} else {
			if (CMD_FROM.isEmpty()) {
				tmp.append("From, ");
			}
			if (CMD_TO.isEmpty()) {
				tmp.append("To, ");
			}
			if (CMD_SUBJECT.isEmpty()) {
				tmp.append("Subject, ");
			}
			if (CMD_CONTENT.isEmpty()) {
				tmp.append("Content,");
			}
		}

		CLIENTREPLY.RE_NO_EMPTY(tmp.toString());

		return false;
	}

	// check the format of email address
	public boolean formatChecker() {
		StringBuilder tmp = new StringBuilder();

		if (CMD_FROM.contains("@") && CMD_TO.contains("@")) {
			return true;
		} else {
			if (!CMD_FROM.contains("@")) {
				tmp.append(CMD_FROM + ", ");
			}
			if (!CMD_TO.contains("@")) {
				tmp.append(CMD_TO + ",");
			}
		}

		CLIENTREPLY.RE_INVALID_FORMAT(tmp.toString());

		return false;
	}

	// encapsulate the data received from GUI to the corresponding
	// MAP commands
	public void encapsulate() {
		try {
			MAP_CMD = new ArrayList<>();
			MAP_CMD.add(CMD_FROM);
			MAP_CMD.add(CMD_TO);
			MAP_CMD.add(CMD_SUBJECT);
			MAP_CMD.add(CMD_CONTENT);

			MAILAGENT.enterMAPSV = true;
		} catch (Exception e) {
			System.out.println("error");
		}
	}
}
