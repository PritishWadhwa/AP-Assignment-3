package AP_Assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

enum PlayerStatus {
	alive, dead
}

enum PlayerRole {
	Mafia, Detective, Healer, Commoner
}

class PlayerVotesPair {
	private Player player;
	private int votes;

	public PlayerVotesPair(Player p) {
		// TODO Auto-generated constructor stub
		this.player = p;
		this.votes = 0;
	}

	public void giveVote() {
		this.votes++;
	}

	public int getVote() {
		return this.votes;
	}

	public Player getPlayer() {
		return this.player;
	}
}

class PlayerStatusPair<T> {
	private T player;
	private PlayerStatus status;
	private final PlayerRole role;

	public PlayerStatusPair(T player, PlayerRole role) {
		this.player = player;
		this.status = PlayerStatus.alive;
		this.role = role;
	}

	public T getPlayer() {
		return this.player;
	}

	public PlayerStatus getStatus() {
		return this.status;
	}

	public PlayerRole getRole() {
		return this.role;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
}

class Game {
	private final int noOfPlayers;
	private PlayerRole userRole;
	private Map<Integer, PlayerStatusPair<Player>> players = new HashMap<Integer, PlayerStatusPair<Player>>();
	private ArrayList<Player> hpChangeList = new ArrayList<Player>();
	private boolean mafiaWin;
	private boolean UserAlive = true;
	private int userID;
	Scanner input = new Scanner(System.in);

	public Game(int n) {
		this.noOfPlayers = n;
	}

	public int getUID() {
		return this.userID;
	}

	public void setUID(int id) {
		this.userID = id;
	}

	public void killUser() {
		this.UserAlive = false;
	}

	public boolean isUserAlive() {
		return this.UserAlive;
	}

	public int getNoOfPlayers() {
		return this.noOfPlayers;
	}

