package PSE;

import java.awt.image.BufferedImage;

import models.Block;

public class AudioVisualizer {
	private final int WIDTH, HEIGHT;
	private final int PADDING;
	private final int NUM_EMITTERS;

	private ParticleEmitter[] emitters;

	public void update(){
		for(ParticleEmitter emit : emitters){
			emit.update();
		}
	}

	public AudioVisualizer(int screenWidth, int screenHeight, int padding,
			int numEmitters) {
		WIDTH = screenWidth;
		HEIGHT = screenHeight;
		PADDING = padding;
		NUM_EMITTERS = numEmitters;
		emitters = new ParticleEmitter[NUM_EMITTERS];
	}

	public String getNumParticles() {
		// TODO Auto-generated method stub
		return null;
	}

}
