package com.example.odd.circles;

import java.util.Random;

public class EnemyCircle extends SimpleCircle {

    public static final int TO_RADIUS = 110;
    public static final int FROM_RADIUS = 10;

    public EnemyCircle(int x, int y, int radius) {
        super(x, y, radius);
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());

        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);

        return new EnemyCircle(x,y, radius);

    }
}
