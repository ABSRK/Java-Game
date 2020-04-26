package com.absrk.game.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	Game() throws FileNotFoundException {
	}

	static Scanner scanner = new Scanner(System.in);
	BufferedReader br1 = new BufferedReader(new FileReader("countryname.txt"));
	BufferedReader br2 = new BufferedReader(new FileReader("cityname.txt"));
	BufferedReader br3 = new BufferedReader(new FileReader("description.txt"));
	ArrayList<String> al1 = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	public static int score = 0;
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws Exception {

		Game g = new Game();
		g.startDisplay();
		for (;;) {
			g.selectDisplay();
		}
	}

	public void startDisplay() {

		System.out.println("1.START");
		System.out.println("2.GAME DESCRIPTION");
		System.out.println("3.EXIT\n");

	}

	public void selectDisplay() throws Exception {
		System.out.print("Enter the number from above: ");

		switch (scanner.next()) {
		case "1": {
			startGame();
		}
			break;
		case "2": {
			gameDesc();
		}
			break;
		case "3": {
			System.out.println("Exited...");
			scanner.close();
			br1.close();
			br2.close();
			br3.close();
			System.exit(0);
		}
			break;
		default: {
			System.out.println("Invalid Option. Enter again!");

		}
			break;
		}
	}

	public void gameDesc() throws IOException {

		String text = null;
		while ((text = br3.readLine()) != null) {
			System.out.println(text);
		}

	}

	public void startGame() throws Exception {
		Game g1 = new Game();

		System.out.print("ENTER NAME: ");
		g1.setName(scanner.next());
		System.out.println("WELCOME " + g1.getName() + "!");
		for (;;) {
			System.out.println("1.CITY NAME");
			System.out.println("2.COUNTRY NAME");
			System.out.println("3.EXIT");
			System.out.print("Enter your chice: ");
			g1.selectGame(scanner.next());

		}
	}

	public void selectGame(String s) throws Exception {
		switch (s) {
		case "1": {
			game1();
			break;
		}
		case "2": {
			game2();
			break;
		}
		case "3": {
			System.out.println("Bye....");

			System.exit(0);
			break;
		}
		default: {
			System.out.println("Select 1 or 2 or 3 only ");
			break;
		}
		}
	}

	public void game2() throws Exception {

		String text = null;
		while ((text = br1.readLine()) != null) {
			al1.add(text);
		}
		generateGame(al1);

	}

	public void game1() throws IOException {

		String text = null;
		while ((text = br2.readLine()) != null) {
			al2.add(text);
		}
		generateGame(al2);
	}

	public String getRandom(ArrayList<String> al1) {

		String temp = al1.get((int) (Math.random() * al1.size()));

		return temp;
	}

	public int playGame(String st) {

		char[] ch = st.toCharArray();
		char t;
		for (int i = 0; i < ch.length; i++) {
			int temp = (int) (Math.random() * ch.length);
			t = ch[i];
			ch[i] = ch[temp];
			ch[temp] = t;
		}
		System.out.println(ch);
		System.out.print("Enter your answer: ");
		for (int i = 1; i <= 3; i++) {
			if (scanner.next().toUpperCase().equals(st)) {
				System.out.println("Correct!");
				addScore(i);
				System.out.println(getName() +" , Your score is " + score);
				return 0;

			} else
				System.out.println("Try again!");
		}
		System.out.println("Answer is " + st);
		return 0;
	}

	public static void addScore(int i) {
		if (i == 1)
			score += 15;
		else if (i == 2)
			score += 10;
		else
			score += 5;
	}

	public void generateGame(ArrayList<String> al) {
		for (;;) {
			playGame(getRandom(al));
			System.out.print("Do you want to play again? (YES/NO): ");
			if (scanner.next().toUpperCase().equals("YES"))
				continue;
			else
				break;
		}
	}
}
