import java.awt.Color;

public class WhiteTiger extends Critter {
    private boolean hasInfected;

    public Action getMove(CritterInfo info) {
        Neighbor nf = info.getFront();
        Neighbor nr = info.getRight();
        if (nf == Neighbor.OTHER) {
            hasInfected = true;
            return Action.INFECT;
        }
        else if (nf == Neighbor.WALL || nr == Neighbor.WALL) return Action.LEFT;
        else if (nf == Neighbor.SAME) return Action.RIGHT;
        else return Action.HOP;
    }

    public Color getColor() {
        return Color.WHITE;
    }

    public String toString() {
        if (hasInfected) return "TGR";
        else return "tgr";
    }

}
