package Products;


import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MyRectangle implements IForme {
    Color fillColor = new Color(Color.RED.getRGB());
    Boolean colored = false;

    Rectangle rectangle;
    public int x;
    public int y;
    public int width;
    public int height;


    public MyRectangle() {
        rectangle = new Rectangle();
    }

    public MyRectangle(int x, int y, int width, int height) {
        rectangle = new Rectangle(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public MyRectangle(MyRectangle Rect) {
        rectangle = new Rectangle(Rect.x, Rect.y, Rect.width, Rect.height);
        this.x = Rect.x;
        this.y = Rect.y;
        this.width = Rect.width;
        this.height = Rect.height;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Boolean getColored() {
        return colored;
    }

    public void setColored(Boolean color) {
        colored = color;
    }

    public void Colorer(Graphics2D graphics) {
        graphics.setColor(fillColor);
        Rectangle2D.Double e1 = new Rectangle2D.Double(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.fill(e1);
        colored = true;
    }

    public void Dessiner(Graphics2D graphics) {
        graphics.setColor(fillColor);
        Rectangle2D.Double e1 = new Rectangle2D.Double(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.draw(e1);
        System.out.println("5 The message Create Form! " + colored +  " ^^");
        if (colored) {
            Colorer(graphics);
        }
    }

    public void Deplacer(Graphics2D graphics, String direction) {
        Rectangle currentRect = rectangle;
        switch (direction) {
            case "haut":
                currentRect = new Rectangle(currentRect.x, currentRect.y - 10, currentRect.width, currentRect.height);
                break;

            case "bas":
                currentRect = new Rectangle(currentRect.x, currentRect.y + 10, currentRect.width, currentRect.height);
                break;

            case "gauche":
                currentRect = new Rectangle(currentRect.x - 10, currentRect.y, currentRect.width, currentRect.height);
                break;

            case "droite":
                currentRect = new Rectangle(currentRect.x + 10, currentRect.y, currentRect.width, currentRect.height);
                break;

            default:
                currentRect = new Rectangle(currentRect.x, currentRect.y, currentRect.width, currentRect.height);
                break;
        }
        rectangle = currentRect;
        x = rectangle.x;
        y = rectangle.y;
        width = rectangle.width;
        height = rectangle.height;
        Dessiner(graphics);
    }


    public String toString() {
        return " rectangle " + rectangle.x + "/" + rectangle.y + " w: " + rectangle.width + " h: " + rectangle.height;
    }

}