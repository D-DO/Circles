package com.example.odd.circles;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class GameManager {
    public static final int AMOUNT_ENEMY_CIRCLES = 10;
    private ArrayList<EnemyCircle> enemyCircles;
    private MainCircle mainCircle;
    private CanvasView canvasView;
    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w;
        height = h;
        initMainCircle();
        initEnemyCircles();

    }

    private void initEnemyCircles() {
        enemyCircles = new ArrayList<EnemyCircle>();
        for (int i = 0; i < AMOUNT_ENEMY_CIRCLES; i++) {
            EnemyCircle circle;

            SimpleCircle mainCircleArea = mainCircle.getCircleArea();
            do {
                circle = EnemyCircle.getRandomCircle();
            } while (circle.isIntersect(mainCircleArea));

            enemyCircles.add(circle);
        }
        calculateAndSetCirclesColor();
    }


    private void calculateAndSetCirclesColor() {
        for (EnemyCircle enemyCircle : enemyCircles) {
            enemyCircle.setEnemyOrFoodColorDependsOf(mainCircle);
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private void initMainCircle() {
        mainCircle = new MainCircle(width / 2, height / 2);
    }

    public void onDraw() {
        canvasView.drawCicle(mainCircle);
        for (EnemyCircle circle : enemyCircles) {
            canvasView.drawCicle(circle);

        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x, y);
    }
}
