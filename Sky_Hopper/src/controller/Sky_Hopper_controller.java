package controller;


import processing.core.*;
import java.util.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import model.Player;
import model.Platform;


public class Sky_Hopper_controller extends PApplet{

	public static void main(String[] args) {
		PApplet.main("controller.Sky_Hopper_controller");

	}
	
	enum GameState{
		Title,InGame,GameOver
	}
	
	
	
	
	int platformCount=0;
	Random rand = new Random();
	ArrayList<Platform> platforms;
	Player p1 = new Player(300, 650);
	Platform sp = new Platform(300, 750 ,200);
	
	
	public void settings() {
		size(600,900);
	}
	
	@Override
	public void setup() {
		GameState state=GameState.Title;
		background(140, 230, 221);
		frameRate(69);
		platforms = new ArrayList<Platform>();
		platforms.add(sp);
		platgen();
		
	}
		   
	@Override
	public void draw() {
		background(140, 230, 221);
		platgen();
		screenMove();
		p1.setXmove();
		p1.DrawPlayer(this);
		for (Platform p : platforms) {
			p.CreatePlatform(this);
			if(platformCollision(p1, p)) {
				p1.s=-10;
			}
		}
		
		
	}
	
	@Override
	public void keyPressed() {
		switch(keyCode) {
		case LEFT: p1.setSpeed(max((float) (p1.getSpeed()-0.3),-3)); break;
		case RIGHT: p1.setSpeed(min((float) (p1.getSpeed()+0.3),3)); break;
		}
		
	}
	
	
	
	boolean platformCollision(Player py,Platform pt){
		Rectangle2D pBound=py.hitBox();
		Rectangle2D boundP=pt.hitBox();	
		if(p1.s>0) {if (pBound.intersects(boundP)) {
			return true;
		}else {return false;}}else {return false;}
	}
	
	
	public void platgen(){
		Platform currentP=platforms.get(platformCount);
		int s=rand.nextInt(126)+75;		
		platforms.add(new Platform(rand.nextInt(601-s)+s, -(rand.nextInt(250+1))+currentP.getYppos()-50,s));		
		platformCount++;
	}
	
	public void screenMove() {
		for(Platform p:platforms) {
			if(p1.getYmove()<200) {
				if(p1.s<=0) {
					p.setYppos(p.getYppos()-p1.s);			
				}else{
					p1.setYmove(min(p1.getYmove()+p1.s,height));
				}
			}	
		}
		if(p1.getYmove()>200) {
			p1.jump(this);
		}else{
			p1.s=(float) (p1.s+0.15);
		}	
		}
	
	
	
	
}
