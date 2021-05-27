package AP_Assignment3;

import java.util.Scanner;

public class Assignment3_Main {
	static Scanner input = new Scanner(System.in);
	static int n;
	static int noMafias;
	static int noDetectives;
	static int noHealers;
	static int noCommoners;
	static Game game;

	public static void main(String[] args) {
		Player user = startGame();
		if (user instanceof Mafia) {
			user.printCategoryPlayers();
		}
		if (user instanceof Detective) {
			user.printCategoryPlayers();
		}
		if (user instanceof Healer) {
			user.printCategoryPlayers();
		}
		if (user instanceof Commoner) {
			System.out.println("You are a commoner");
		}
		game.playGame(n, noMafias, noDetectives, noHealers, noCommoners);
		input.close();
	}

	public static Player startGame() {
		System.out.println("Welcome to Mafia");
		takeN();
		game = new Game(n);
		showMenu();
		int option = input.nextInt();
		return distributePlayers(option, game);
	}

	public static void takeN() {
		do {
			System.out.print("Enter Number of players : ");
			n = input.nextInt();
		} while (n < 6);
	}

	public static void showMenu() {
		System.out.println("Choose a Character");
		System.out.println("1) Mafia");
		System.out.println("2) Detective");
		System.out.println("3) Healer");
		System.out.println("4) Commoner");
		System.out.println("5) Assign Randomly");
	}

	public static Player distributePlayers(int opt, Game game) {
		noMafias = n / 5;
		noDetectives = n / 5;
		noHealers = Math.max(n / 10, 1);
		noCommoners = n - (noDetectives + noHealers + noMafias);
		Player user = game.addPlayers(opt, noMafias, noDetectives, noHealers, noCommoners);
		return user;
	}

}
