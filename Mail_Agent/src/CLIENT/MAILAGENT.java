package CLIENT;

import MAP_SERVER.MAPSERVER;

public class MAILAGENT {
	public static boolean enterMAPSV;
	public static boolean success;

	public static void main(String[] args) {
		Run();
		System.out.println("Mission complete.");
	}

	// run the mail agent which runs MAP protocol
	public static void Run() {
		try {
			GUI window = new GUI();
			window.open();
			window.display.readAndDispatch();

			// wait until user closes the window
			while (!window.shell.isDisposed()) {
				if (!window.display.readAndDispatch()) {
					window.display.sleep();
				}

				if (MAILAGENT.enterMAPSV == true) {

					MAPSERVER.checkPort();
					if (MAILAGENT.enterMAPSV != true)
						continue;

					MAPSERVER server = new MAPSERVER(CLIENT.MAP_CMD);

					server.dnsQuery();
					if (MAILAGENT.enterMAPSV != true)
						continue;

					server.Run();
					if (MAILAGENT.enterMAPSV != true)
						continue;

					CLIENTREPLY.RE_SUCCESS();
					MAILAGENT.enterMAPSV = false;
					continue;
				}
			}

			window.display.close();

		} catch (Exception e) {
			CLIENTREPLY.BREAKDOWN();
			e.printStackTrace();
		}
	}

}
