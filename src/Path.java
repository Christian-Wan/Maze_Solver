import java.util.ArrayList;
import java.util.Arrays;

public class Path {
    public Path() {}

    public static ArrayList<String> solver(String[][] map, int startX, int startY, int beenX, int beenY, ArrayList<String> solution) {
        beenX = startX;
        beenY = startY;

        int lastIndex = solution.size() - 1;

        while (!(startY == map.length - 1 && startX == map[0].length - 1)) {
            //This prints out the current state of the maze
//            System.out.println();
//            for (String[] row: map) {
//                for (String thing: row) {
//                    System.out.print(thing);
//                }
//                System.out.println();
//            }
//            System.out.println(solution);

            Looker look = lookAround(map, startX, startY, beenX, beenY);
//            System.out.println(startX + " " + startY + " " + look.getPaths());
            if (look.getPaths() == 1) {
                if (look.contains("UP")) {
                    map[startY][startX] = "V";
                    startY--;
                }
                if (look.contains("RIGHT")) {
                    map[startY][startX] = "V";
                    startX++;
                }
                if (look.contains("DOWN")) {
                    map[startY][startX] = "V";
                    startY++;
                }
                if (look.contains("LEFT")) {
                    map[startY][startX] = "V";
                    startX--;
                }
                solution.add("(" + startY + ", " + startX + ")");
            }
            else if (look.getPaths() == 0) {
//                System.out.println("ASDKJHADSKASDH");
                int size = solution.size();
                for (int i = lastIndex; i < size; i++) {
                    solution.remove(lastIndex);
                }
                return null;
            }
            else {
//                System.out.println(startY + " " + startX);
//                System.out.println(Arrays.toString(look.getDirections()));
                if (look.contains("UP")) {
//                    System.out.println("UP");
                    map[startY][startX] = "V";
                    solution.add("(" + (startY - 1) + ", " + startX + ")");

                    solver(map, startX, startY - 1, startX, startY, solution);
                }
                else if (look.contains("RIGHT")) {
//                    System.out.println("RIGHT");
                    map[startY][startX] = "V";
                    solution.add("(" + startY + ", " + (startX + 1)+ ")");
                    solver(map, startX + 1, startY, startX, startY, solution);
                }
                else if (look.contains("DOWN")) {
//                    System.out.println("DOWN");
                    map[startY][startX] = "V";
                    solution.add("(" + (startY + 1) + ", " + startX + ")");
                    solver(map, startX, startY + 1, startX, startY, solution);
                }
                else if (look.contains("LEFT")) {
//                    System.out.println("LEFT");
                    map[startY][startX] = "V";
                    solution.add("(" + startY + ", " + (startX - 1) + ")");
                    solver(map, startX - 1, startY, startX, startY, solution);
                }
            }
        }
        System.out.println(solution);
        return null;
    }

    private static Looker lookAround(String[][] map, int startX, int startY, int beenX, int beenY) { //this bad
        int count = 0;
        String[] directions = {"", "", "", ""};
        try { //up
            if (map[startY - 1][startX].equals(".")) {
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
            if (map[startY + 1][startX].equals(".")) {
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
