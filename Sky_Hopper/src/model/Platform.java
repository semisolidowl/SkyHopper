package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import processing.core.*;

public class Platform {
	
	private int Xppos;
	private float Yppos;
	private int size;
	private int thick=15;

		public void CreatePlatform(PApplet proc) {
			proc.rectMode(PApplet.CENTER);
			proc.noStroke();
			proc.fill(94, 224, 47);
			proc.rect(Xppos,Yppos,size,thick);
			//uncaption this to see Platform hitbox
			/*
			proc.fill(215, 24, 245);
			proc.rectMode(PApplet.CORNER);
			proc.rect(Xppos-(size/2),Yppos-(thick/2),size,thick);
			 */
		}


		public Platform(int xppos, float f, int size) {
			super();
			Xppos = xppos;
			Yppos = f;
			this.size = size;
		}


		public int getXppos() {
			return Xppos;
		}


		public void setXppos(int xppos) {
			Xppos = xppos;
		}


		public float getYppos() {
			return Yppos;
		}


		public void setYppos(float yppos) {
			Yppos = yppos;
		}


		public int getSize() {
			return size;
		}


		public void setSize(int size) {
			this.size = size;
		}

		
		public Rectangle2D hitBox() {

			
			Rectangle2D fir = new Rectangle2D.Float(Xppos-(size/2),Yppos-(thick/2),size,thick);
			return fir;
		}
		
		
		
		



}
