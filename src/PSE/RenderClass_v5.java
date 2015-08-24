// sdurant12
// 11/14/2012

package PSE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import models.Block;
import models.Graviton;
import models.Particle;

//- Set up:
//	- static set up of (emi++ers and gravitons)
//		- number
//		- position based on screen size and 
//- Update:
//	- change frequency value randomly
//	- have emi++er only effected by single graviton
//	- change graviton y position based on frequency
public class RenderClass_v5 extends JPanel{

	private final int WIDTH, HEIGHT;
	private long lastTime;
	private boolean pause = false;

	private BufferedImage particleImage;
	private BufferedImage blockImage;
	
	//background stuff
	private int[] blockRaster;
	private int[][] densityArray;
	private Block[][] blockArray;
	private int[] particleRaster;

	//visualizer
	private AudioVisualizer visualizer;
	
	public RenderClass_v5(int W, int H){
		WIDTH = W / 2;
		HEIGHT = H / 2;
		setUpWindow(); 
		initializeImages();
		initializeBackgroundArrays();
		int padding = 10;
		int numFrequencies = 10;
		visualizer = new AudioVisualizer(WIDTH, HEIGHT, padding, numFrequencies, particleImage);
	}
	private void setUpWindow(){
	    setFocusable(true);
		requestFocusInWindow();
		this.setBackground(Color.BLACK);
	}
	private void initializeImages() {
		particleImage = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		particleRaster = ((DataBufferInt) particleImage.getRaster()
				.getDataBuffer()).getData();
		blockImage = new BufferedImage(WIDTH / 4, HEIGHT / 4,
				BufferedImage.TYPE_INT_ARGB);
		blockRaster = ((DataBufferInt) blockImage.getRaster().getDataBuffer())
				.getData();
	}
	private void initializeBackgroundArrays() {
		densityArray = new int[WIDTH / 32 + 1][HEIGHT / 32 + 1];
		blockArray = new Block[WIDTH / 32 + 1][HEIGHT / 32 + 1];
		for (int x_I = 0; x_I < blockArray.length; x_I++) {
			for (int y_I = 0; y_I < blockArray[1].length; y_I++) {
				Block b = new Block();
				b.setBlock(0, 0, 0, 0);
//				if (y_I < 9 && y_I > 2 && x_I > 2 && x_I < 11) {
//					b.type = Block.GRYSTONE;
//				}
//				if (y_I % 2 == 0 && x_I % 2 == 0 && x_I < WIDTH / 32 - 2
//						&& x_I > 2 && y_I < HEIGHT / 32 - 2 && y_I > 2) {
//					b.type = Block.GRYSTONE;
//				}
				blockArray[x_I][y_I] = b;
			}
		}
	}
	

	
	public void update() {
		updateBlockLighting();

		if (!pause) {
			visualizer.update();
			clearLightArray();
		}

		for (int x_I = 0, lightWidth = (int) (WIDTH / 32) + 1; x_I < lightWidth; x_I++) {
			for (int y_I = 0, lightHeight = (int) (HEIGHT / 32) + 1; y_I < lightHeight; y_I++) {
				glow(densityArray[x_I][y_I], x_I, y_I);
			}
		}
		
		resetParticleRaster();
		lastTime = System.currentTimeMillis();

		//TODO THIS is code that handles particle update stuff
		//if the particle is on screen, update the density array, to get good lighting
//		if (xPos <= WIDTH && xPos >= 0 && yPos <= HEIGHT && yPos >= 0) { 
//			densityArray[(int) ((xPos + 2) / 32)][(int) ((yPos + 2) / 32)] -= 2;
//		}
//		// if visible on screen
//		if (xPos <= WIDTH - 64 && xPos >= 64 && yPos <= HEIGHT - 64
//				&& yPos >= 64) { 
//			//if no collision with blocks
////			if (blockArray[(int) ((xPos + xVel) / 32)][(int) ((yPos + yVel) / 32)].type == 0) { 
////				xPos += xVel;
////				yPos += yVel;
////			} else { // if collision, 
////				//percentage of chance 
////				if (rand.nextFloat() <= .1f) {
////					xPos += .5 * xVel;
////					yPos += .5 * yVel;
////				}
////				//a collided particle loses energy, and may die 
////				float Vel = ageIfTooSlow();
////				//handle y block collision
////				if (blockArray[(int) (xPos / 32)][(int) ((yPos + yVel) / 32)].type != 0) {
////					xVel = xVel > 0 ? Vel : -Vel;
////					yVel = rand.nextFloat() - .5f;
////				} else {
////					yPos += yVel;
////				}
////				//handle x block collision
////				if (blockArray[(int) ((xPos + xVel) / 32)][(int) (yPos / 32)].type != 0) {
////					yVel = yVel > 0 ? Vel : -Vel;
////					xVel = rand.nextFloat() - .5f;
////				} else {
////					xPos += xVel;
////				}
////			}
//		} 
		//if in canvas
//		if (xPos <= WIDTH - 4 && xPos >= 4 && yPos <= HEIGHT - 4
//				&& yPos >= 4) { 
//
//			for (int xi = -2; xi < 2; xi++) {
//				for (int yi = -2; yi < 2; yi++) {
//					particleRaster[(int) (xPos + xi + WIDTH
//							* (int) (yPos + yi))] = additiveColor(
//							particleRaster[(int) (xPos + xi + WIDTH
//									* (int) (yPos + yi))], 0xff9f1604); // opaque, with blending
//				}
//			}
//
//			//energy is contained within an area
//			//if particle strays too far from crowd, age faster
//			if (densityArray[(int) ((xPos + 2) / 32)][(int) ((yPos + 2) / 32)] < 400) {
//				age += 400 - densityArray[(int) ((xPos + 2) / 32)][(int) ((yPos + 2) / 32)];
//			}
//			
//			//kill old particles, or update density array
//			if (age > 10000) {
//				particleAL.remove(particle_I);
//			} else {
//				densityArray[(int) ((xPos + 2) / 32)][(int) ((yPos + 2) / 32)] += 2;
//			}
//
//		} else { // if outside of canvas
//			//particle should only live for at most 5 frames
//			age += 200;
//			if (age > 10000) {
//				particleAL.remove(particle_I);
//			}
//		}

	}
	//separates the background into blocks which can be colored to represent lighting 
		private void updateBlockLighting() {
			for (int x_I = 0, lightWidth = (int) (WIDTH / 32); x_I < lightWidth; x_I++) {
				for (int y_I = 0, lightHeight = (int) (HEIGHT / 32); y_I < lightHeight; y_I++) {
					updateBlock(x_I, y_I);
				}
			}
		}
		private void updateBlock(int x, int y) {
			int light = (int) (.1 * blockArray[x][y].light);
			// clamp
			light = (light > 200) ? 200 : ((light < 0) ? 0 : light);

			if (blockArray[x][y].type == 0) {
				for (int x_I = 0; x_I < 8; x_I++) {
					for (int y_I = 0; y_I < 8; y_I++) {
						blockRaster[x * 8 + x_I + (y * 8 + y_I) * WIDTH / 4] = ParticleUtilities.makeIntColor(
								255, light, (int) light / 3, 0);
					}
				}
			} else if (blockArray[x][y].type == Block.GRYSTONE) {

				float leftBright = blockArray[x - 1][y].light + .5f
						* blockArray[x - 2][y].light + .25f
						* blockArray[x - 3][y].light + .125f
						* blockArray[x - 1][y - 1].light + .125f
						* blockArray[x - 1][y + 1].light;
				float rightBright = blockArray[x + 1][y].light + .5f
						* blockArray[x + 2][y].light + .25f
						* blockArray[x + 3][y].light + .125f
						* blockArray[x + 1][y - 1].light + .125f
						* blockArray[x + 1][y + 1].light;
				float upBright = blockArray[x][y - 1].light + .5f
						* blockArray[x][y - 2].light + .25f
						* blockArray[x][y - 3].light + .125f
						* blockArray[x - 1][y - 1].light + .125f
						* blockArray[x + 1][y - 1].light;
				float downBright = blockArray[x][y + 1].light + .5f
						* blockArray[x][y + 2].light + .25f
						* blockArray[x][y + 3].light + .125f
						* blockArray[x - 1][y + 1].light + .125f
						* blockArray[x - 1][y + 1].light;

				for (int x_I = 0; x_I < 8; x_I++) {
					for (int y_I = 0; y_I < 8; y_I++) {
						int normal = Block.normalIndentMap[y_I][x_I];
						int bright = 0;
						bright += leftBright * ((0xff000000 & normal) >>> 24)
								/ 0xff;
						bright += rightBright * ((0x00ff0000 & normal) >> 16)
								/ 0xff;
						bright += upBright * ((0x0000ff00 & normal) >> 8) / 0xff;
						bright += downBright * (0x000000ff & normal) / 0xff;

						blockRaster[x * 8 + x_I + (y * 8 + y_I) * WIDTH / 4] = ParticleUtilities.additiveColor(
								Block.GRYSTONEINDENT_TEX[y_I][x_I],
								ParticleUtilities.makeIntColor(255, bright / 12, bright / 24,
										bright / 64));
					}
				}
			}
		}
		private void glow(int light, int x, int y) {

			if (light > lightCap) {
				light = lightCap;
			}

			if (blockArray[x][y].type == 0) {
				if (blockArray[x][y].light <= light) {
					blockArray[x][y].light = light;
				}
				/* if light <= 1 || location invalid*/
				if (light <= lightCutoff || x <= 0 || x >= (int) (WIDTH / 32)
						|| y <= 0 || y >= (int) (HEIGHT / 32)) {
					// base case; do nothing
				} else {
					float lightAttenuation = .8f;
					if (light > lightCutoff + blockArray[x + 1][y].light) {
						glow((int) (light * lightAttenuation), x + 1, y);
					}
					if (light > lightCutoff + blockArray[x - 1][y].light) {
						glow((int) (light * lightAttenuation), x - 1, y);
					}
					if (light > lightCutoff + blockArray[x][y + 1].light) {
						glow((int) (light * lightAttenuation), x, y + 1);
					}
					if (light > lightCutoff + blockArray[x][y - 1].light) {
						glow((int) (light * lightAttenuation), x, y - 1);
					}
				}
			} else {
				if (light <= lightCutoff || x <= 0 || x >= (int) (WIDTH / 32)
						|| y <= 0 || y >= (int) (HEIGHT / 32)) {
					// base case; do nothing
				} else {
					float lightAttenuation = .3f;
					if (light > lightCutoff + blockArray[x + 1][y].light) {
						glow((int) (light * lightAttenuation), x + 1, y);
					}
					if (light > lightCutoff + blockArray[x - 1][y].light) {
						glow((int) (light * lightAttenuation), x - 1, y);
					}
					if (light > lightCutoff + blockArray[x][y + 1].light) {
						glow((int) (light * lightAttenuation), x, y + 1);
					}
					if (light > lightCutoff + blockArray[x][y - 1].light) {
						glow((int) (light * lightAttenuation), x, y - 1);
					}
				}
			}

		}
		private void clearLightArray() {
			for (int x_I = 0, lightWidth = (int) (WIDTH / 32) + 1; x_I < lightWidth; x_I++) {
				for (int y_I = 0, lightHeight = (int) (HEIGHT / 32) + 1; y_I < lightHeight; y_I++) {
					blockArray[x_I][y_I].light = (int) (.9 * blockArray[x_I][y_I].light);
				}
			}
		}
		private void resetParticleRaster() {
			for (int I = 0; I < particleRaster.length; I++) {
				particleRaster[I] = 0;
			}
		}
	
	int lightCutoff = 90;
	int lightCap = 2048;
	//render components on screen, get particles from the emitters and draw them on screen
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(blockImage, 0, 0, 2 * WIDTH, 2 * HEIGHT, null);
		g.drawImage(particleImage, 0, 0, 2 * WIDTH, 2 * HEIGHT, null);
		g.setColor(Color.WHITE);
		drawDebugScreenData(g);
		drawGravitons(g);
		drawParticles(g);
	}
		private void drawDebugScreenData(Graphics g){
			g.drawString("Framerate:"
					+ (1000 / (System.currentTimeMillis() - lastTime)), 5, 15);
			g.drawString("Particles : " + visualizer.getNumParticles(), 5, 28);
		}
		private void drawGravitons(Graphics g){
			for(Graviton grav : visualizer.getGravitons()){
				g.fillRect((int) grav.getxPos() * 2, 0, 2, 16);
				g.fillRect(0, (int) grav.getyPos() * 2, 16, 2);
			}
		}
		private void drawParticles(Graphics g){
			for(Particle p : visualizer.getParticles()){
				g.fillRect((int) p.getxPos() * 2, (int) p.getyPos() * 2, 1, 1);
			}
		}
	
}