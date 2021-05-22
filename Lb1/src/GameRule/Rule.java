package GameRule;

import Figure.Rectangles;

import java.awt.*;

public interface Rule {

    boolean FigureCross(Rectangle r1, Rectangle r2);

    //void ColorChange(Rectangles r);

    int SizeChange(int Size);
}
