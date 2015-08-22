// sdurant12
// 11/14/2012

package PSE;

import java.awt.Canvas;
import javax.swing.*;

public class ParticleSystem_v5 extends Canvas {

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1200;
	public static final int TICK = 33;

	public static void main(String[] args) {
		final RenderClass_v5 renderer = new RenderClass_v5(WIDTH, HEIGHT);
		setUpFrame(renderer);
		runProgram(renderer);
	}
	
	public static void setUpFrame(final RenderClass_v5 renderer){
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.add(renderer);
		frame.setVisible(true);
	}
	
	public static void runProgram(final RenderClass_v5 renderer){
		Thread runThread = new Thread(new Runnable() {
			public void run() {
				if (true) {
					while (true) {
						long time = System.currentTimeMillis();
						renderer.update();
						renderer.repaint();
						long endtime = System.currentTimeMillis();
						try {
							Thread.sleep(TICK - (endtime - time));
						} catch (Exception e) {
							System.out.println("Exception e at Thread.sleep");
						}
					}
				}
			}
		});
		runThread.start();
	}
}