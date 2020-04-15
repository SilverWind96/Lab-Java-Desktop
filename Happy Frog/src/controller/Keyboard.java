/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author quang
 */
public class Keyboard implements KeyListener {

    private boolean press = false;
    private boolean release = true;

    public boolean isPress() {
        return press;
    }

    public void setPress(boolean press) {
        this.press = press;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            if (release) {
                release = false;
                press = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        release = true;
    }

}
