package PSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Graviton;
import models.Particle;

//simulate a frequency band with a particle emitter
public class ParticleEmitter {
	
	private static int NUM_EMIT_PARTICLES = 2;
	private static int AGE_THRESHOLD = 100;
	
	private static Random rand = new Random();
	
	private float posX, posY;
	private boolean emit = true;
	private ArrayList<Particle> particleAL = new ArrayList<Particle>();
	private Graviton force;

	public ParticleEmitter(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
		this.force = new Graviton(posX, posY - 200, 0, 10);
	}

	// to be called every frame
	public void update() {
		//add more particles
		tryEmitParticlesAtLocation(NUM_EMIT_PARTICLES, posX, posY);
		//update graviton's yPos based on frequency
		updateGraviton();
		//affect particles with graviton
		updateParticles();
		//clear old
		killOldParticles();
	}
	private void updateGraviton(){
		this.force.setyPos(this.force.getyPos() - 1);
	}
	private void updateParticles(){
		for(Particle p : particleAL){
			p.update(force);
		}
	}
	
	// if emit on, emit
	private void tryEmitParticlesAtLocation(int numberSquare, float positionX,
			float positionY) {
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

	private void killOldParticles(){
		for(Particle p : particleAL){
			if(p.getAge() > AGE_THRESHOLD){
				particleAL.remove(p);
			}
		}
	}
	
	public int getNumParticles() {
		return particleAL.size();
	}

	public Graviton getGraviton() {
		return force;
	}

	public List<Particle> getParticles() {
		return new ArrayList(particleAL);
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