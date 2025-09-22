import java.util.Objects;

class Game {
    String name;
    int players;

    public Game(String name, int players) {
        this.name = name;
        this.players = players;
    }

    @Override
    public String toString() {
        return "Game{name='" + name + "', players=" + players + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // same reference
        if (!(obj instanceof Game)) return false;
        Game other = (Game) obj;
        return players == other.players && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, players);
    }
}

class CardGame extends Game {
    String deckType;

    public CardGame(String name, int players, String deckType) {
        super(name, players);
        this.deckType = deckType;
    }

    @Override
    public String toString() {
        return super.toString() + ", CardGame{deckType='" + deckType + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CardGame)) return false;
        if (!super.equals(obj)) return false; // compare parent fields
        CardGame other = (CardGame) obj;
        return Objects.equals(deckType, other.deckType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deckType);
    }
}

public class HW3_Test {
    public static void main(String[] args) {
        Game g1 = new Game("Chess", 2);
        Game g2 = new Game("Chess", 2);
        Game g3 = new Game("Football", 22);

        CardGame c1 = new CardGame("Poker", 4, "Standard 52-card");
        CardGame c2 = new CardGame("Poker", 4, "Standard 52-card");
        CardGame c3 = new CardGame("Poker", 4, "Uno Deck");

        System.out.println("--- toString() Tests ---");
        System.out.println(g1);
        System.out.println(c1);

        System.out.println("\n--- equals() Tests ---");
        System.out.println("g1 equals g2? " + g1.equals(g2)); // true
        System.out.println("g1 equals g3? " + g1.equals(g3)); // false
        System.out.println("c1 equals c2? " + c1.equals(c2)); // true
        System.out.println("c1 equals c3? " + c1.equals(c3)); // false

        System.out.println("\n--- hashCode() consistency ---");
        System.out.println("g1.hashCode() = " + g1.hashCode());
        System.out.println("g2.hashCode() = " + g2.hashCode()); // same as g1
        System.out.println("c1.hashCode() = " + c1.hashCode());
        System.out.println("c2.hashCode() = " + c2.hashCode()); // same as c1
    }
}