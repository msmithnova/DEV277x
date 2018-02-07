import java.awt.Color;

public class Giant extends Critter {
    private static final String[] REPR = {"fee", "fie", "foe", "fum"};
    private int moves;

    public Action getMove(CritterInfo info) {
        moves++;
        moves %= 24;
        Neighbor nf = info.getFront();
        if (nf == Neighbor.OTHER) return Action.INFECT;
        else if (nf == Neighbor.EMPTY) return Action.HOP;
        else return Action.RIGHT;
    }

    public Color getColor() {
        return Color.GRAY;
    }

    public String toString() {
        String repr = REPR[moves / 6];
        return repr;
    }

}
