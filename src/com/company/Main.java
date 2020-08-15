package com.company;

import java.util.Random;

public class Main {


    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {250, 260, 270};
    public static int[] heroesDamage = {20, 15, 25};
    public static int medicHealth = 500;
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic"};
    public static String medicHelp = " ";
    public static int roundNumber = 0;

    public static void main(String[] args) {
        System.out.println("Game start");
        printStatistics();
        while (!isGameFinished()) {

            roundNumber++;
            System.out.println("__________________");
            System.out.println("Round" + roundNumber);
            round();
        }
    }


    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose" + " " + bossDefenceType);
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHits();
        }
        printStatistics();
    }


    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }

        }
        if (allHeroesDead) {
            System.out.println("Boss won");
        }

        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossHealth > 0) {
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random r = new Random();
                        int coef = r.nextInt(10);
                        if (bossHealth - heroesDamage[i] * coef < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i] * coef;
                        }
                        System.out.println("Critical attack:" + (heroesDamage[i] + coef));
                    } else {
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i];
                        }
                    }
                } else {
                    break;
                }

            }
        }
    }

    public static void medicalTreatment() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100) {
                if (medicHealth > 0) {
                    Random r = new Random();
                    int randomIndex = r.nextInt(heroesHealth.length);
                    medicHelp = heroesAttackType[randomIndex];
                    System.out.println("Medic chose" + " " + heroesAttackType[i]);
                } else {
                    medicHealth = heroesHealth[i] - medicHealth;
                }


            }

        }

    }


    public static void printStatistics() {
        System.out.println("__________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Medic health: " + medicHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " " + "Health: " + heroesHealth[i]);


        }

    }

}



