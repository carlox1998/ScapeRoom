/*
 * ScapeRoomApp.java
 */

package scaperoom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ScapeRoomApp extends SingleFrameApplication {
    public static Socket servidor;

    private static void serverConnection() throws UnknownHostException, IOException {
        InetAddress dir = InetAddress.getByName("172.26.57.217");
        servidor = new Socket(dir, 9000);
    }
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new ScapeRoomView(this, servidor));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ScapeRoomApp
     */
    public static ScapeRoomApp getApplication() {
        return Application.getInstance(ScapeRoomApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws IOException {
        serverConnection();
        launch(ScapeRoomApp.class, args);
    }
}
