package AP_Assignment3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

abstract class Player {
	private final int id;
	private int hp;

	public Player(int id, int hp) {
		this.id = id;
		this.hp = hp;
	}

	public int getId() {
		return this.id;
	}

	public int getHP() {
		return this.hp;
	}

	public void changeHP(int change) {
		this.hp = Math.max(0, hp - change);
	}

	public abstract void printCategoryPlayers();

//	public abstract void addPlayer(int key, PlayerStatusPair<T> p);

	@Override
	public abstract boolean equals(Object o);
}

class sortByHp implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return o1.getHP() - o2.getHP();
	}

}

class sortByVotes implements Comparator<PlayerVotesPair> {

	@Override
	public int compare(PlayerVotesPair o1, PlayerVotesPair o2) {
		// TODO Auto-generated method stub
		return o2.getVote() - o1.getVote();
	}

}

class Mafia extends Player {
	public Mafia(int id) {
		super(id, 2500);
		// TODO Auto-generated constructor stub
	}

	private static Map<Integer, PlayerStatusPair<Mafia>> mafias = new HashMap<Integer, PlayerStatusPair<Mafia>>();

	public static Map<Integer, PlayerStatusPair<Mafia>> getMafiaList() {
		return mafias;
	}

	public static void addPlayer(int key, PlayerStatusPair<Mafia> m) {
		mafias.put(key, m);
	}

	public static int calculateTotalHP() {
		int totalHP = 0;
		for (Map.Entry<Integer, PlayerStatusPair<Mafia>> m : mafias.entrySet()) {
			if (m.getValue().getStatus() == PlayerStatus.alive) {
				totalHP += m.getValue().getPlayer().getHP();
			}
		}
		return totalHP;
	}

	public static int positiveAlive() {
		int ans = 0;
		for (Map.Entry<Integer, PlayerStatusPair<Mafia>> m : mafias.entrySet()) {
			if (m.getValue().getStatus() == PlayerStatus.alive && m.getValue().getPlayer().getHP() > 0) {
				ans++;
			}
		}
		return ans;
	}

	@Override
	public void printCategoryPlayers() {
		// TODO Auto-generated method stub
		System.out.print("You are a mafia. Other mafias are: [");
		for (Map.Entry<Integer, PlayerStatusPair<Mafia>> entry : getMafiaList().entrySet()) {
			if (!equals(entry.getValue().getPlayer())) {
				System.out.print("Player" + entry.getValue().getPlayer().getId() + "], [");
			}
		}
		System.out.println("]");
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o != null && getClass() == o.getClass()) {
			Mafia m = (Mafia) o;
			if (getId() == m.getId()) {
				return true;
			}
		}
		return false;
	}

	public static void printMafias() {
		for (Map.Entry<Integer, PlayerStatusPair<Mafia>> pair : getMafiaList().entrySet()) {
			System.out.print("Player" + pair.getValue().getPlayer().getId() + ", ");
		}
		System.out.println("were Mafia");
	}

}

class Detective extends Player implements Comparable<Detective> {
	public Detective(int id) {
		super(id, 800);
		// TODO Auto-generated constructor stub
	}

	private static Map<Integer, PlayerStatusPair<Detective>> detectives = new HashMap<Integer, PlayerStatusPair<Detective>>();

	public static void addPlayer(int key, PlayerStatusPair<Detective> d) {
		detectives.put(key, d);
	}

	public static Map<Integer, PlayerStatusPair<Detective>> getDetectiveList() {
		return detectives;
	}

	@Override
	public void printCategoryPlayers() {
		// TODO Auto-generated method stub
		System.out.print("You are a detective. Other detectives are: [");
		for (Map.Entry<Integer, PlayerStatusPair<Detective>> entry : getDetectiveList().entrySet()) {
			if (!equals(entry.getValue().getPlayer())) {
				System.out.print("Player" + entry.getValue().getPlayer().getId() + "], [");
			}
		}
		System.out.println("]");
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o != null && getClass() == o.getClass()) {
			Detective d = (Detective) o;
			if (getId() == d.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Detective d) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void printDetectives() {
		for (Map.Entry<Integer, PlayerStatusPair<Detective>> pair : getDetectiveList().entrySet()) {
			System.out.print("Player" + pair.getValue().getPlayer().getId() + ", ");
		}
		System.out.println("were detectives");
	}

}

class Healer extends Player {
	public Healer(int id) {
		super(id, 800);
		// TODO Auto-generated constructor stub
	}

	private static Map<Integer, PlayerStatusPair<Healer>> healers = new HashMap<Integer, PlayerStatusPair<Healer>>();

	public static void addPlayer(int key, PlayerStatusPair<Healer> h) {
		healers.put(key, h);
	}

	public static Map<Integer, PlayerStatusPair<Healer>> getHealerList() {
		return healers;
	}

	@Override
	public void printCategoryPlayers() {
		// TODO Auto-generated method stub
		System.out.print("You are a Healer. Other healers are: [");
		for (Map.Entry<Integer, PlayerStatusPair<Healer>> entry : getHealerList().entrySet()) {
			if (!equals(entry.getValue().getPlayer())) {
				System.out.print("Player" + entry.getValue().getPlayer().getId() + "], [");
			}
		}
		System.out.println("]");
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o != null && getClass() == o.getClass()) {
			Healer h = (Healer) o;
			if (getId() == h.getId()) {
				return true;
			}
		}
		return false;
	}

	public static void printHealers() {
		for (Map.Entry<Integer, PlayerStatusPair<Healer>> pair : getHealerList().entrySet()) {
			System.out.print("Player" + pair.getValue().getPlayer().getId() + ", ");
		}
		System.out.println("were healers");
	}
}

class Commoner extends Player {
	public Commoner(int id) {
		super(id, 1000);
		// TODO Auto-generated constructor stub
	}

	private static Map<Integer, PlayerStatusPair<Commoner>> commoners = new HashMap<Integer, PlayerStatusPair<Commoner>>();

	public static void addPlayer(int key, PlayerStatusPair<Commoner> c) {
		commoners.put(key, c);
	}

	public static Map<Integer, PlayerStatusPair<Commoner>> getCommonerList() {
		return commoners;
	}

	@Override
	public void printCategoryPlayers() {
		// TODO Auto-generated method stub
		System.out.print("You are a commoner. Other commoners are: [");
		for (Map.Entry<Integer, PlayerStatusPair<Commoner>> entry : getCommonerList().entrySet()) {
			if (!equals(entry.getValue().getPlayer())) {
				System.out.print("Player" + entry.getValue().getPlayer().getId() + "], [");
			}
		}
		System.out.println("]");
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o != null && getClass() == o.getClass()) {
			Commoner c = (Commoner) o;
			if (getId() == c.getId()) {
				return true;
			}
		}
		return false;
	}

	public static void printCommoners() {
		for (Map.Entry<Integer, PlayerStatusPair<Commoner>> pair : getCommonerList().entrySet()) {
			System.out.print("Player" + pair.getValue().getPlayer().getId() + ", ");
		}
		System.out.println("were commoners");
	}

}