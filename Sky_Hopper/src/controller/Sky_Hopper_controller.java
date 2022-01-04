package controller;


import processing.core.*;
import java.util.*;

import model.Player;
import model.Platform;


public class Sky_Hopper_controller extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("controller.Sky_Hopper_controller");

	}
	Player p1 = new Player(100, 100);
	Platform sp = new Platform(300, 750 ,200);
	
	public void settings() {
		size(600,900);
	
	}
	
	@Override
	public void setup() {
		background(140, 230, 221);
	
	
	}
		   
	@Override
	public void draw() {
		background(140, 230, 221);

		p1.DrawPlayer(this);
		p1.setYmove(min(p1.getYmove()+3,height-75));
		
		sp.CreatePlatform(this);
	}
	
	@Override
	public void keyPressed() {
		switch(keyCode) {
		case LEFT: p1.setXmove(max(p1.getXmove() - 5, 0)); break;
		case RIGHT: p1.setXmove(min(p1.getXmove() + 5, width)); break;
		}
		
	}
	
	
	
	
	
	
}
