package com.anji.plus.study.javapro;

import java.applet.Applet;
import java.awt.*;

/**
 * @Auther: Kean
 * @Date: 2019/2/24 2:19 PM
 * @Description:
 */

public class HelloWorldApplet extends Applet {
    @Override
    public void paint(Graphics g) {
        g.drawString("hello world", 25, 50);
    }
}
