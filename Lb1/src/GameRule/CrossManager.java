package GameRule;
import Figure.Rectangles;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.List;

public class CrossManager implements Rule{

    List<Rectangles> RectangleList;
    int figureCount;
    int[] Cords;
    int maxFigureCount;


    public CrossManager(){
        RectangleList = new ArrayList<Rectangles>();
        figureCount = 0;
        maxFigureCount = 42;
        Cords = new int[4];
    }

    public void rGeneratePosotion(List<List<Integer>> Cords, int n){
        if (n >= maxFigureCount){
            System.out.println("Too big value!");
            System.exit(-1);
        }
        int frame = 640000;
        int min = 1;
        int max = 799;
        int Position = rndForPosition(min, max);
        maxFigureCount -= 3600;
    }
    private static int rndForPosition(int min, int max){
        max -= min;
        return (int) (Math.random() * ++ max) + min;
    }

    public void CreateFigure(int count) {
        figureCount = count;

        if (count >= maxFigureCount){
            System.out.println("Too many figures!\nPrint only 42 max.");
            count = maxFigureCount;
            figureCount = count;
        }
        int x = 10, y = 5;
        int ColorN = 1;

        Point cords = new Point(x, y);
        Dimension size = new Dimension(50,50);

        for (int i = 0; i < count; i++){

            if ((int) cords.getX() < 0 || (int) cords.getX() > 740) {
                y += 120;
                x = 10;
                cords = new Point(x, y);
            }

            RectangleList.add(new Rectangles(cords, size, ColorN));

            x += 120;
            cords = new Point(x,y);
            ColorN ++;
            if (ColorN >= 8){ ColorN = 1; }
        }
    }

    public int[] getCords(int i){
        Cords[0] = (int) RectangleList.get(i).getPoint().getX();
        Cords[1] = (int) RectangleList.get(i).getPoint().getY();
        Cords[2] = (int) RectangleList.get(i).getDimension().getWidth();
        Cords[3] = (int) RectangleList.get(i).getDimension().getHeight();
        return Cords;
    }

    public int getFigureCount(){return figureCount;}

    public List<Rectangles> getRectList(){return RectangleList;}

    public boolean FigureCross(Rectangle r1, Rectangle r2) {

        return r1.intersects(r2);
    }

    public boolean TimerStart(){
        try {
            for (Rectangles rec : RectangleList){
                rec.TimerStart();
            }
            return true;
        } catch (Exception e) {
            e.getCause();
            return false;
        }
    }

    public boolean TimerStop(){
        for (Rectangles rec : RectangleList){
            rec.TimerStop();
        }
        return false;
    }


    public boolean GameFieldCross(Rectangle r1) {
        Rectangle border = new Rectangle(0,0,800,800);
        return border.intersects(r1);
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public int SizeChange(int s) {

        final int a = 1;
        final int b = 2;
        final int randomizer = rnd(a, b);
        if (randomizer == 1){ return s;}
        else {return s/2;}
    }
}
