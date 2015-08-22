// sdurant12
// 11/14/2012

package models;

import java.util.Random;

import PSE.ParticleUtilities;

public class Particle {

	private static Random rand = new Random();
	private float xPos, yPos, xVel, yVel;
	private float pxVel, pyVel;
	private int age = 0;

	public Particle(float xPos, float yPos, float xVel, float yVel) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
	}

	public Particle(Random r, int numberSquare, float xPos, float yPos) {
		float Vel = r.nextFloat();
		double angle = r.nextDouble() * Math.PI * 2;
		float xVel = Vel * (float) Math.cos(angle);
		float yVel = Vel * (float) Math.sin(angle);
		resetParticle(xPos, yPos, xVel, yVel);
	}

	
	private void affectVelocitybyGraviton(Graviton force){
		float ClickToX, ClickToY;

		ClickToX = force.getxPos() - xPos;
		ClickToY = force.getyPos() - yPos;
		float xPull = force.getxPull();
		float yPull = force.getyPull();

		float InvClickToP = ParticleUtilities.InvSqrt((ClickToX * ClickToX + ClickToY
				* ClickToY));

		xVel += xPull * ClickToX * InvClickToP;
		yVel += yPull * ClickToY * InvClickToP;
	}
	
	private float ageIfTooSlow(){
		float Vel = (float) Math.sqrt(xVel * xVel + yVel * yVel);
		//particle is moving too slow, kill it
		if (Vel < 1) {
			age += 100000;
		}
		return Vel;
	}
	
	public Particle update(Graviton force) {
		// determine random velocity for particle movement
		randomizeVelocity(rand);
		
		if (force == null) { // if no force, slow the particle
			slow();
		} else {
			affectVelocitybyGraviton(force);
		}
		
		xPos += xVel;
		yPos += yVel;
		age += 10;

		//age based on speed reduction
		if ((Math.abs(xVel) + Math.abs(yVel)) <= .3) {
			age += 100 / (Math.abs(xVel) + Math.abs(yVel));
		}
		
		//p.resetParticle(xPos, yPos, xVel, yVel);
		//p.setAge(age);
		return this;
	}
	
	public void resetParticle(float xPos, float yPos, float xVel, float yVel) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
	}

	private void randomizeParticle(Random r, int numberSquare, float xPos,
			float yPos) {
		float Vel = r.nextFloat();
		double angle = r.nextDouble() * Math.PI * 2;
		float xVel = Vel * (float) Math.cos(angle);
		float yVel = Vel * (float) Math.sin(angle);
		resetParticle(xPos, yPos, xVel, yVel);
	}

	public void slow() {
		this.xVel = .97f * xVel;
		this.yVel = .97f * yVel;
	}

	public void randomizeVelocity(Random r) {
		float rand = r.nextFloat() * .5f + .5f;
		float oldXVel = getxVel();
		float oldYVel = getyVel();
		this.xVel = oldXVel * (1 - rand) + oldXVel * rand;
		this.yVel = oldYVel * (1 - rand) + oldYVel * rand;
	}

	public float getxPos() {
		return xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public float getxVel() {
		return xVel;
	}

	public float getyVel() {
		return yVel;
	}

	public float getpxVel() {
		return pxVel;
	}

	public float getpyVel() {
		return pyVel;
	}

	public int getAge() {
		return age;
	}

	public void setxPos(float xp) {
		xPos = xp;
	}

	public void setyPos(float yp) {
		yPos = yp;
	}

	public void setxVel(float xv) {
		xVel = xv;
	}

	public void setyVel(float yv) {
		yVel = yv;
	}

	public void setpxVel(float pxv) {
		pxVel = pxv;
	}

	public void setpyVel(float pyv) {
		pyVel = pyv;
	}

	public void setAge(int a) {
		age = a;
	}

}