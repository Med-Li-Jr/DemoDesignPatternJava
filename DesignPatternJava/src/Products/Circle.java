package Products;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle implements IForme {
    Color fillColor = new Color(Color.BLACK.getRGB());
    Boolean colored = false;


    Rectangle ConvertToRectangle;
    int CenterValueX, CenterValueY;
    Point TopLeft, TopRight, BottomLeft, BottomRight;
    double Rayon;

    public Circle() {

    }

    public Circle(int X, int Y, double R) {
        CenterValueX = X;
        CenterValueY = Y;
        Rayon = R;

        TopLeft = new Point(X - (int) R, Y - (int) R);
        TopRight = new Point(X + (int) R, Y - (int) R);


        BottomLeft = new Point(X - (int) R, Y + (int) R);
        BottomRight = new Point(X + (int) R, Y + (int) R);

        ConvertToRectangle = new Rectangle(X - (int) R, Y - (int) R, (int) Math.sqrt(3 * R * R), (int) Math.sqrt(3 * R * R));
    }

    public Circle(Rectangle Rect) {
        Rayon = Rect.height / 2;

        CenterValueX = Rect.x + (int) Rayon;
        CenterValueY = Rect.y + (int) Rayon;
        ConvertToRectangle = new Rectangle(Rect.x, Rect.y, Rect.width, Rect.height);

        TopLeft = new Point(Rect.x, Rect.y);
        TopRight = new Point(Rect.x + Rect.width, Rect.y);
        BottomRight = new Point(Rect.x + Rect.width, Rect.y + Rect.height);
        BottomLeft = new Point(Rect.x, Rect.y + Rect.height);

    }

    public double GetRayon() {
        return Rayon;
    }

    public Rectangle GetRectangle() {
        return ConvertToRectangle;
    }

    public Point GetCenter() {
        return new Point(CenterValueX, CenterValueY);
    }

    public Point GetTopLeft() {
        return TopLeft;
    }

    public Point GetBottomRight() {
        return BottomRight;
    }

    public void SetTopLeft(Point P) {
        TopLeft = new Point(P.x, P.y);
    }

    public void SetBottomRight(Point P) {
        BottomRight = new Point(P.x, P.y);
    }

    public Point GetTopRight() {
        return TopRight;
    }

    public Point GetBottomLeft() {
        return BottomLeft;
    }

    public void SetTopRight(Point P) {
        TopRight = new Point(P.x, P.y);
    }

    public void SetBottomLeft(Point P) {
        BottomLeft = new Point(P.x, P.y);
    }


    public Boolean getColored() {
        return colored;
    }

    public void setColored(Boolean color) {
        colored = color;
    }

    public void Deplacer(Graphics2D graphics, String direction) {
        Rectangle currentRect = ConvertToRectangle;
        switch (direction) {
            case "haut":
                ConvertToRectangle = new Rectangle(currentRect.x, currentRect.y - 10, currentRect.width, currentRect.height);
                break;
            case "bas":
                ConvertToRectangle = new Rectangle(currentRect.x, currentRect.y + 10, currentRect.width, currentRect.height);
                break;
            case "gauche":
                ConvertToRectangle = new Rectangle(currentRect.x - 10, currentRect.y, currentRect.width, currentRect.height);
                break;
            case "droite":
                ConvertToRectangle = new Rectangle(currentRect.x + 10, currentRect.y, currentRect.width, currentRect.height);
                break;
            default:
                ConvertToRectangle = new Rectangle(currentRect.x, currentRect.y, currentRect.width, currentRect.height);
                break;
        }
        this.ParameterCircle(ConvertToRectangle);

        this.Dessiner(graphics);
    }

    public void Colorer(Graphics2D graphics) {
        graphics.setColor(fillColor);
        Ellipse2D.Double e1 = new Ellipse2D.Double(ConvertToRectangle.x, ConvertToRectangle.y, ConvertToRectangle.width, ConvertToRectangle.height);
        graphics.fill(e1);
        colored = true;
    }

    public void Dessiner(Graphics2D graphics) {
        graphics.setColor(fillColor);
        Ellipse2D.Double e1 = new Ellipse2D.Double(ConvertToRectangle.x, ConvertToRectangle.y, ConvertToRectangle.width, ConvertToRectangle.height);
        graphics.draw(e1);
        if (colored) {
            Colorer(graphics);
        }
    }

    public float Surface() {
        return  0;
    }

    public String toString() {
        return "X : " + CenterValueX + " Y : " + CenterValueY + " ray " + Rayon;
    }

    public void ParameterCircle(Rectangle Rect) {
        Rayon = Rect.height / 2;

        CenterValueX = Rect.x + (int) Rayon;
        CenterValueY = Rect.y + (int) Rayon;
        ConvertToRectangle = new Rectangle(Rect.x, Rect.y, Rect.width, Rect.height);

        TopLeft = new Point(Rect.x, Rect.y);
        TopRight = new Point(Rect.x + Rect.width, Rect.y);
        BottomRight = new Point(Rect.x + Rect.width, Rect.y + Rect.height);
        BottomLeft = new Point(Rect.x, Rect.y + Rect.height);
    }
}
