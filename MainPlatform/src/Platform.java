
import java.awt.Color;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Platform {

	private static final int WINDOW_WIDTH = 1200;
	private static final int WINDOW_HEIGHT = 800;
	protected static JPanel executionPanel;

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Homework 5 Platform");
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		// List Panel
		JPanel listPanel = new JPanel();
		listPanel.setSize(WINDOW_WIDTH / 4, WINDOW_HEIGHT);
		listPanel.setLocation(0, 0);
		listPanel.setBackground(Color.BLUE);

		// Status Panel
		JPanel statusPanel = new JPanel();
		statusPanel.setSize(WINDOW_WIDTH * 3 / 4, WINDOW_HEIGHT / 4);
		statusPanel.setLocation(WINDOW_WIDTH / 4, WINDOW_HEIGHT * 3 / 4);
		statusPanel.setBackground(Color.GREEN);

		// Execution Panel
		executionPanel = new JPanel();
		executionPanel.setSize(WINDOW_WIDTH * 3 / 4, WINDOW_HEIGHT * 3 / 4);
		executionPanel.setLocation(WINDOW_WIDTH / 4, 0);
		executionPanel.setBackground(Color.ORANGE);

		frame.add(listPanel);
		frame.add(statusPanel);
		frame.add(executionPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		Platform platform = new Platform();
		platform.run("HelloPlugin.jar");
	}

	private void run(String fileName) throws Exception {
		File file = new File(fileName);
		
		URL url = file.toURI().toURL();
		URL[] urls = new URL[] { url };
		
		ClassLoader cl = new URLClassLoader(urls);
		
		String className = fileName.substring(0, fileName.indexOf('.'));
		
		Object plugin = cl.loadClass(className).newInstance();
		IPlugin p = (IPlugin) plugin;
		p.start();

	}
}
