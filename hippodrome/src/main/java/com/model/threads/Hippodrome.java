package com.model.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Phaser;

/**
 * @author Viktor Makarov
 */
public class Hippodrome {
    private final static String SPACES = "\n\n\n\n\n\n\n\n";

    private final int cyclesNeeded = 10;

    private final Phaser phaser;

    private List<Thread> horseThreads;

    public Hippodrome(Phaser phaser) {
        this.phaser = phaser;
    }

    public static void main(String[] args) throws InterruptedException {
        Hippodrome game = new Hippodrome(new Phaser());

        game.horseThreads = new ArrayList<>();

        Horse horseOne = new Horse("Billy", 3, game.getCyclesNeeded(), game.getPhaser());
        Horse horseTwo = new Horse("Willy", 3, game.getCyclesNeeded(), game.getPhaser());
        Horse horseThree = new Horse("Dilly", 3, game.getCyclesNeeded(), game.getPhaser());

        game.horseThreads.add(new Thread(horseOne));
        game.horseThreads.add(new Thread(horseTwo));
        game.horseThreads.add(new Thread(horseThree));

        System.out.println("start");
        game.gameStart();

        Thread.sleep(1000);
        System.out.println("end");
        System.out.println(SPACES);
        Horse winner = game.getWinner(horseOne, horseTwo, horseThree);
        System.out.println("Winner is: " + winner.getName() + " distance: " + winner.getTotalDistance());
    }


    private void gameStart() throws InterruptedException {
        phaser.register();
        horseThreads.forEach(Thread::start);
        for (int i = 0; i < cyclesNeeded; i++) {
            System.out.println(SPACES);
            System.out.println("Current cycle: " + phaser.getPhase());
            Thread.sleep(200);
            phaser.arriveAndAwaitAdvance();
        }
        phaser.arriveAndDeregister();
    }

    private Horse getWinner(Horse... horses) {
        return Arrays.stream(horses).max(Comparator.comparingInt(Horse::getTotalDistance)).get();
    }

    public int getCyclesNeeded() {
        return cyclesNeeded;
    }

    public Phaser getPhaser() {
        return phaser;
    }
}
