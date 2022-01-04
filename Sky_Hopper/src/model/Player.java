package model;

import processing.core.*;


public class Player {

	private int Xmove;
	private int Ymove;
	private int Score;
	private int Speed;
	
	

	  
public void DrawPlayer(PApplet proc) {
	proc.rectMode(proc.CENTER);
	proc.noStroke();
	proc.fill(115,25,34);
	proc.rect(Xmove,Ymove,100,150);
}



public Player(int xmove, int ymove) {
	super();
	Xmove = xmove;
	Ymove = ymove;

}



public int getXmove() {
	return Xmove;
}



public void setXmove(int xmove) {
	Xmove = xmove;
}



public int getScore() {
	return Score;
}



public void setScore(int score) {
	Score = score;
}



public int getYmove() {
	return Ymove;
}



public void setYmove(int ymove) {
	Ymove = ymove;
}



public int getSpeed() {
	return Speed;
}






	
}
