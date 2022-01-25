package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import processing.core.*;

/**
 * this is the player
 * @author Dario.grob
 *
 */
public class Player {

	private float Xmove;
	private float Ymove;
	private float Xsize=75;
	private float Ysize=105;
	private int Score=0;
	private float Speed=(float) 0;
	public float s;

	/**
	 * this is the constructor for the player class
	 * @param xmove
	 * @param ymove
	 */
	public Player(float xmove, float ymove) {
		super();
		Xmove = xmove;
		Ymove = ymove;

	}



	public float getXmove() {
		return Xmove;
	}

	public void setXmove(float xmove) {
		Xmove = xmove;
	}

	public int getScore() {
		return Score;
	}



	public void setScore(int score) {
		Score = score;
	}



	public float getYmove() {
		return Ymove;
	}



	public void setYmove(float ymove) {
		Ymove = ymove;
	}



	public float getSpeed() {
		return Speed;
	}


	public void setSpeed(float speed) {
		Speed = speed;
	}


	/**
	 * this gives the instructions to draw the player
	 * @param proc
	 */
	public void DrawPlayer(PApplet proc) {
		proc.rectMode(PApplet.CENTER);
		proc.noStroke();
		proc.fill(115,25,34);
		proc.rect(Xmove,Ymove,Xsize,Ysize);
		proc.rect(Xmove+600,Ymove,Xsize,Ysize);
		proc.rect(Xmove-600,Ymove,Xsize,Ysize);
		//uncaption this to see player hitbox(pink)
		/*
		proc.fill(215, 24, 245);
		proc.rectMode(PApplet.CORNER);
		proc.rect(Xmove-(Xsize/2),Ymove+(Ysize/2)-(Ysize-100),Xsize,Ysize-100);
		proc.rect(Xmove-(Xsize/2)+600,Ymove+(Ysize/2)-(Ysize-100),Xsize,Ysize-100);
		proc.rect(Xmove-(Xsize/2)+600,Ymove+(Ysize/2)-(Ysize-100),Xsize,Ysize-100);
		 */
	}

	/**
	 * instructions for the hitbox dimensions
	 * @return the dimensions of the players hitbox
	 */
	public Rectangle2D hitBox() {
		Rectangle2D fir = new Rectangle2D.Float(Xmove-(Xsize/2),Ymove+(Ysize/2)-(Ysize-100),Xsize,Ysize-100);
		return fir;
	}
	/**
	 * instructions for the hitbox dimensions
	 * @return the dimensions of the player-doubles hitbox
	 */
	public Rectangle2D hitBox2() {
		Rectangle2D fir = new Rectangle2D.Float(Xmove-(Xsize/2)+600,Ymove+(Ysize/2)-(Ysize-100),Xsize,Ysize-100);
		return fir;
	}
	/**
	 * instructions for the hitbox dimensions
	 * @return the dimensions of the player-doubles hitbox
	 */
	public Rectangle2D hitBox3() {
		Rectangle2D fir = new Rectangle2D.Float(Xmove-(Xsize/2)-600,Ymove+(Ysize/2)-(Ysize-100),Xsize,Ysize-100);
		return fir;
	}

	/**
	 * gives the acceleration/deceleration/simulates gravity for the player
	 * @param proc
	 */
	public void fall(PApplet proc) {
		s=(float) (s+0.15);
		Ymove=PApplet.min(Ymove+s,proc.height);			
	}
	/**
	 * gives the movements in the x-axis inertia 
	 */
	public void setXeinertia() {
		Xmove=Xmove+getSpeed();
		if(getSpeed()<=0.0001){
			setSpeed((float) (getSpeed()+0.1));
		}if(!(getSpeed()<=0.0001)) {
			setSpeed((float) (getSpeed()-0.1));
		}

	}
	/*
	 * Teleports the central player model to the exposed models location 
	 */
	public void infinity(PApplet proc) {
		if(getXmove()>proc.width) {
			setXmove(getXmove()-600);
		}
		if(getXmove()<0) {
			setXmove(getXmove()+600);
		}

	}




}
