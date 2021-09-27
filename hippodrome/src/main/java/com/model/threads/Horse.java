package com.model.threads;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author Viktor Makarov
 */
public class Horse implements Runnable {
    private final Phaser phaser;

    private String name;

    private int speed;

    private int distance = 0;

    private int distanceCounter = 0;

    private final int cyclesToRun;

    public Horse(String name, int speed, int cyclesToRun, Phaser phaser) {
        this.name = name;
        this.speed = speed;
        this.cyclesToRun = cyclesToRun;
        this.phaser = phaser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        phaser.register();
        while (!phaser.isTerminated()) {
            print();
            move();
            if (phaser.getPhase() == cyclesToRun) {
                phaser.arriveAndDeregister();
            } else {
                phaser.arriveAndAwaitAdvance();
            }
        }
        System.out.println(name + " " + getTotalDistance());
    }

    public void print() {
        for (int i = 0; i < distance; i++) {
            System.out.print(".");
        }
        System.out.println(name);
    }

    public void move() {
        distance += speed;
        if (distance > 200) {
            distance -= 200;
            distanceCounter++;
        }
        speed = new Random().nextInt(10) + 1;
    }

    public int getTotalDistance() {
        return (distanceCounter * 200) + distance;
    }
}
