package CLIENT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class CLIENTREPLY {
	static void RE_NO_EMPTY(String msg) {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_NO_EMPTY: <" + msg + ">.");
		mb.open();
		GUI.reply.close();
	}

	static void RE_INVALID_FORMAT(String msg) {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_INVALID_FORMAT <" + msg + ">.");
		mb.open();
		GUI.reply.close();
	}

	static void RE_SUCCESS() {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_SUCCESS.");
		mb.open();
		GUI.reply.close();
	}

	static void BREAKDOWN() {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("ERROR");
		mb.setMessage("Sorry, client is broken.");
		mb.open();
		GUI.reply.close();
	}
}