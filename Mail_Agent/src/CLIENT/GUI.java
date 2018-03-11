package CLIENT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class GUI extends CLIENT {

	Shell shell;

	// a reply shell for all MAP reply pop-ups
	public static Shell reply;

	private Text From_text;
	private Text To_text;
	private Text Subject_text;
	private Text Content_text;

	Display display;

	protected static void Run() {

	}

	// create contents of the window
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(550, 400);
		shell.setText("Mail Agent");

		Label From_L = new Label(shell, SWT.NONE);
		From_L.setBounds(25, 7, 55, 25);
		From_L.setText("From");

		Label To_L = new Label(shell, SWT.NONE);
		To_L.setBounds(25, 38, 55, 25);
		To_L.setText("To");

		Label Subject_L = new Label(shell, SWT.NONE);
		Subject_L.setBounds(25, 69, 55, 27);
		Subject_L.setText("Subject");

		From_text = new Text(shell, SWT.BORDER);
		From_text.setBounds(86, 4, 369, 21);

		To_text = new Text(shell, SWT.BORDER);
		To_text.setBounds(86, 35, 369, 21);

		Subject_text = new Text(shell, SWT.BORDER);
		Subject_text.setBounds(86, 66, 369, 21);

		Label Content_L = new Label(shell, SWT.NONE);
		Content_L.setBounds(25, 99, 55, 25);
		Content_L.setText("Content");

		Content_text = new Text(shell, SWT.BORDER);
		Content_text.setBounds(86, 99, 369, 216);

		Button Send_btn = new Button(shell, SWT.NONE);
		Send_btn.setBounds(380, 326, 75, 25);
		Send_btn.setText("Send");

		// waits for the user's click
		Send_btn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				transmissionStart();
			}

		});

	}

	// open the window
	protected void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();

		Monitor primary = display.getPrimaryMonitor();

		Rectangle bounds = primary.getBounds();

		Rectangle rect = shell.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shell.setLocation(x, y);
	}

	// begin MAP procedure when user clicks Send button
	public void transmissionStart() {
		convertCmd();

		if (emptyChecker()) {
			if (formatChecker()) {
				encapsulate();
			} else {
				return;
			}
		} else {
			return;
		}
	}

	// convert received information to corresponding MAP commands
	public void convertCmd() {
		CMD_FROM = From_text.getText();
		CMD_TO = To_text.getText();
		CMD_SUBJECT = Subject_text.getText();
		CMD_CONTENT = Content_text.getText();

	}

}