	public int getRandomValue(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public Map<Integer, PlayerStatusPair<Player>> getPlayerList() {
		return this.players;
	}

	public ArrayList<Player> getHPChangeList() {
		return this.hpChangeList;
	}

	public void setWinner(boolean win) {
		this.mafiaWin = win;
	}

	public boolean isMafiaWinner() {
		return this.mafiaWin;
	}

	public void addPlayer(int id, Player p, PlayerRole role) {
		players.put(id, new PlayerStatusPair<Player>(p, role));
	}

	public void setRole(PlayerRole role) {
		this.userRole = role;
	}

	public PlayerRole getUserRole() {
		return this.userRole;
	}

	public Player addPlayers(int userOpt, int mafias, int detectives, int healers, int commoners) {
		int[] arr = new int[getNoOfPlayers()];
		int temp;
		Player user;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		if (userOpt == 1) {
			int n = getNoOfPlayers();
			int randVal;
			randVal = getRandomValue(0, n - 1);
			user = new Mafia(arr[randVal]);
			addPlayer(arr[randVal], user, PlayerRole.Mafia);
			Mafia.addPlayer(arr[randVal], new PlayerStatusPair<Mafia>(new Mafia(arr[randVal]), PlayerRole.Mafia));
			System.out.println("You are Player" + arr[randVal]);
			setUID(arr[randVal]);
			temp = arr[randVal];
			arr[randVal] = arr[n - 1];
			arr[n - 1] = temp;
			n--;
			setRole(PlayerRole.Mafia);
			for (int i = 0; i < mafias - 1; i++) {
				randVal = getRandomValue(0, n - 1);
				Player m = new Mafia(arr[randVal]);
				addPlayer(arr[randVal], m, PlayerRole.Mafia);
				Mafia.addPlayer(arr[randVal], new PlayerStatusPair<Mafia>(new Mafia(arr[randVal]), PlayerRole.Mafia));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < detectives; i++) {
				randVal = getRandomValue(0, n - 1);
				Player d = new Detective(arr[randVal]);
				addPlayer(arr[randVal], d, PlayerRole.Detective);
				Detective.addPlayer(arr[randVal],
						new PlayerStatusPair<Detective>(new Detective(arr[randVal]), PlayerRole.Detective));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < healers; i++) {
				randVal = getRandomValue(0, n - 1);
				Player h = new Healer(arr[randVal]);
				addPlayer(arr[randVal], h, PlayerRole.Healer);
				Healer.addPlayer(arr[randVal],
						new PlayerStatusPair<Healer>(new Healer(arr[randVal]), PlayerRole.Healer));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < commoners; i++) {
				randVal = getRandomValue(0, n - 1);
				Player c = new Commoner(arr[randVal]);
				addPlayer(arr[randVal], c, PlayerRole.Commoner);
				Commoner.addPlayer(arr[randVal],
						new PlayerStatusPair<Commoner>(new Commoner(arr[randVal]), PlayerRole.Commoner));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
		} else if (userOpt == 2) {
			int n = getNoOfPlayers();
			int randVal;
			randVal = getRandomValue(0, n - 1);
			user = new Detective(arr[randVal]);
			addPlayer(arr[randVal], user, PlayerRole.Detective);
			Detective.addPlayer(arr[randVal],
					new PlayerStatusPair<Detective>(new Detective(arr[randVal]), PlayerRole.Detective));
			System.out.println("You are Player" + arr[randVal]);
			setUID(arr[randVal]);
			temp = arr[randVal];
			arr[randVal] = arr[n - 1];
			arr[n - 1] = temp;
			n--;
			setRole(PlayerRole.Detective);
			for (int i = 0; i < mafias; i++) {
				randVal = getRandomValue(0, n - 1);
				Player m = new Mafia(arr[randVal]);
				addPlayer(arr[randVal], m, PlayerRole.Mafia);
				Mafia.addPlayer(arr[randVal], new PlayerStatusPair<Mafia>(new Mafia(arr[randVal]), PlayerRole.Mafia));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < detectives - 1; i++) {
				randVal = getRandomValue(0, n - 1);
				Player d = new Detective(arr[randVal]);
				addPlayer(arr[randVal], d, PlayerRole.Detective);
				Detective.addPlayer(arr[randVal],
						new PlayerStatusPair<Detective>(new Detective(arr[randVal]), PlayerRole.Detective));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < healers; i++) {
				randVal = getRandomValue(0, n - 1);
				Player h = new Healer(arr[randVal]);
				addPlayer(arr[randVal], h, PlayerRole.Healer);
				Healer.addPlayer(arr[randVal],
						new PlayerStatusPair<Healer>(new Healer(arr[randVal]), PlayerRole.Healer));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < commoners; i++) {
				randVal = getRandomValue(0, n - 1);
				Player c = new Commoner(arr[randVal]);
				addPlayer(arr[randVal], c, PlayerRole.Commoner);
				Commoner.addPlayer(arr[randVal],
						new PlayerStatusPair<Commoner>(new Commoner(arr[randVal]), PlayerRole.Commoner));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
		} else if (userOpt == 3) {
			int n = getNoOfPlayers();
			int randVal;
			randVal = getRandomValue(0, n - 1);
			user = new Healer(arr[randVal]);
			addPlayer(arr[randVal], user, PlayerRole.Healer);
			Healer.addPlayer(arr[randVal], new PlayerStatusPair<Healer>(new Healer(arr[randVal]), PlayerRole.Healer));
			System.out.println("You are Player" + arr[randVal]);
			setUID(arr[randVal]);
			temp = arr[randVal];
			arr[randVal] = arr[n - 1];
			arr[n - 1] = temp;
			n--;
			setRole(PlayerRole.Healer);
			for (int i = 0; i < mafias; i++) {
				randVal = getRandomValue(0, n - 1);
				Player m = new Mafia(arr[randVal]);
				addPlayer(arr[randVal], m, PlayerRole.Mafia);
				Mafia.addPlayer(arr[randVal], new PlayerStatusPair<Mafia>(new Mafia(arr[randVal]), PlayerRole.Mafia));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < detectives; i++) {
				randVal = getRandomValue(0, n - 1);
				Player d = new Detective(arr[randVal]);
				addPlayer(arr[randVal], d, PlayerRole.Detective);
				Detective.addPlayer(arr[randVal],
						new PlayerStatusPair<Detective>(new Detective(arr[randVal]), PlayerRole.Detective));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < healers - 1; i++) {
				randVal = getRandomValue(0, n - 1);
				Player h = new Healer(arr[randVal]);
				addPlayer(arr[randVal], h, PlayerRole.Healer);
				Healer.addPlayer(arr[randVal],
						new PlayerStatusPair<Healer>(new Healer(arr[randVal]), PlayerRole.Healer));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < commoners; i++) {
				randVal = getRandomValue(0, n - 1);
				Player c = new Commoner(arr[randVal]);
				addPlayer(arr[randVal], c, PlayerRole.Commoner);
				Commoner.addPlayer(arr[randVal],
						new PlayerStatusPair<Commoner>(new Commoner(arr[randVal]), PlayerRole.Commoner));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
		} else if (userOpt == 4) {
			int n = getNoOfPlayers();
			int randVal;
			randVal = getRandomValue(0, n - 1);
			user = new Commoner(arr[randVal]);
			addPlayer(arr[randVal], user, PlayerRole.Commoner);
			Commoner.addPlayer(arr[randVal],
					new PlayerStatusPair<Commoner>(new Commoner(arr[randVal]), PlayerRole.Commoner));
			System.out.println("You are Player" + arr[randVal]);
			setUID(arr[randVal]);
			temp = arr[randVal];
			arr[randVal] = arr[n - 1];
			arr[n - 1] = temp;
			n--;
			setRole(PlayerRole.Commoner);
			for (int i = 0; i < mafias; i++) {
				randVal = getRandomValue(0, n - 1);
				Player m = new Mafia(arr[randVal]);
				addPlayer(arr[randVal], m, PlayerRole.Mafia);
				Mafia.addPlayer(arr[randVal], new PlayerStatusPair<Mafia>(new Mafia(arr[randVal]), PlayerRole.Mafia));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < detectives; i++) {
				randVal = getRandomValue(0, n - 1);
				Player d = new Detective(arr[randVal]);
				addPlayer(arr[randVal], d, PlayerRole.Detective);
				Detective.addPlayer(arr[randVal],
						new PlayerStatusPair<Detective>(new Detective(arr[randVal]), PlayerRole.Detective));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < healers; i++) {
				randVal = getRandomValue(0, n - 1);
				Player h = new Healer(arr[randVal]);
				addPlayer(arr[randVal], h, PlayerRole.Healer);
				Healer.addPlayer(arr[randVal],
						new PlayerStatusPair<Healer>(new Healer(arr[randVal]), PlayerRole.Healer));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
			for (int i = 0; i < commoners - 1; i++) {
				randVal = getRandomValue(0, n - 1);
				Player c = new Commoner(arr[randVal]);
				addPlayer(arr[randVal], c, PlayerRole.Commoner);
				Commoner.addPlayer(arr[randVal],
						new PlayerStatusPair<Commoner>(new Commoner(arr[randVal]), PlayerRole.Commoner));
				temp = arr[randVal];
				arr[randVal] = arr[n - 1];
				arr[n - 1] = temp;
				n--;
			}
		} else if (userOpt == 5) {
			int newOpt = getRandomValue(1, 4);
			return addPlayers(newOpt, mafias, detectives, healers, commoners);
		} else {
			System.out.println("Error: Please enter a value between 1 - 5: ");
			int newOpt = input.nextInt();
			return addPlayers(newOpt, mafias, detectives, healers, commoners);
		}
		return user;
	}

	public void playGame(int n, int noMafia, int noDetective, int noHealer, int noCommoner) {
		int noOfP = n;
		int noOfM = noMafia;
		int noOfD = noDetective;
		int noOfH = noHealer;
		int noOfC = noCommoner;
		boolean gameOver = false;
		int roundNo = 1;
		do {
			System.out.println();
			System.out.println("Round " + roundNo + ": ");
			printAlivePlayers();
			int playerSelectedByMafia = 0, playerSelectedByDetective = 0, playerSelectedByHealers;
			boolean mafiaCaught = false;
			if (noOfM > 0) {
				playerSelectedByMafia = mafiaSelectPlayer(noOfP - noOfM, n);
				int initialHP = getPlayerList().get(playerSelectedByMafia).getPlayer().getHP();
				getPlayerList().get(playerSelectedByMafia).getPlayer().changeHP(Mafia.calculateTotalHP());
				changeMafiaHP(initialHP);
			} else {
				System.out.println("Mafias have chosen their target");
			}
			if (noOfD > 0) {
				playerSelectedByDetective = detectivesSelectPlayer(noOfP - noOfD, n);
				if (getPlayerList().get(playerSelectedByDetective).getPlayer() instanceof Mafia) {
					mafiaCaught = true;
				}
				if (getUserRole() == PlayerRole.Detective) {
					if (mafiaCaught) {
						System.out.println("Player" + playerSelectedByDetective + " is a Mafia");
					} else {
						System.out.println("Player" + playerSelectedByDetective + " is not a Mafia");
					}
				}
			} else {
				System.out.println("Detectives have chosen a player to test.");

			}
			if (noOfH > 0) {
				playerSelectedByHealers = healersSelectPlayer(noOfP, n);
				getPlayerList().get(playerSelectedByHealers).getPlayer().changeHP(-500);
				if (getPlayerList().get(playerSelectedByHealers).getPlayer() instanceof Mafia) {
					Mafia.getMafiaList().get(playerSelectedByHealers).getPlayer().changeHP(-500);
				}
			} else {
				System.out.println("Healers have chosen a player to heal");
			}
			System.out.println("--End of Actions--");
			if (noOfM > 0 && getPlayerList().get(playerSelectedByMafia).getPlayer().getHP() <= 0) {
				System.out.println("Player" + playerSelectedByMafia + " has died.");
				if (getPlayerList().get(playerSelectedByMafia).getPlayer() instanceof Commoner) {
					noOfC--;
				} else if (getPlayerList().get(playerSelectedByMafia).getPlayer() instanceof Healer) {
					noOfH--;
				} else if (getPlayerList().get(playerSelectedByMafia).getPlayer() instanceof Detective) {
					noOfD--;
				}
				getPlayerList().get(playerSelectedByMafia).setStatus(PlayerStatus.dead);
				if (getUID() == playerSelectedByMafia) {
					killUser();
				}
				noOfP--;
			} else {
				System.out.println("No one died");
			}
			gameOver = isGameOver(noOfM, noOfD, noOfH, noOfC);
			if (gameOver) {
				break;
			}
			if (mafiaCaught) {
				System.out.println("Player" + playerSelectedByDetective + " has been voted out");
				getPlayerList().get(playerSelectedByDetective).setStatus(PlayerStatus.dead);
				Mafia.getMafiaList().get(playerSelectedByDetective).setStatus(PlayerStatus.dead);
				if (getUID() == playerSelectedByDetective) {
					killUser();
				}
				noOfM--;
				noOfP--;
			} else {
				int personToBeVotedOut = conductVoting(noOfP, n);
				if (getPlayerList().get(personToBeVotedOut).getPlayer() instanceof Commoner) {
					noOfC--;
				} else if (getPlayerList().get(personToBeVotedOut).getPlayer() instanceof Healer) {
					noOfH--;
				} else if (getPlayerList().get(personToBeVotedOut).getPlayer() instanceof Detective) {
					noOfD--;
				} else if (getPlayerList().get(personToBeVotedOut).getPlayer() instanceof Mafia) {
					noOfM--;
					Mafia.getMafiaList().get(personToBeVotedOut).setStatus(PlayerStatus.dead);
				}
				if (getUID() == personToBeVotedOut) {
					killUser();
				}
				getPlayerList().get(personToBeVotedOut).setStatus(PlayerStatus.dead);
				noOfP--;
				System.out.println("Player" + personToBeVotedOut + " has been voted out");
			}
			gameOver = isGameOver(noOfM, noOfD, noOfH, noOfC);
			System.out.println("--End of Round " + roundNo + "--");
			roundNo++;
		} while (gameOver == false);
		showFinalScreen();
	}

	public void showFinalScreen() {
		System.out.println();
		System.out.println("Game Over!!!");
		if (isMafiaWinner()) {
			System.out.println("The Mafias have Won!");
		} else {
			System.out.println("The Mafias have lost!");
		}
		Mafia.printMafias();
		Detective.printDetectives();
		Healer.printHealers();
		Commoner.printCommoners();
	}

	public void printAlivePlayers() {
		ArrayList<Player> alivePlayers = new ArrayList<Player>();

		for (Map.Entry<Integer, PlayerStatusPair<Player>> p : getPlayerList().entrySet()) {
			if (p.getValue().getStatus() == PlayerStatus.alive) {
				alivePlayers.add(p.getValue().getPlayer());
			}
		}
		System.out.println(alivePlayers.size() + " players are remaining:");
		for (Player player : alivePlayers) {
			System.out.print("Player" + player.getId() + ", ");
		}
		System.out.println("are alive");
	}

	public int mafiaSelectPlayer(int noPpl, int n) {
		int selectedTgt;
		if (getUserRole() == PlayerRole.Mafia && isUserAlive()) {
			do {
				System.out.print("Choose a valid target: ");
				selectedTgt = input.nextInt();
			} while (selectedTgt > n || (getPlayerList().get(selectedTgt).getPlayer() instanceof Mafia)
					|| getPlayerList().get(selectedTgt).getStatus() == PlayerStatus.dead);
			return selectedTgt;
		}
		int[] arr = new int[noPpl];
		int i = 0;
		for (Map.Entry<Integer, PlayerStatusPair<Player>> list : getPlayerList().entrySet()) {
			if ((!(list.getValue().getPlayer() instanceof Mafia))
					&& list.getValue().getStatus() == PlayerStatus.alive) {
				arr[i++] = list.getValue().getPlayer().getId();
			}
		}
		System.out.println("Mafias have chosen their target");
		return arr[getRandomValue(0, noPpl - 1)];
	}

	public int detectivesSelectPlayer(int noPpl, int n) {
		int selectedTgt;
		if (getUserRole() == PlayerRole.Detective && isUserAlive()) {
			do {
				System.out.print("Choose a valid person to detect: ");
				selectedTgt = input.nextInt();
			} while (selectedTgt > n || (getPlayerList().get(selectedTgt).getPlayer() instanceof Detective)
					|| getPlayerList().get(selectedTgt).getStatus() == PlayerStatus.dead);
			return selectedTgt;
		}
		int[] arr = new int[noPpl];
		int i = 0;
		for (Map.Entry<Integer, PlayerStatusPair<Player>> list : getPlayerList().entrySet()) {
			if ((!(list.getValue().getPlayer() instanceof Detective))
					&& list.getValue().getStatus() == PlayerStatus.alive) {
				arr[i++] = list.getValue().getPlayer().getId();
			}
		}
		System.out.println("Detectives have chosen a player to test.");
		return arr[getRandomValue(0, noPpl - 1)];
	}

	public int healersSelectPlayer(int no, int n) {
		int[] arr = new int[n];
		int selectedTgt;
		if (getUserRole() == PlayerRole.Healer && isUserAlive()) {
			do {
				System.out.print("Enter a valid person to heal: ");
				selectedTgt = input.nextInt();
			} while (selectedTgt > n || getPlayerList().get(selectedTgt).getStatus() == PlayerStatus.dead);
			return selectedTgt;
		}
		int i = 0;
		for (Map.Entry<Integer, PlayerStatusPair<Player>> list : getPlayerList().entrySet()) {
			if (list.getValue().getStatus() == PlayerStatus.alive) {
				arr[i++] = list.getValue().getPlayer().getId();
			}
		}
		System.out.println("Healers have chosen a player to heal");
		return arr[getRandomValue(0, no - 1)];
	}

	public int conductVoting(int noOfP, int n) {
		int userSelection;
		int randomVote;
		ArrayList<PlayerVotesPair> votes = new ArrayList<PlayerVotesPair>();
		for (Map.Entry<Integer, PlayerStatusPair<Player>> list : getPlayerList().entrySet()) {
			votes.add(new PlayerVotesPair(list.getValue().getPlayer()));

		}
		if (isUserAlive()) {
			do {
				System.out.print("Enter your vote: ");
				userSelection = input.nextInt();
			} while (userSelection > votes.size()
					|| getPlayerList().get(userSelection).getStatus() == PlayerStatus.dead);
			votes.get(userSelection - 1).giveVote();
		} else {
			do {
				randomVote = getRandomValue(1, n);
			} while (getPlayerList().get(randomVote).getStatus() == PlayerStatus.dead);
			votes.get(randomVote - 1).giveVote();
		}
		for (int i = 0; i < noOfP - 1; i++) {
			do {
				randomVote = getRandomValue(1, n);
			} while (getPlayerList().get(randomVote).getStatus() == PlayerStatus.dead);
			votes.get(randomVote - 1).giveVote();
		}
		Collections.sort(votes, new sortByVotes());
		if (votes.size() > 1 && votes.get(0).getVote() == votes.get(1).getVote()) {
			return conductVoting(noOfP, n);
		} else {
			return votes.get(0).getPlayer().getId();
		}
	}

	public void displayPlayers() {
		for (Map.Entry<Integer, PlayerStatusPair<Player>> entry : players.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue().getStatus() + " " + entry.getValue().getRole()
					+ " " + entry.getValue().getPlayer().getId());
		}
	}

	public void changeMafiaHP(int initialHP) {
		ArrayList<Player> list = getHPChangeList();
		list.clear();
		for (Map.Entry<Integer, PlayerStatusPair<Mafia>> p : Mafia.getMafiaList().entrySet()) {
			if ((p.getValue().getPlayer() instanceof Mafia) && p.getValue().getStatus() == PlayerStatus.alive
					&& p.getValue().getPlayer().getHP() > 0) {
				list.add(p.getValue().getPlayer());
			}
		}
		Collections.sort(list, new sortByHp());
		if (list.size() != 0) {
			if (list.get(0).getHP() < initialHP) {
				int overRide = 0;
				int i = 0;
				for (Player m : list) {
					int temp = m.getHP();
					m.changeHP((initialHP + overRide) / (list.size() - i));
					int change = ((initialHP + overRide) / (list.size() - i)) - temp;
					overRide = Math.max(0, change);
					i++;
				}
			} else {
				for (Player m : list) {
					m.changeHP(initialHP / list.size());
				}
			}
		}

		ArrayList<Player> list1 = getHPChangeList();
		list1.clear();
		for (Map.Entry<Integer, PlayerStatusPair<Player>> p : getPlayerList().entrySet()) {
			if ((p.getValue().getPlayer() instanceof Mafia) && p.getValue().getStatus() == PlayerStatus.alive
					&& p.getValue().getPlayer().getHP() > 0) {
				list1.add(p.getValue().getPlayer());
			}
		}
		Collections.sort(list1, new sortByHp());
		if (list1.size() != 0) {
			if (list1.get(0).getHP() < initialHP) {
				int overRide = 0;
				int i = 0;
				for (Player m : list1) {
					int temp = m.getHP();
					m.changeHP((initialHP + overRide) / (list1.size() - i));
					int change = ((initialHP + overRide) / (list1.size() - i)) - temp;
					overRide = Math.max(0, change);
					i++;
				}
			} else {
				for (Player m : list1) {
					m.changeHP(initialHP / list1.size());
				}
			}
		}
	}

	public boolean isGameOver(int noOfM, int noOfD, int noOfH, int noOfC) {
		if (noOfM == 0) {
			setWinner(false);
			return true;
		} else if (noOfM == (noOfD + noOfH + noOfC)) {
			setWinner(true);
			return true;
		} else {
			return false;
		}
	}
}
