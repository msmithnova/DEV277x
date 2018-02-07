import java.awt.Color;
import java.util.Random;

public class NinjaCat extends Critter {
    private static final Action[] RL = {Action.LEFT, Action.RIGHT};
    private static final Color[] COLORS = {Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
    private Random rand;

    public NinjaCat() {
        rand = new Random();
    }

    public Action getMove(CritterInfo info) {
        Neighbor nf = info.getFront();
        if (nf == Neighbor.OTHER) return Action.INFECT;
        else if (nf == Neighbor.WALL || nf == Neighbor.SAME) return RL[rand.nextInt(RL.length)];
        else return Action.HOP;
    }

    public Color getColor() {
        return COLORS[rand.nextInt(COLORS.length)];
    }

    public String toString() {
        return ">:)";
    }

}
