package com.anjiplus.gip.view;

import java.awt.Image;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.anjiplus.gip.tools.GUITools;


public abstract class AbstractShapeDialog extends JDialog {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6773746727837412293L;

	public AbstractShapeDialog(JDialog dialog) {
		super(dialog);
		
	}
	
	protected void initDialog() {
		this.addComponent();
		this.init();
	}
	
	private void init() {
		this.pack();
		GUITools.center(this);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public abstract List<String> getImagePaths();
	
	private void addComponent() {
		List<String> imagePaths = getImagePaths();
		if(imagePaths == null) {
			return;
		}
		JPanel panel = new JPanel();
		this.add(panel);
		for(String imagePath : imagePaths) {
			try {
				Image image = ImageIO.read(new File(imagePath));
				panel.add(new JLabel(new ImageIcon(image)));
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
