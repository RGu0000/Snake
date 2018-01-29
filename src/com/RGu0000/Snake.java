package com.RGu0000;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class Snake extends JPanel implements ActionListener {
    private int sizeWidth;
    private int sizeHeight;
    private int offsetWidth;
    private int offsetHeight;
    private int scale;
    private ArrayList<Point> snakeLocation;
    private static Point food;
    private String direction = "RIGHT";
    private String tmpDirection = "RIGHT";
    private static final Snake snake = new Snake();
    private Boolean isPaused = false;
    private Boolean isAlive = false;
    private Timer timer;
    private Board board;
    private Buttons buttons;
    private JFrame frame;
    private Integer score=0;
    private int speed=5;

    private Snake() {}

    static Snake getInstance() {
        return snake;
    }

    void createBoard() {
        frame = new JFrame("Typical Snake Game");
        snakeLocation = new ArrayList<>();
        snakeLocation.add(new Point(-100,-100));
        food=new Point(-100,-100);
        board = new Board();
        sizeWidth=board.getSizeWidth();
        sizeHeight=board.getSizeHeight();
        offsetHeight=board.getOffsetHeight();
        offsetWidth=board.getOffsetWidth();
        scale=board.getScale();
        buttons = new Buttons();
        frame.getContentPane().add(BorderLayout.CENTER, board);
        frame.getContentPane().add(BorderLayout.SOUTH, buttons);
        frame.setPreferredSize(new Dimension(sizeWidth + 2 * offsetWidth, sizeHeight + 2 * offsetHeight + 50));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void startGame() {
        Integer delay = 100 + (5 - speed) * 15;
        timer = new Timer(delay, this);
        if(frame==null){
            snake.createBoard();
        }
        score=0;
        direction="RIGHT";
        snakeLocation.clear();

        for(int i=0;i<6;i++){
            snakeLocation.add(new Point(Math.round((sizeWidth+offsetWidth)/(2*10))*10-i*10, Math.round((sizeHeight+offsetHeight)/(2*10))*10));
        }

        newFood();
        buttons.blockButtons();
        isAlive = true;
        isPaused = false;
        timer.start();
    }

    ArrayList<Point> getSnakeLocation() {
        return snakeLocation;
    }

    Point getFoodLocation() {
        return food;
    }
    Boolean getIsAlive() { return isAlive;}

    void setDirection(String dir) {
        snake.direction = dir;
    }
    String getDirection() {
        return snake.direction;
    }

    String getTmpDirection(){
        return snake.tmpDirection;
    }

    void spacePressed(){

        if(!isAlive) {
            snake.startGame();
        }
        else {
            isPaused^=true;
        }
    }

    Boolean getPause(){
        return isPaused;
    }

    private void move() {
        switch (direction) {
            case "RIGHT":
                snakeLocation.add(0, new Point(snakeLocation.get(0).x + 10, snakeLocation.get(0).y + 0));
                break;
            case "LEFT":
                snakeLocation.add(0, new Point(snakeLocation.get(0).x - 10, snakeLocation.get(0).y + 0));
                break;
            case "UP":
                snakeLocation.add(0, new Point(snakeLocation.get(0).x, snakeLocation.get(0).y - 10));
                break;
            case "DOWN":
                snakeLocation.add(0, new Point(snakeLocation.get(0).x, snakeLocation.get(0).y + 10));
                break;
        }
    }


    public void actionPerformed(ActionEvent arg0) {
        if(!isPaused && isAlive) {
            tmpDirection = direction;
            snake.move();
            snake.checkPosition();
            board.repaint();

        }  else if(!isAlive) {
            timer.stop();
            buttons.enableButtons();
        }
    }

    private void newFood() {
        Random random = new Random();
        Point point;
        point = new Point(random.nextInt(sizeWidth / scale) * scale + offsetWidth, random.nextInt(sizeHeight / scale) * scale + offsetHeight);

        while (getSnakeLocation().contains(point)) {
            point = new Point(random.nextInt(sizeWidth / scale) * scale + offsetWidth, random.nextInt(sizeHeight / scale) * scale + offsetHeight);
        }

        food = point;
    }

    private void increaseScore() {
        score=score+speed;
    }
    int getScore(){
        return score;
    }

    void increaseSpeed(){
        if(speed<10) {
            speed += 1;
        }
    }

    void decreaseSpeed(){
        if(speed>1) {
            speed -= 1;
        }
    }

    int getSpeed(){
        return speed;
    }

    void refresh(){
        board.repaint();
    }

    private void checkPosition(){
        for (int j = 1; j < snakeLocation.size()-1; j++) {
            if (snakeLocation.get(0).equals(snakeLocation.get(j))) {
                isAlive = false;
            }
        }

        if (snakeLocation.get(0).x==offsetWidth-scale || snakeLocation.get(0).x==sizeWidth+offsetWidth ||snakeLocation.get(0).y==offsetHeight-scale || snakeLocation.get(0).y==sizeHeight+offsetHeight) {
            isAlive = false;
        }

        if (snakeLocation.get(0).equals(food)) {
            newFood();
            increaseScore();
        }
        else {
            snakeLocation.remove(snakeLocation.size() - 1);
        }

    }
}

