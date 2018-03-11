package MAP_SERVER;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import CLIENT.GUI;
import CLIENT.MAILAGENT;

public class SERVERREPLY {
	static void RE_DNS_FAIL(String msg) {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_DNS_FAIL: <" + msg + ">.");
		mb.open();
		GUI.reply.close();
		MAILAGENT.enterMAPSV = false;
	}
	
	static void RE_SERVER_UNREACH() {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_SERVER_UNREACH.");
		mb.open();
		GUI.reply.close();
		MAILAGENT.enterMAPSV = false;
	}

	static void RE_PORT_BLOCKED() {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_PORT_BLOCKED.");
		mb.open();
		GUI.reply.close();
		MAILAGENT.enterMAPSV = false;
	}

	static void RE_BADREPLY(String msg) {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_BADREPLY <" + msg + ">.");
		mb.open();
		GUI.reply.close();
		MAILAGENT.enterMAPSV = false;
	}

	static void RE_SERVICE_DENIED() {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_SERVICE_DENIED.");
		mb.open();
		GUI.reply.close();
		MAILAGENT.enterMAPSV = false;
	}

	static void RE_SUCCESS() {
		GUI.reply = new Shell();
		MessageBox mb = new MessageBox(GUI.reply, SWT.OK);
		mb.setText("MAP_REPLY");
		mb.setMessage("RE_SUCCESS.");
		mb.open();
		GUI.reply.close();
		MAILAGENT.enterMAPSV = false;
		MAILAGENT.success = true;
	}
}
