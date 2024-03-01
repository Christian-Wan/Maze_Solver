public class Looker {
    int paths;
    String[] directions;

    public Looker(int paths, String[] directions) {
        this.paths = paths;
        this.directions = directions;
    }

    public int getPaths() {
        return paths;
    }

    public String[] getDirections() {
        return directions;
    }

    public boolean contains(String direction) {
        for (String word: directions) {
            if (word.equals(direction)) {
                return true;
            }
        }
        return false;
    }
}
