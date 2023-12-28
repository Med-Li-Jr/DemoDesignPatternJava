package Products;

import java.awt.*;

public class Triangle implements IForme {

    Point[] CurrentTriangle;
    int[] AllCurrentX;
    int[] AllCurrentY;
    Point P1, P2, P3;

    Color fillColor = new Color(Color.BLUE.getRGB());
    Boolean colored = false;


    public Triangle() {

    }

    public Triangle(int Firstx, int Firsty, int Secondx, int Secondy, int Thirdx, int Thirdy) {
        P1 = new Point(Firstx, Firsty);
        P2 = new Point(Secondx, Secondy);
        P3 = new Point(Thirdx, Thirdy);
        CurrentTriangle = new Point[]{P1, P2, P3};
        AllCurrentX = new int[]{P1.x, P2.x, P3.x};
        AllCurrentY = new int[]{P1.y, P2.y, P3.y};
    }

    public Triangle(Point[] AllPoints) {
        if (AllPoints.length >= 3) {
            P1 = new Point(AllPoints[0].x, AllPoints[0].y);
            P2 = new Point(AllPoints[1].x, AllPoints[1].y);
            P3 = new Point(AllPoints[2].x, AllPoints[2].y);
            CurrentTriangle = new Point[]{P1, P2, P3};
            AllCurrentX = new int[]{P1.x, P2.x, P3.x};
            AllCurrentY = new int[]{P1.y, P2.y, P3.y};
        }
    }

    public Point getP1() {
        return P1;
    }

    public Point getP2() {
        return P2;
    }

    public Point getP3() {
        return P3;
    }

    public Point[] GetTriangle() {
        return CurrentTriangle;
    }


    public Boolean getColored() {
        return colored;
    }

    public void setColored(Boolean color) {
        colored = color;
    }

    public void Colorer(Graphics2D graphics) {
        graphics.setColor(fillColor);
        graphics.fillPolygon(AllCurrentX, AllCurrentY, 3);
        colored = true;
    }


    public void Dessiner(Graphics2D graphics) {
        graphics.setColor(fillColor);
        graphics.drawPolygon(AllCurrentX, AllCurrentY, 3);

        if (colored) {
            Colorer(graphics);
        }
    }

    public void Deplacer(Graphics2D graphics, String direction) {

        Triangle currentTriangle = new Triangle(CurrentTriangle);
        switch (direction) {
            case "haut":
                CurrentTriangle = new Point[]{
                        new Point(currentTriangle.getP1().x, currentTriangle.getP1().y - 10),
                        new Point(currentTriangle.getP2().x, currentTriangle.getP2().y - 10),
                        new Point(currentTriangle.getP3().x, currentTriangle.getP3().y - 10)};
                break;

            case "bas":
                CurrentTriangle = new Point[]{
                        new Point(currentTriangle.getP1().x, currentTriangle.getP1().y + 10),
                        new Point(currentTriangle.getP2().x, currentTriangle.getP2().y + 10),
                        new Point(currentTriangle.getP3().x, currentTriangle.getP3().y + 10)};
                break;

            case "gauche":
                CurrentTriangle = new Point[]{
                        new Point(currentTriangle.getP1().x - 10, currentTriangle.getP1().y),
                        new Point(currentTriangle.getP2().x - 10, currentTriangle.getP2().y),
                        new Point(currentTriangle.getP3().x - 10, currentTriangle.getP3().y)};
                break;

            case "droite":
                CurrentTriangle = new Point[]{
                        new Point(currentTriangle.getP1().x + 10, currentTriangle.getP1().y),
                        new Point(currentTriangle.getP2().x + 10, currentTriangle.getP2().y),
                        new Point(currentTriangle.getP3().x + 10, currentTriangle.getP3().y)};
                break;

            default:
                CurrentTriangle = new Point[]{
                        new Point(currentTriangle.getP1().x, currentTriangle.getP1().y),
                        new Point(currentTriangle.getP2().x, currentTriangle.getP2().y),
                        new Point(currentTriangle.getP3().x, currentTriangle.getP3().y)};
                break;
        }
        this.parameterTriangle(CurrentTriangle);
        Dessiner(graphics);
    }


    private void parameterTriangle(Point[] triangle) {

        P1 = new Point(triangle[0].x, triangle[0].y);
        P2 = new Point(triangle[1].x, triangle[1].y);
        P3 = new Point(triangle[2].x, triangle[2].y);
        CurrentTriangle = new Point[]{P1, P2, P3};
        AllCurrentX = new int[]{P1.x, P2.x, P3.x};
        AllCurrentY = new int[]{P1.y, P2.y, P3.y};
    }

    public String toString() {
        return "triangle " + P1.x + "/" + P1.y + " :: " + P2.x + "/" + P2.y;
    }

}
