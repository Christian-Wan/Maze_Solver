import java.util.ArrayList;
import java.util.Arrays;

public class Path {
    public Path() {}

    public static ArrayList<String> solver(String[][] map, int startX, int startY, ArrayList<String> solution) {


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

            Looker look = lookAround(map, startX, startY);
//            System.out.println(startX + " " + startY + " " + look.getPaths());
            if (look.getPaths() == 1) {
                map[startY][startX] = "V";
                String newCords = updateCords(startX, startY, look);
                startX = Integer.parseInt(newCords.substring(0, newCords.indexOf(":")));
                startY = Integer.parseInt(newCords.substring(newCords.indexOf(":") + 1));
                solution.add("(" + startY + ", " + startX + ")");
            }
            else if (look.getPaths() == 0) {
//                System.out.println("ASDKJHADSKASDH");
                map[startY][startX] = "V";
                int size = solution.size();
                for (int i = lastIndex; i < size; i++) {
                    solution.remove(lastIndex);
                }
                return null;
            }
            else {
//                System.out.println(startY + " " + startX);
//                System.out.println(Arrays.toString(look.getDirections()));
                map[startY][startX] = "V";
                if (look.contains("UP")) {
//                    System.out.println("UP");
                    solution.add("(" + (startY - 1) + ", " + startX + ")");
                    solver(map, startX, startY - 1, solution);
                }
                if (look.contains("RIGHT")) {
//                    System.out.println("RIGHT");
                    solution.add("(" + startY + ", " + (startX + 1)+ ")");
                    solver(map, startX + 1, startY, solution);
                }
                if (look.contains("DOWN")) {
//                    System.out.println("DOWN");
                    solution.add("(" + (startY + 1) + ", " + startX + ")");
                    solver(map, startX, startY + 1, solution);
                }
                if (look.contains("LEFT")) {
//                    System.out.println("LEFT");
                    solution.add("(" + startY + ", " + (startX - 1) + ")");
                    solver(map, startX - 1, startY, solution);
                }

            }
        }
        System.out.println(solution);
        return null;
    }


    public static ArrayList<String> nonRecursiveSolver(String[][] map, int startX, int startY, ArrayList<String> solution) {
        solution.add("(0, 0)");
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

            Looker look = lookAround(map, startX, startY);
//            System.out.println(startX + " " + startY + " " + look.getPaths());
            if (look.getPaths() >= 1) {
                map[startY][startX] = "V";
                String newCords = updateCords(startX, startY, look);
                startX = Integer.parseInt(newCords.substring(0, newCords.indexOf(":")));
                startY = Integer.parseInt(newCords.substring(newCords.indexOf(":") + 1));

                solution.add("(" + startY + ", " + startX + ")");
            }
            else if (look.getPaths() == 0) {
//                System.out.println("ASDKJHADSKASDH");
                map[startY][startX] = "X";
                startY = 0;
                startX = 0;
                int size = solution.size();
                for (int i = lastIndex; i < size; i++) {
                    solution.remove(lastIndex);
                }
                for (int r = 0; r < map.length; r++) {
                    for (int c = 0; c < map[0].length; c++) {
                        if (map[r][c].equals("V")) {
                            map[r][c] = ".";
                        }
                    }
                }
            }
        }
        return solution;
    }




    private static Looker lookAround(String[][] map, int startX, int startY) { //this bad
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

    private static String updateCords(int startX, int startY, Looker look) {
        if (look.contains("UP")) {
            startY--;
        }
        else if (look.contains("RIGHT")) {
            startX++;
        }
        else if (look.contains("DOWN")) {
            startY++;
        }
        else if (look.contains("LEFT")) {
            startX--;
        }
        return startX + ":" + startY;
    }
}
