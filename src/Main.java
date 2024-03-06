import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] map = getMaze("Data/Map");
        ArrayList<String> solution = new ArrayList<>();
        String[][] mapCopy = new String[map.length][map[0].length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                mapCopy[r][c] = map[r][c];
            }
        }
        solution.add("(0, 0)");
        System.out.println(Path.nonRecursiveSolver(map, 0, 0, solution));
//        for (String[] row: map) {
//            for (String thing: row) {
//                System.out.print(thing);
//            }
//            System.out.println();
//        }
        solution.clear();
        solution.add("(0, 0)");
        System.out.println("ASD");
        Path.solver(mapCopy, 0, 0, solution);
        for (String[] row: mapCopy) {
            for (String thing: row) {
                System.out.print(thing);
            }
            System.out.println();
        }
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;

    }

}