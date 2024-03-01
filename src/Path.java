import java.util.ArrayList;

public class Path {
    public Path() {}

    public static ArrayList<String> solver(String[][] map, int startX, int startY, int beenX, int beenY, ArrayList<String> solution) {
        beenX = startX;
        beenY = startY;

        while (!(startY == map.length - 1 && startX == map[0].length - 1)) {
//            System.out.println();
//            for (String[] row: map) {
//                for (String thing: row) {
//                    System.out.print(thing);
//                }
//                System.out.println();
//            }

            Looker look = lookAround(map, startX, startY, beenX, beenY);
//            System.out.println(startX + " " + startY + " " + look.getPaths());
            if (look.getPaths() == 1) {
                if (look.contains("UP")) {
                    map[startY][startX] = "V";
                    startY++;
                }
                if (look.contains("RIGHT")) {
                    map[startY][startX] = "V";
                    startX++;
                }
                if (look.contains("DOWN")) {
                    map[startY][startX] = "V";
                    startY--;
                }
                if (look.contains("LEFT")) {
                    map[startY][startX] = "V";
                    startX--;
                }
            }
            solution.add("(" + startY + ", " + startX + ")");
        }
        return solution;
    }

    private static Looker lookAround(String[][] map, int startX, int startY, int beenX, int beenY) { //this bad
        int count = 0;
        String[] directions = {"", "", "", ""};
        try { //up
            if (map[startY + 1][startX].equals(".")) {
                count++;
                directions[0] = "UP";
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        try { //right
            if (map[startY][startX + 1].equals(".")) {
                count++;
                directions[1] = "RIGHT";
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        try { //down
            if (map[startY - 1][startX].equals(".")) {
                count++;
                directions[2] = "DOWN";
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        try { //left
            if (map[startY][startX - 1].equals(".")) {
                count++;
                directions[3] = "LEFT";
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        return new Looker(count, directions);
    }

}
