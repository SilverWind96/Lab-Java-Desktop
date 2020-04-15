/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author quang
 */
public class Data {

    private int xFrog;
    private int yFrog;
    private int xPipe1;
    private int xPipe2;
    private int score;
    private int speed;
    private int gravity;
    private int space1;
    private int space2;
    private int yPipe1;
    private int yPipe2;

    public Data(int yFrog, int xPipe1, int xPipe2, int score, int speed, int gravity, int space1, int space2, int yPipe1, int yPipe2) {
        this.yFrog = yFrog;
        this.xPipe1 = xPipe1;
        this.xPipe2 = xPipe2;
        this.score = score;
        this.speed = speed;
        this.gravity = gravity;
        this.space1 = space1;
        this.space2 = space2;
        this.yPipe1 = yPipe1;
        this.yPipe2 = yPipe2;
    }

    public int getyPipe1() {
        return yPipe1;
    }

    public void setyPipe1(int yPipe1) {
        this.yPipe1 = yPipe1;
    }

    public int getyPipe2() {
        return yPipe2;
    }

    public void setyPipe2(int yPipe2) {
        this.yPipe2 = yPipe2;
    }

    public int getSpace1() {
        return space1;
    }

    public void setSpace1(int space1) {
        this.space1 = space1;
    }

    public int getSpace2() {
        return space2;
    }

    public void setSpace2(int space2) {
        this.space2 = space2;
    }

    public Data() {
    }

    public int getxFrog() {
        return xFrog;
    }

    public void setxFrog(int xFrog) {
        this.xFrog = xFrog;
    }

    public int getyFrog() {
        return yFrog;
    }

    public void setyFrog(int yFrog) {
        this.yFrog = yFrog;
    }

    public int getxPipe1() {
        return xPipe1;
    }

    public void setxPipe1(int xPipe1) {
        this.xPipe1 = xPipe1;
    }

    public int getxPipe2() {
        return xPipe2;
    }

    public void setxPipe2(int xPipe2) {
        this.xPipe2 = xPipe2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void addYFrog(int bound) {
        this.yFrog += bound;
    }

    public void subYFrog(int bound) {
        this.yFrog -= bound;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

}
