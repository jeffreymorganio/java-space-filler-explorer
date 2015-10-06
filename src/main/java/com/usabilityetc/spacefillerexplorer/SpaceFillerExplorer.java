package com.usabilityetc.spacefillerexplorer;

import java.io.File;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.usabilityetc.spacefillerexplorer.tree.*;
import com.usabilityetc.spacefillerexplorer.treemap.*;

/**
 * The SpaceFillerExplorer demonstrates the use of the TreemapPanel class.
 * 
 * @author Jeffrey Morgan
 */
public class SpaceFillerExplorer extends JFrame {
	private static final long serialVersionUID = 1L;
	final TreemapPanel treemapPanel = new TreemapPanel();

	public SpaceFillerExplorer(final Tree tree) {
		final Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(buttonPanel(), BorderLayout.NORTH);
		contentPane.add(treemapPanel, BorderLayout.CENTER);
		
		configureWindow();
		treemapPanel.setTree(tree);
	}
	
	private JComponent buttonPanel() {
		final JPanel panel = new JPanel();
		panel.add(homeFolderButton());
		return panel;
	}
	
	private JComponent homeFolderButton() {
		final JButton button = new JButton("Select Folder");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				final File selectedDirectory = selectDirectory();
				if (selectedDirectory != null) {
					final Tree tree = DirectoryTreeBuilder.createDirectoryTree(selectedDirectory);
					treemapPanel.setTree(tree);
				}
			}
			
			private File selectDirectory() {
				File selectedDirectory = null;
				final JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				final int returnValue = fileChooser.showOpenDialog(SpaceFillerExplorer.this);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
				  selectedDirectory = fileChooser.getSelectedFile();
				}
				return selectedDirectory;
			}
		});
		return button;
	}
	
	private void configureWindow() {
		pack();
		setResizable(true);
		setTitle("Space Filler Explorer");
		sizeWindow();
		centerWindow();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void sizeWindow() {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final double screenScaleFactor = 0.5;
		setSize(
			(int)((double)screenSize.width * screenScaleFactor),
			(int)((double)screenSize.height * screenScaleFactor)
		);
	}

	private void centerWindow() {
		final Dimension applicationSize = getSize();
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocationRelativeTo(null);
		setLocation(
			(screenSize.width - applicationSize.width) / 2,
			(screenSize.height - applicationSize.height) / 2
		);
	}

	public static void main(String[] args) {
		new SpaceFillerExplorer(ExampleTreeBuilder.createTree());
	}
}
