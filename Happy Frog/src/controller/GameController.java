/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author quang
 */
public class GameController {

    private formMain main;
    private JButton TPipe1 = new JButton();
    private JButton TPipe2 = new JButton();
    private JButton BPipe1 = new JButton();
    private JButton BPipe2 = new JButton();
    private JButton Frog = new JButton();
    private Keyboard kb;
    private Data data = new Data();
    private FileWorker fileWorker = new FileWorker();
    Timer t;
    boolean inOut = false;
    int pWitdh;
    int pHeight;
    int timetemp = 0;

    public GameController(formMain main) {
        this.main = main;
        pWitdh = main.getPanelMain().getWidth();
        pHeight = main.getPanelMain().getHeight();
        data.setxFrog(50);

        dataInitialize();

        TPipe1.setBackground(Color.GREEN);
        TPipe2.setBackground(Color.GREEN);
        BPipe1.setBackground(Color.GREEN);
        BPipe2.setBackground(Color.GREEN);
        TPipe1.setFocusable(false);
        TPipe2.setFocusable(false);
        BPipe1.setFocusable(false);
        BPipe2.setFocusable(false);

        Frog.setFocusable(false);
        Frog.setBorderPainted(false);
        Frog.setContentAreaFilled(false);
        Frog.setFocusPainted(false);
        Frog.setOpaque(false);

        kb = new Keyboard();
        main.getBtnStart().addKeyListener(kb);
        main.getBtnPause().addKeyListener(kb);
        main.getBtnExit().addKeyListener(kb);
        main.getBtnSave().addKeyListener(kb);
        try {
            Image img = ImageIO.read(getClass().getResource("bird.png"));
            Frog.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dataInitialize() {
        ArrayList<Integer> value = fileWorker.load();
        if (value != null) {
            int reply = JOptionPane.showConfirmDialog(null, "Save file found, do you want to load it?", "", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                data = new Data(value.get(0), value.get(1), value.get(2), value.get(3), value.get(4), value.get(5), value.get(6), value.get(7), value.get(8), value.get(9));
            } else {
                data = new Data(pHeight / 2, 0, 0 - (pWitdh / 2), 0, 3, 2, 150, 150, randomY(), randomY());
            }
            fileWorker.delete();
        } else {
            data = new Data(pHeight / 2, 0, 0 - (pWitdh / 2), 0, 3, 2, 150, 150, randomY(), randomY());
        }
    }

    public void setLocationPipes() {
        if (data.getxPipe1() >= (pWitdh + 100)) {
            data.setxPipe1(0);
            inOut = false;
            if (data.getSpace1() > 100) {
                data.setSpace1(data.getSpace1() - 5);
            }
        }
        if (data.getxPipe1() == 0) {
            data.setyPipe1(randomY());
        }
        if (data.getxPipe2() >= (pWitdh + 100)) {
            data.setxPipe2(0);
            inOut = false;
            if (data.getSpace2() > 100) {
                data.setSpace2(data.getSpace2() - 5);
            }
        }
        if (data.getxPipe2() == 0) {
            data.setyPipe2(randomY());
        }
    }

    public void addPipes() {
        Frog.setBounds(50, data.getyFrog(), 40, 40);
        main.getjLabel2().add(Frog);
        setLocationPipes();
        BPipe1.setBounds(pWitdh - data.getxPipe1(), data.getyPipe1(), 100, pHeight - data.getyPipe1());
        TPipe1.setBounds(pWitdh - data.getxPipe1(), 0, 100, data.getyPipe1() - data.getSpace1());
        BPipe2.setBounds(pWitdh - data.getxPipe2(), data.getyPipe2(), 100, pHeight - data.getyPipe2());
        TPipe2.setBounds(pWitdh - data.getxPipe2(), 0, 100, data.getyPipe2() - data.getSpace2());
        main.getjLabel2().add(TPipe1);
        main.getjLabel2().add(BPipe1);
        main.getjLabel2().add(TPipe2);
        main.getjLabel2().add(BPipe2);
        data.setxPipe1(data.getxPipe1() + data.getSpeed());
        data.setxPipe2(data.getxPipe2() + data.getSpeed());
    }

    public int randomY() {
        return ThreadLocalRandom.current().nextInt(200, pHeight - 100);
    }

    public void run() {
        t = new Timer(15, ((ae) -> {
            getScore();
            if (kb.isPress() == true) {
                move();
                kb.setPress(false);
                data.setGravity(2);
                timetemp = 0;
            } else {
                data.addYFrog(data.getGravity());
                if (timetemp == 25) {
                    data.setGravity(data.getGravity() + 1);
                    timetemp = 0;
                }
                timetemp++;
            }
            addPipes();
            if (checkCollision()) {
                t.stop();
            }
        }));
        t.start();
    }

    public boolean checkCollision() {
        return Frog.getBounds().intersects(BPipe1.getBounds())
                || Frog.getBounds().intersects(BPipe2.getBounds())
                || Frog.getBounds().intersects(TPipe1.getBounds())
                || Frog.getBounds().intersects(TPipe2.getBounds())
                || data.getyFrog() <= 0
                || data.getyFrog() + 40 >= pHeight;
    }

    private void move() {
        data.subYFrog(40);
    }

    public void start() {
        main.getLblScore().setText("Score: 0");
        main.getBtnPause().setText("Pause");
        dataInitialize();
        t.restart();
    }

    public void getScore() {
        if (((data.getxFrog() >= (pWitdh - data.getxPipe2() + 100))
                || (data.getxFrog() >= (pWitdh - data.getxPipe1() + 100))) && inOut == false) {
            data.setScore(data.getScore() + 1);
            main.getLblScore().setText("Score: " + data.getScore());
            inOut = true;
        }
        if (data.getScore() >= 10) {
            data.setSpeed(3 + data.getScore() / 10);
        }
    }

    void stop() {
        if (main.getBtnPause().getText().equals("Resume")) {
            main.getBtnPause().setText("Pause");
            t.start();
        } else {
            t.stop();
            main.getBtnPause().setText("Resume");
        }
    }

    public void save() {
        fileWorker.save(data);
    }
}
