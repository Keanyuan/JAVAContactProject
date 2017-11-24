package com.anjiplus.gip.tools;

import java.awt.Component;
import java.awt.Toolkit;

public class GUITools {
	public static void center(Component c) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		int x = (kit.getScreenSize().width - c.getWidth()) / 2;
		int y = (kit.getScreenSize().height - c.getHeight()) / 2;
		c.setLocation(x, y);
	}
}
