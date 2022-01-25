package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Highscore {

	private String date;
	private int height;

	/**
	 * inputs score outputs score with appropriate time stamp
	 * @param height
	 */
	public Highscore(int height) {
		this.height = height;
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		String formattedDate = myDateObj.format(myFormatObj);
		this.date = myDateObj.format(myFormatObj);
	}

	public String getDate() {
		return date;
	}

	public int getScore() {
		return height;
	}

}
