package PSE;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import models.Graviton;
import models.Particle;

//holds all of the particle emitters, which hold their particles' positions
//gets all particles and returns to renderer for drawing
public class AudioVisualizer {
	private final int WIDTH, HEIGHT;
	private final int PADDING;
	private final int NUM_EMITTERS;

	private ParticleEmitter[] emitters;
	private boolean isPaused = false;

	public AudioVisualizer(int screenWidth, int screenHeight, int padding,
			int numEmitters, BufferedImage particleImage) {
		WIDTH = screenWidth;
		HEIGHT = screenHeight;
		PADDING = padding;
		NUM_EMITTERS = numEmitters;
		initializeEmitters(particleImage);
	}
	
	private void initializeEmitters(BufferedImage particleImage){
		emitters = new ParticleEmitter[NUM_EMITTERS];
		
		int availableScreenSpace = WIDTH - (PADDING * 2);
		//divides availableScreenSpace into even sections all of the same index sized width
		int sectionWidth = availableScreenSpace / NUM_EMITTERS;
		int sectionMidpoint = sectionWidth / 2;
		float yPos = 0;//HEIGHT - PADDING;
		
		float xPos = PADDING + sectionMidpoint;
		for(int i = 0; i < emitters.length; i ++, xPos += sectionWidth){
			emitters[i] = new ParticleEmitter(xPos, yPos);
		}
	}
	
	public void update(){
		if(!isPaused)
		{
			for(ParticleEmitter emit : emitters){
				emit.update();
			}	
		}
	}

	public void pause(){
		this.isPaused = true;
	}
	
	public void unPause(){
		this.isPaused = false;
	}
	
	//used for debugPrintout
	public String getNumParticles() {
		int numParticles = 0;
		for(int i = 0; i < emitters.length; i ++){
			numParticles += emitters[i].getNumParticles();
		}
		return String.valueOf(numParticles);
	}

	
	public List<Graviton> getGravitons() {
		List<Graviton> ret = new ArrayList<Graviton>();
		for(ParticleEmitter emit : emitters){
			ret.add(emit.getGraviton());
		}
		return ret;
	}

	public List<Particle> getParticles() {
		List<Particle> ret = new ArrayList<Particle>();
		for(ParticleEmitter emit : emitters){
			ret.addAll(emit.getParticles());
		}
		return ret;	}

}
