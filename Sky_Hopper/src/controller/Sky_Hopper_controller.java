package controller;


import processing.core.*;
/**
 * extends PApplet gibt zugriff auf processing funktionen
 * */
import java.util.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.awt.*;
import model.Player;
import model.Highscore;
import model.Platform;

/**
 * Start and Controller of Sky Hopper
 * "extends PApplet" grants acsses to Processing features
 * @author Dario.grob
 *
 */
public class Sky_Hopper_controller extends PApplet{

	public static void main(String[] args) {
		//starts the window
		PApplet.main("controller.Sky_Hopper_controller");

	}
	/**
	 * changes game state
	 * @author Dario.grob
	 *
	 */
	enum GameState{
		Title,InGame,GameOver
	}
	GameState state=GameState.Title;

	boolean highScoreAdd;
	int platformCount=0;
	Random rand = new Random();
	ArrayList<Highscore> highscores;
	ArrayList<Platform> platforms;
	Player p1 = new Player(300, 650);
	Platform sp = new Platform(300, 750 ,200);
	float oldSpLocation=sp.getYppos();


	public void settings() {
		/**
		 * sets window size
		 */
		size(600,900);
	}

	@Override
	public void setup() {

		background(140, 230, 221);
		frameRate(69);
		highscores = new ArrayList<Highscore>();
		platforms = new ArrayList<Platform>();
		platforms.add(sp);
		platgen();
		readHighscore();
	}

	public void draw() {
		switch(state){
		case Title:TitleDraw(); break;
		case InGame:InGameDraw(); break;
		case GameOver:GameOverDraw(); break;
		}
	}

	void TitleDraw(){
		background(140, 230, 221);
		p1.DrawPlayer(this);
		Platform sp = new Platform(300, 750 ,200);
		sp.CreatePlatform(this);
		p1.fall(this);
		if(platformCollision(p1, sp)) {
			p1.s=-10;
		}
		fill(0, 100);
		rect(300,450,600,900);
		textSize(40);
		textAlign(CENTER);
		fill(20);
		text("Press Any ''Key'' To Start Game",300,200);
		textSize(20);
		text("Controls: use Left and Right Arrow Keys To Move",300,230);
		text("Goal: use the Platforms to climb as high as possible",300,260);
		textSize(32);
		fill(255);
		text("highscore",300,310);
		textSize(20);
		int i = 0;
		for(Highscore h : highscores) {
			if(i<=5) {
				text("Date: "+h.getDate(),300,340+i*55);
				text("Height Reached: "+h.getScore()+"m",300,360+i*55);
				i++;
			}
		}
	}
	void InGameDraw() {
		background(140, 230, 221);
		platgen();
		screenMove();
		p1.setXeinertia();
		p1.infinity(this);
		scoring();
		p1.DrawPlayer(this);
		for (Platform p : platforms) {
			p.CreatePlatform(this);
			if(platformCollision(p1, p)) {
				p1.s=-10;
			}
		}
		if(p1.getYmove()>860) {
			state=GameState.GameOver;
		}
		fill(5);
		textSize(10);
		text("height: "+p1.getScore()+"m",30,10);
	}
	void GameOverDraw(){
		sp.setXppos(300);
		sp.setYppos(750);
		fill(0, 4);
		rect(300,450,600,900);
		textSize(55);
		textAlign(CENTER);
		fill(179, 59, 59);
		text("GameOver",300,450);
		textSize(20);
		fill(250);
		text("You climbed "+p1.getScore()+"m!",300,550);
		textSize(20);
		fill(250);
		text("Press DownArow To return to title",300,700);
		if(!highScoreAdd) {
			Highscore newHighScore = new Highscore(p1.getScore());
			highscores.add(newHighScore);
			writeHighscore();
			highScoreAdd=true;
		}

	}

	@Override
	public void keyPressed() {
		switch(state){
		case Title:TitleKeys(); break;
		case InGame:InGameKeys();break;
		case GameOver:GameOverKeys(); break;
		}


	}
	void TitleKeys() {
		if(keyPressed==true) {
			highScoreAdd=false;
			state = GameState.InGame;
		}
	}
	void InGameKeys(){
		switch(keyCode) {
		case LEFT: p1.setSpeed(max((float) (p1.getSpeed()-0.3),-3)); break;
		case RIGHT: p1.setSpeed(min((float) (p1.getSpeed()+0.3),3)); break;
		}
	}
	void GameOverKeys() {
		if(keyCode==DOWN) {
			state = GameState.Title;
			p1.setXmove(300);
			p1.setYmove(650);
			platforms.clear();
			platforms.add(sp);
			oldSpLocation=sp.getYppos();
			platformCount=0;
			p1.setScore(0);
		}
	}
	/**
	 * checks if the players hitbox intercept a platform
	 * @param py
	 * @param pt
	 * @return true when p1 intersects a platform
	 */
	public boolean platformCollision(Player py,Platform pt){
		Rectangle2D pBound=py.hitBox();
		Rectangle2D pBound2=py.hitBox2();
		Rectangle2D pBound3=py.hitBox3();
		Rectangle2D boundP=pt.hitBox();	
		if(p1.s>0) {if (pBound.intersects(boundP)||pBound2.intersects(boundP)||pBound3.intersects(boundP)) {
			return true;
		}else {return false;}}else {return false;}
	}

	/**
	 * generates platforms
	 */
	public void platgen(){
		Platform currentP=platforms.get(platformCount);
		int s=rand.nextInt(126)+75;		
		platforms.add(new Platform(rand.nextInt(width-(s))+(s/2), -(rand.nextInt(250+1))+currentP.getYppos()-50,s));		
		platformCount++;
	}
	/**
	 * moves the platforms down to give the illusion upward movement
	 */
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
		if(p1.getYmove()>=200) {
			p1.fall(this);
		}else{
			p1.s=(float) (p1.s+0.15);
		}	
	}
	/**
	 * checks for platform movement to give score
	 */
	public void scoring() {
		if(sp.getYppos()>=oldSpLocation+50){
			oldSpLocation=sp.getYppos();
			p1.setScore(p1.getScore()+1);
		}
	}
	/**
	 * saves the "highscore" to the highscores file
	 */
	private void writeHighscore(){
		PrintWriter prnt = null;
		File hsFile = new File("highscores");

		highscores.sort((a,b)-> b.getScore()-a.getScore());


		try {
			prnt =new PrintWriter(hsFile);

			for(Highscore s : highscores) {

				prnt.write(s.getDate()+";"+s.getScore()+System.lineSeparator());
			}
			prnt.flush();
			prnt.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}
	/**
	 * reads the "highscore" from the highscores file
	 */
	private void readHighscore(){
		highscores = new ArrayList<Highscore>();
		File hsFile = new File("highscores");
		try {
			FileReader fReader = new FileReader(hsFile);
			BufferedReader bfr = new BufferedReader(fReader);
			try {
				while(bfr.ready()==true) {
					String line=bfr.readLine();
					System.out.println(line);
					String[] bestandteil =line.split(";");
					Highscore highs = new Highscore(Integer.parseInt(bestandteil[1])
							);

					highscores.add(highs);
				}
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println(e);
			}

		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}




}