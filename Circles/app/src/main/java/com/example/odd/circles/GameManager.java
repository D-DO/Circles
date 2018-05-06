package com.example.odd.circles;

import android.widget.Toast;

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

    private void initMainCircle() {
        mainCircle = new MainCircle(width / 2, height / 2);
    }

    private void initEnemyCircles() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        enemyCircles = new ArrayList<EnemyCircle>();
        for (int i = 0; i < AMOUNT_ENEMY_CIRCLES; i++) {
            EnemyCircle circle;

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


    public void onDraw() {
        canvasView.drawCicle(mainCircle);
        for (EnemyCircle circle : enemyCircles) {
            canvasView.drawCicle(circle);

        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x, y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle circleForDel = null;
        for (EnemyCircle circle : enemyCircles) {
            if (mainCircle.isIntersect(circle)){

                if (circle.isSmallerThan(mainCircle)){
                    mainCircle.growRadius(circle);
                    circleForDel = circle;
                    calculateAndSetCirclesColor();
                } else {
                gameEnd("OOPS!");
                return;
                }

            }

        }
//            for (EnemyCircle circle: enemyCircles) {
//                if (circleForDel == circle){
//                    enemyCircles.remove(circle);
//                }
//            }

            if (circleForDel != null){
            enemyCircles.remove(circleForDel);
            }

            if (enemyCircles.isEmpty()){
            gameEnd("YOU WIN!");
            }
    }

    private void gameEnd(String text) {
         canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.reDraw();
    }


    private void moveCircles() {
        for (EnemyCircle circle : enemyCircles) {
            circle.moveOneStep();
        }
    }
}
