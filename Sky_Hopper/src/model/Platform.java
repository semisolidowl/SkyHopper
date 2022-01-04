package model;

import processing.core.*;

public class Platform {
	
	private int Xppos;
	private int Yppos;
	private int size;


		public void CreatePlatform(PApplet proc) {
			proc.rectMode(proc.CENTER);
			proc.noStroke();
			proc.fill(94, 224, 47);
			proc.rect(Xppos,Yppos,size,15);
		}


		public Platform(int xppos, int yppos, int size) {
			super();
			Xppos = xppos;
			Yppos = yppos;
			this.size = size;
		}


		public int getXppos() {
			return Xppos;
		}


		public void setXppos(int xppos) {
			Xppos = xppos;
		}


		public int getYppos() {
			return Yppos;
		}


		public void setYppos(int yppos) {
			Yppos = yppos;
		}


		public int getSize() {
			return size;
		}


		public void setSize(int size) {
			this.size = size;
		}





}
