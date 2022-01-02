package robot;

import java.util.Random;

public class Robot {

    private static final int DEFAULT_ROBOT_HEALTH = 100;

    private String name;
    private int health;
    private String damageKeys;


    public Robot(String name) {
        this.name = name;
        this.health = DEFAULT_ROBOT_HEALTH;
        this.damageKeys = calculateDamageKeys();
    }

    private String calculateDamageKeys() {
        String alphabet = "QWEASDZXC";
        Random r = new Random();
        while (alphabet.length() > 5) {
            int randIndex = r.nextInt(alphabet.length());
            char randChar = alphabet.charAt(randIndex);
            alphabet = alphabet.replace(String.valueOf(randChar), "");
        }
        return alphabet;
    }


    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void applyDamage() {
        health = health - 20;
    }

    public boolean hasDamageKey(String damageKey) {
        if (damageKey == null || damageKey.isBlank()) {
            return false;
        }
        return damageKeys.contains(damageKey.toUpperCase());
    }

    @Override
    public String toString() {
        return "Robot with name - " + name + " has health" + ": " + health + ":" + damageKeys;
    }
}

