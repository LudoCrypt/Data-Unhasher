package main.java.net.ludocrypt.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.java.net.ludocrypt.data.Indexer;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {

	public File assets;
	public File index;
	public File output;

	public Text consoleText = new Text("");

	public Indexer indexer = new Indexer(consoleText);

	JFrame frame;
	JTextArea console;
	JButton assetsButton = new JButton();
	JButton indexButton = new JButton();
	JButton outputAndRunButton = new JButton();
	JPanel panel;

	JFrame AssetsImageFrame;
	JFrame IndexImageFrame;
	JFrame OutputImageFrame;

	Font comicSans = new Font("Comic Sans MS", Font.BOLD, 15);
	Font comicSansSmaller = new Font("Comic Sans MS", Font.BOLD, 5);
	ImageIcon assetsIcon = new ImageIcon(new File("src/main/resources/assets.png").getAbsolutePath());
	ImageIcon indexIcon = new ImageIcon(new File("src/main/resources/index.png").getAbsolutePath());
	ImageIcon outputIcon = new ImageIcon(new File("src/main/resources/output.png").getAbsolutePath());
	ImageIcon backgroundIcon = new ImageIcon(new File("src/main/resources/background.png").getAbsolutePath());
	ImageIcon assetsTutorialIcon = new ImageIcon(new File("src/main/resources/tutorial_assets.png").getAbsolutePath());
	ImageIcon indexTutorialIcon = new ImageIcon(new File("src/main/resources/tutorial_index.png").getAbsolutePath());
	ImageIcon outputTutorialIcon = new ImageIcon(new File("src/main/resources/tutorial_output.png").getAbsolutePath());

	public GUI() {

		frame = new JFrame("Data Unhasher");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x - 250, GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y - 150);
		frame.setLayout(null);
		frame.setContentPane(new ImagePanel(backgroundIcon.getImage()));

		console = new JTextArea();
		console.setBounds(62, 66, 376, 180);
		console.setFont(comicSans);
		console.setEditable(false);
		console.setLineWrap(true);
		console.setWrapStyleWord(true);
		console.setText("Console. Libraries used: commons-io-2.8.0, commons-lang3-3.5, gson-2.8.0, guava-25.1-jre");

		assetsButton = new JButton("Assets");
		indexButton = new JButton("Index");
		outputAndRunButton = new JButton("Output");

		indexButton.addActionListener(this);
		indexButton.setIcon(indexIcon);
		indexButton.setFocusable(false);
		indexButton.setBounds(62, 33, 50, 25);

		assetsButton.addActionListener(this);
		assetsButton.setIcon(assetsIcon);
		assetsButton.setFocusable(false);
		assetsButton.setBounds(219, 33, 50, 25);

		outputAndRunButton.addActionListener(this);
		outputAndRunButton.setIcon(outputIcon);
		outputAndRunButton.setFocusable(false);
		outputAndRunButton.setBounds(388, 33, 50, 25);

		frame.add(assetsButton);
		frame.add(indexButton);
		frame.add(outputAndRunButton);
		frame.add(console);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == indexButton) {

			IndexImageFrame = new JFrame("Index Tutorial");
			IndexImageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			IndexImageFrame.setSize(500, 350);
			IndexImageFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x - 700, GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y - 150);
			IndexImageFrame.setLayout(null);
			IndexImageFrame.setContentPane(new JLabel(indexTutorialIcon));
			IndexImageFrame.setVisible(true);

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choose Index.json");
			fileChooser.setCurrentDirectory(new File("."));
			int response = fileChooser.showOpenDialog(this);
			if (response == JFileChooser.APPROVE_OPTION) {
				index = fileChooser.getSelectedFile();
				consoleText.addLog("Chosen Index.json at " + index.getAbsolutePath());
			}

			IndexImageFrame.dispose();

		} else if (e.getSource() == assetsButton) {

			AssetsImageFrame = new JFrame("Index Tutorial");
			AssetsImageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			AssetsImageFrame.setSize(350, 100);
			AssetsImageFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x - 700, GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y - 150);
			AssetsImageFrame.setLayout(null);
			AssetsImageFrame.setContentPane(new JLabel(assetsTutorialIcon));
			AssetsImageFrame.setVisible(true);

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choose Assets Folder");
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showOpenDialog(this);
			if (response == JFileChooser.APPROVE_OPTION) {
				assets = fileChooser.getSelectedFile();
				consoleText.addLog("Chosen assets folder at " + assets.getAbsolutePath());
			}

			AssetsImageFrame.dispose();

		} else if (e.getSource() == outputAndRunButton) {

			OutputImageFrame = new JFrame("Index Tutorial");
			OutputImageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			OutputImageFrame.setSize(150, 75);
			OutputImageFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x - 500, GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y - 150);
			OutputImageFrame.setLayout(null);
			OutputImageFrame.setContentPane(new JLabel(outputTutorialIcon));
			OutputImageFrame.setVisible(true);

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choose Output Folder & run");
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showOpenDialog(this);
			if (response == JFileChooser.APPROVE_OPTION) {
				output = fileChooser.getSelectedFile();
			}

			OutputImageFrame.dispose();

			if (assets == null) {
				consoleText.addLog("Assets folder not chosen");
			}
			if (index == null) {
				consoleText.addLog("Index not chosen");
			}
			if (output == null) {
				consoleText.addLog("No output destination");
			}
			if (assets != null && index != null && output != null) {
				this.indexer.run(assets, index, output);
			}
		}
		if (!consoleText.toString().equals("")) {
			System.out.println(consoleText);
			console.setText(consoleText.toString());
			consoleText.clear();
		}
	}

	private class ImagePanel extends JComponent {
		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int x = 0; x < 30; x++) {
				for (int y = 0; y < 30; y++) {
					g.drawImage(image, x * 256, y * 256, this);
				}
			}
		}
	}

}
