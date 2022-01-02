package robot;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainClass {

    private static final int ROBOTS_AMOUNT = 2;

    private static Robot createRobot(Scanner in, int i, Set<String> names) {
        boolean uniqueName;
        String name;
        do {
            System.out.print("Input name for robot " + (i + 1) + ": ");
            name = in.nextLine().toUpperCase();

            if (names.contains(name)) {
                System.out.println("Please enter another name. This name already exists");
                uniqueName = false;
            } else {
                uniqueName = true;
            }
        } while (!uniqueName);

        names.add(name);

        return new Robot(name);
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Robot[] robots = new Robot[ROBOTS_AMOUNT];
        Set<String> names = new HashSet<>();

        for (int i = 0; i < ROBOTS_AMOUNT; i++) {
            robots[i] = createRobot(in, i, names);
        }

        boolean gameTerminated = false;
        while (robots[0].getHealth() > 0 && robots[1].getHealth() > 0 && !gameTerminated) {
            for (int i = 0; i < robots.length && !gameTerminated; i++) {
                System.out.print("Press key from [Q], [W], [E], [A], [S], [D], [Z], [X], [C] to shot in robot "
                        + (i + 1) + " or \n for exit press [L]: ");
                String inputKey = in.nextLine().toUpperCase().trim();
                if (inputKey.length() > 1) {
                    inputKey = String.valueOf(inputKey.charAt(0));
                }

                if ("L".equals(inputKey)) {
                    gameTerminated = true;
                } else {
                    boolean hasDamageKey = robots[i].hasDamageKey(inputKey);
                    if (hasDamageKey) {
                        robots[i].applyDamage();
                        System.out.println("Good shot!");
                    }
                }
            }
            for (int i = 0; i < robots.length; i++) {
                System.out.println(robots[i].getName() + " has health - " + robots[i].getHealth());

            }

        }

        if (!gameTerminated) {
            showResults(robots);
        }

        in.close();
    }

    private static void showResults(Robot[] robots) {
        if (robots[0].getHealth() == 0 && robots[1].getHealth() == 0) {
            System.out.println("The game ended in a draw!");
        } else if (robots[0].getHealth() > 0) {
            System.out.printf("Congratulation, %s! Robot %s - was killed!", robots[0].getName(), robots[1].getName());
        } else {
            System.out.printf("Congratulation, %s! Robot %s - was killed!", robots[1].getName(), robots[0].getName());
        }
    }
}
