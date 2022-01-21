package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import processing.core.*;


public class Player {

	private float Xmove;
	private float Ymove;
	private float Xsize=75;
	private float Ysize=105;
	private int Score;
	private float Speed=(float) 0;
	public float s;
	

	public Player(float xmove, float ymove) {
		super();
		Xmove = xmove;
		Ymove = ymove;

	}



	public float getXmove() {
		return Xmove;
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



	public void DrawPlayer(PApplet proc) {
		proc.rectMode(PApplet.CENTER);
		proc.noStroke();
		proc.fill(115,25,34);
		proc.rect(Xmove,Ymove,Xsize,Ysize);
		//uncaption this to see player hitbox(pink)
		/*
		proc.fill(215, 24, 245);
		proc.rectMode(PApplet.CORNER);
		proc.rect(Xmove-(Xsize/2),Ymove+(Ysize/2)-(Ysize-100),Xsize,Ysize-100);
		*/
	}

	
	public Rectangle2D hitBox() {
		Rectangle2D fir = new Rectangle2D.Float(Xmove-(Xsize/2),Ymove+(Ysize/2)-(Ysize-100),Xsize,Ysize-100);
		return fir;
	}
	
	
	public void jump(PApplet proc) {
		s=(float) (s+0.15);
		Ymove=PApplet.min(Ymove+s,proc.height);			
	}

	public void setXmove() {
		Xmove=Xmove+getSpeed();
		if(getSpeed()<0){
			setSpeed((float) (getSpeed()+0.1));
		}else {
			setSpeed((float) (getSpeed()-0.1));
		}
	}
	
	
	
	

	
}
