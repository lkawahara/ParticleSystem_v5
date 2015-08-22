package PSE;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

import models.Particle;

//simulate a frequency band with a particle emitter
public class ParticleEmitter {
	
	private static int NUM_EMIT_PARTICLES = 16;
	private static Random rand = new Random();
	
	private float posX, posY;
	private boolean emit = false;
	ArrayList<Particle> particleAL = new ArrayList<Particle>();
	private int[] particleRaster;

	public ParticleEmitter(float posX, float posY, BufferedImage particleImage) {
		this.posX = posX;
		this.posY = posY;
		particleRaster = ((DataBufferInt) particleImage.getRaster()
				.getDataBuffer()).getData();
	}

	// to be called every frame
	public void update() {
		resetParticleRaster();
	}
	
	private void resetParticleRaster() {
		for (int I = 0; I < particleRaster.length; I++) {
			particleRaster[I] = 0;
		}
	}
	// if emit on, emit
	private void tryEmitParticlesAtLocation(int numberSquare, int positionX,
			int positionY) {
		if (emit) {
			for (int x = 0; x <= numberSquare; x++) {
				for (int y = 0; y <= numberSquare; y++) {
					float xPos = (positionX + x - numberSquare / 2);
					float yPos = (positionY + y - numberSquare / 2);
					particleAL
							.add(new Particle(rand, numberSquare, xPos, yPos));
				}
			}
		}
	}

//	private void addParticleIfInOpenSpace(Particle p, float xPos, float yPos) {
//		if (blockArray[(int) ((xPos) / 32)][(int) ((yPos) / 32)].type == 0) { 
//			if (xPos <= WIDTH - 4 && xPos >= 4 && yPos <= HEIGHT - 4
//					&& yPos >= 4) {
//				densityArray[(int) ((xPos + 2) / 32)][(int) ((yPos + 2) / 32)] += 2;
//				particleAL.add(p);
//			}
//		}
//	}

}