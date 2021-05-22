package Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rectangles implements Figure {

    Rectangle rec;
    Point point;
    Dimension dimension;
    Color col;
    int ColorNumber;

    int MiddleX;
    int MiddleY;
    int speedX;
    int speedY;

    int Move;

    Timer timer;

    public void setCords(int x, int y){
        this.point.setLocation(x, y);
    }

    public int getX(){
        return (int) point.getX();
    }
    public int getY(){
        return (int) point.getY();
    }

    public void setColor(Color c){col = c;}

    public Color getColor() {return col;}

    public Rectangles(){}

    public Rectangles(Point newPoint, Dimension newDimension, int ColorNumber){
        this.point = newPoint;
        this.dimension = newDimension;
        this.col = nextColor(ColorNumber);
        rec = new Rectangle(point, dimension);

        Move = (int) (Math.random() * 4);

        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    if ( (int) point.getX() < 0 || (int) point.getX() > 750) {
                        setSpeedX(-getSpeedX());
                    }
                    if ( (int) point.getY() < 0 || (int) point.getY() > 750) {
                        setSpeedY(-getSpeedY());
                    }
                    setCords((int) point.getX() + (int) getSpeedX(), (int) point.getY() + (int) getSpeedY());
                }
            }
        });
    }

    public void TimerStart(){
        timer.start();
    }

    public void TimerStop(){
        timer.stop();
    }

    public Point getPoint(){return point;}

    public Dimension getDimension(){return dimension;}

    public void setSpeedX(int s){
        speedX = s;
    }
    public int getSpeedX(){return speedX;}

    public void setSpeedY(int s){
        speedY = s;
    }
    public int getSpeedY(){return speedY;}



    public void StarMoving(int[] speed){
        switch (Move){
            case 0: point.x += speed[0];
            case 1: point.x -= speed[0];
            case 2: point.y += speed[1];
            case 3: point.y -= speed[1];
        }
    }

    public void ChangeMove(){
        switch (Move){
            case 0: Move = 1;
            case 1: Move = 0;
            case 2: Move = 3;
            case 3: Move = 2;
        }
    }

    public Rectangle getRec(){
        return rec;
    }

    private Color nextColor(int ColorNumber){
        switch (ColorNumber){
            case 1:
                return Color.RED;
            case 2:
                return Color.ORANGE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.BLUE.brighter();
            case 6:
                return Color.BLUE.darker();
            case 7:
                return Color.PINK.darker();

            default: ColorNumber = 1;
        }
        return null;
    }
}
