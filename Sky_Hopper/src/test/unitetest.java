package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import model.Platform;
import model.Player;

public class unitetest {

	
		
		@Test
		public void colissiontests() {
			Player y = new Player(50, 50);
			Platform t = new Platform(50, 50, 100);
			
			assertEquals(y.getXmove(), t.getXppos());
			assertEquals(y.getYmove(), t.getYppos());
		}
		
		
		/*
		@Test
		public void () {
			
		}
		
		@Test
		public void () {
			
		}
		*/
	

}
