import java.awt.Color;

public class Bear extends Critter {
    private boolean polar;
    private String repr = "/";

    public Bear(boolean polar) {
        this.polar = polar;
    }

    public Action getMove(CritterInfo info) {
        Neighbor n = info.getFront();
        if (n == Neighbor.OTHER) return Action.INFECT;
        else if (n == Neighbor.EMPTY) return Action.HOP;
        else return Action.LEFT;
    }

    public Color getColor() {
        if (polar) return Color.WHITE;
        else return Color.BLACK;
    }

    public String toString() {
        if (repr.equals("/")) repr = "\\";
        else repr =  "/";
        return repr;
    }

}
