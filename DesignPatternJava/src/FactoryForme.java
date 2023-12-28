import Products.Circle;
import Products.IForme;
import Products.MyRectangle;
import Products.Triangle;

import java.awt.*;

public class FactoryForme {

    int[] CadreX = new int[]{10, 500}; // Le cadre pour dessiner les graphes
    int[] CadreY = new int[]{100, 350};


    public IForme createForme(String choice)
    {
        switch (choice)
        {
            case "rectangle":
                //on teste pour savoir si un objet a ete deja cree d'abord
                //si oui on cree une forme à l'interieur de cette forme
                if (InterfaceUI.CurrentForm != null)
                    InterfaceUI.CurrentForm = createNewForm("rectangle");
                else
                {
                    //sinon on cree une nouvelle forme qui sera la premiere forme

                    InterfaceUI.CurrentForm = new MyRectangle(
                            CadreX[0] + 5, CadreY[0] + 5,
                            CadreX[1] - 5,
                            CadreY[1] - 5);
                }
                break;

            case "cercle":
                //on teste pour savoir si un objet a ete deja cree d'abord
                //si oui on cree une forme à l'interieur de cette forme
                if (InterfaceUI.CurrentForm != null)
                    InterfaceUI.CurrentForm = createNewForm("cercle");
                else
                {
                    //sinon on cree une nouvelle forme qui sera la premiere forme
                    int Heigh = CadreY[1] - 5;
                    int widtLeft = CadreX[1] - Heigh - CadreX[0];

                    InterfaceUI.CurrentForm = new Circle(new Rectangle(CadreX[0] + 5 + (widtLeft / 2), CadreY[0] + 5, Heigh, Heigh));

                }
                break;

            case "triangle":
                //on teste pour savoir si un objet a ete deja cree d'abord
                //si oui on cree une forme à l'interieur de cette forme
                if (InterfaceUI.CurrentForm != null)
                    InterfaceUI.CurrentForm = createNewForm("triangle");
                else
                {
                    //sinon on cree une nouvelle forme qui sera la premiere forme
                    InterfaceUI.CurrentForm = ObtainTrangleEquilateral(
                            new Point(CadreX[0] + 10, CadreY[1] - 5),
                            new Point(CadreX[1] - 10, CadreY[1] - 5),
                            new Point((CadreX[1] - CadreX[0]) / 2, CadreY[0] + 5));
                }
                break;
        }

        return (IForme)InterfaceUI.CurrentForm;
    }

    public IForme createNewForm(String formeTxt)
    {
        IForme newForm = null;

        //verifier le type de la forme existante qui va recevoir la nouvelle forme

        switch (formeTxt)
        {
            case "rectangle":
                if (InterfaceUI.CurrentForm.getClass() == Triangle.class)
                    newForm = ObtainRectangleInCurrentTriangle();
                else if (InterfaceUI.CurrentForm.getClass() == MyRectangle.class)
                    newForm = ObtainRectangleInCurrentRectangle();
                else if (InterfaceUI.CurrentForm.getClass() == Circle.class)
                    newForm = ObtainRectangleInCurrentCercle();
                break;

            case "cercle":
                if (InterfaceUI.CurrentForm.getClass() == Triangle.class)
                    newForm = ObtainCircleInCurrentTriangle();
                else if (InterfaceUI.CurrentForm.getClass() == MyRectangle.class)
                    newForm = ObtainCircleInCurrentRectangle();
                else if (InterfaceUI.CurrentForm.getClass() == Circle.class)
                    newForm = ObtainCircleInCurrentCercle();
                break;

            case "triangle":
                if (InterfaceUI.CurrentForm.getClass() == Triangle.class)
                    newForm = ObtainTriangleInCurrentTriangle();
                else if (InterfaceUI.CurrentForm.getClass() == MyRectangle.class)
                    newForm = ObtainTriangleInCurrentRectangle();
                else if (InterfaceUI.CurrentForm.getClass() == Circle.class)
                    newForm = ObtainTriangleInCurrentCercle();
                break;
        }

        return newForm;
    }







    //Ces fonctions suivantes servent uniquement pour creer des formes specifique comme un triangle equilaterial
    //ou encore un cercle inscrit dans un triangle

    public Triangle ObtainTriangleInCurrentCercle()
    {

        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == Circle.class)
        {
            Circle CurrentCircle = (Circle)InterfaceUI.CurrentForm;
            boolean senschanged = false;
            Point BtLeft = new Point();
            Point BtRight = new Point();

            Point BottomLeft = new Point(CurrentCircle.GetBottomLeft().x, CurrentCircle.GetBottomLeft().y);
            Point BottomRight = new Point(CurrentCircle.GetBottomRight().x, CurrentCircle.GetBottomRight().y);
            Point Sommet = new Point(CurrentCircle.GetCenter().x, CurrentCircle.GetTopLeft().y);

            for (int y = BottomRight.y; y > CurrentCircle.GetCenter().y; y--)
            {
                if (calculdistance(BottomRight, CurrentCircle.GetCenter()) < CurrentCircle.GetRayon())
                {
                    senschanged = true;
                    if (BtLeft.x == 0 && BtLeft.y == 0)
                    {
                        BtLeft = new Point(BottomLeft.x, BottomLeft.y);
                        BtRight = new Point(BottomRight.x, BottomRight.y);
                    }
                }
                else
                {
                    senschanged = false;
                }
                if (senschanged)
                {
                    BottomLeft.x -= 1;
                    BottomLeft.y -= 1;
                    BottomRight.x += 1;
                    BottomRight.y -= 1;
                }
                else
                {
                    BottomLeft.x += 1;
                    BottomLeft.y -= 1;
                    BottomRight.x -= 1;
                    BottomRight.y -= 1;
                }

                if (calculdistance(BottomLeft, BottomRight) == calculdistance(Sommet, BottomRight))
                {
                    return new Triangle(new Point[] { BottomLeft, BottomRight, Sommet });
                }
            }
            return new Triangle(new Point[] { BtLeft, BtRight, Sommet });
        }
        return null;
    }

    public MyRectangle ObtainRectangleInCurrentCercle()
    {

        MyRectangle Curr = new MyRectangle(0, 0, 0, 0);

        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == Circle.class)
        {
            Circle CurrentCircle = (Circle)InterfaceUI.CurrentForm;

            boolean senschanged = false;

            Point TopLeft = new Point(CurrentCircle.GetTopLeft().x, CurrentCircle.GetTopLeft().y);
            Point TopRight = new Point(CurrentCircle.GetTopRight().x, CurrentCircle.GetTopRight().y);
            Point Center = new Point(CurrentCircle.GetCenter().x, CurrentCircle.GetCenter().y);
            Point Sommet = new Point(CurrentCircle.GetCenter().x, CurrentCircle.GetBottomRight().y);

            for (int y = TopLeft.y; y < Center.y; y++)
            {
                if (calculdistance(TopLeft, Center) < CurrentCircle.GetRayon())
                {
                    senschanged = true;
                }
                else
                {
                    senschanged = false;
                }
                if (!senschanged)
                {
                    TopLeft.x += 1;
                    TopLeft.y += 1;
                    TopRight.x -= 1;
                    TopRight.y += 1;
                }
                else
                {
                    TopLeft.x -= 1;
                    TopLeft.y += 1;
                    TopRight.x += 1;
                    TopRight.y += 1;
                }

                if (calculdistance(TopLeft, TopRight) == (calculdistance(Sommet, TopRight) + 1)
                        || calculdistance(TopLeft, TopRight) == (calculdistance(Sommet, TopRight) - 1)
                        || calculdistance(TopLeft, TopRight) == calculdistance(Sommet, TopRight))
                {
                    Point P3 = new Point(TopLeft.x, 2 * (Center.y - TopLeft.y) + TopLeft.y);
                    Point P4 = new Point(TopRight.x, 2 * (Center.y - TopLeft.y) + TopLeft.y);
                    return new MyRectangle(TopLeft.x, TopLeft.y, P4.x - TopLeft.x, P3.y - TopRight.y);
                }

            }
        }

        return Curr;
    }

    public Circle ObtainCircleInCurrentCercle()
    {
        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == Circle.class)
        {
            Circle CurrentCircle = (Circle)InterfaceUI.CurrentForm;

            Rectangle Rect = CurrentCircle.GetRectangle();

            Rect.width = Rect.width - 10;
            Rect.height = Rect.height - 10;
            Rect.x = Rect.x + 5;
            Rect.y = Rect.y + 5;

            return new Circle(Rect);
        }
        return null;
    }


    public Triangle ObtainTriangleInCurrentTriangle()
    {

        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == Triangle.class)
        {
            Triangle CurrentTriangle = (Triangle)InterfaceUI.CurrentForm;

            Point PSommet = new Point(CurrentTriangle.getP3().x, CurrentTriangle.getP3().y + 5);
            Point SommetDefault = new Point();
            SommetDefault.y = PSommet.y;
            SommetDefault.x = PSommet.x;
            Point FirstPoint = new Point(CurrentTriangle.getP1().x + 10, CurrentTriangle.getP1().y - 5);
            Point SecondPoint = new Point(CurrentTriangle.getP2().x - 10, CurrentTriangle.getP2().y - 5);
            for (int i = FirstPoint.x; i < SecondPoint.x; i++)
            {
                SommetDefault.x = i;
                if (calculdistance(FirstPoint, SommetDefault) == calculdistance(SommetDefault, SecondPoint)
                        && calculdistance(SommetDefault, SecondPoint) == calculdistance(SecondPoint, FirstPoint))
                {
                    return new Triangle(new Point[] { FirstPoint, SecondPoint, SommetDefault });
                }
            }

            SommetDefault.x = FirstPoint.x + (calculdistance(FirstPoint, SecondPoint) / 2);
            return new Triangle(new Point[] { FirstPoint, SecondPoint, SommetDefault });
        }
        return null;
    }

    public MyRectangle ObtainRectangleInCurrentTriangle()
    {

        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == Triangle.class)
        {
            Triangle CurrentTriangle = (Triangle)InterfaceUI.CurrentForm;

            int h = CurrentTriangle.getP1().y - CurrentTriangle.getP3().y;
            int a = CurrentTriangle.getP3().x - CurrentTriangle.getP1().x;
            int x = a / 2;

            int y = h / 2;

            int b = calculdistance(CurrentTriangle.getP1(), CurrentTriangle.getP2());

            int xPrim = (a + b) / 2;

            return new MyRectangle(CurrentTriangle.getP1().x + x, CurrentTriangle.getP1().y - y, xPrim - x, y);
        }
        return new MyRectangle(0, 0, 0, 0);
    }

    public Circle ObtainCircleInCurrentTriangle()
    {

        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == Triangle.class)
        {
            Triangle CurrentTriangle = (Triangle)InterfaceUI.CurrentForm;

            Point PointA = new Point(CurrentTriangle.getP1().x, CurrentTriangle.getP1().y);
            Point PointB = new Point(CurrentTriangle.getP2().x, CurrentTriangle.getP2().y);
            Point PointC = new Point(CurrentTriangle.getP3().x, CurrentTriangle.getP3().y);

            double aEqualsDtP2P3 = calculdistance(PointB, PointC);
            double bEqualsDtP3P1 = calculdistance(PointC, PointA);
            double cEqualsDtP1P2 = calculdistance(PointA, PointB);

            int SommeTotalDistance = (int)(aEqualsDtP2P3 + bEqualsDtP3P1 + cEqualsDtP1P2);

            int numerateurX = (int)((aEqualsDtP2P3 * PointA.x) + (bEqualsDtP3P1 * PointB.x) + (cEqualsDtP1P2 * PointC.x));
            int ValueX = (numerateurX / SommeTotalDistance);

            int numerateurY = (int)((aEqualsDtP2P3 * PointA.y) + (bEqualsDtP3P1 * PointB.y) + (cEqualsDtP1P2 * PointC.y));
            int ValueY = (numerateurY / SommeTotalDistance);

            Point PointCenter = new Point(ValueX, ValueY);

            double average = SommeTotalDistance / 2;

            double Rayon = Math.sqrt((((average - aEqualsDtP2P3) * (average - bEqualsDtP3P1) * (average - cEqualsDtP1P2)) / average));

            return new Circle(new Rectangle(ValueX - (int)Rayon, ValueY - (int)Rayon, (int)Rayon * 2, (int)Rayon * 2));

        }
        return null;
    }



    public Triangle ObtainTriangleInCurrentRectangle()
    {

        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == MyRectangle.class)
        {
            MyRectangle CurrentRectangle = (MyRectangle)InterfaceUI.CurrentForm;
            Point PSommet = new Point(CurrentRectangle.x, CurrentRectangle.y + 5);
            Point SommetDefault = new Point();
            SommetDefault.y = PSommet.y;
            SommetDefault.x = PSommet.x;
            Point FirstPoint = new Point(CurrentRectangle.x + 10, CurrentRectangle.y + CurrentRectangle.height - 5);
            Point SecondPoint = new Point(CurrentRectangle.x + CurrentRectangle.width - 10, CurrentRectangle.y + CurrentRectangle.height - 5);
            for (int i = FirstPoint.x; i < SecondPoint.x; i++)
            {
                SommetDefault.x = i;
                if (calculdistance(FirstPoint, SommetDefault) == calculdistance(SommetDefault, SecondPoint)
                        && calculdistance(SommetDefault, SecondPoint) == calculdistance(SecondPoint, FirstPoint))
                {
                    return new Triangle(new Point[] { FirstPoint, SecondPoint, SommetDefault });
                }
            }

            SommetDefault.x = FirstPoint.x + (calculdistance(FirstPoint, SecondPoint) / 2);
            return new Triangle(new Point[] { FirstPoint, SecondPoint, SommetDefault });
        }
        return null;
    }

    public MyRectangle ObtainRectangleInCurrentRectangle()
    {
        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == MyRectangle.class)
        {
            MyRectangle CurrentRectangle = (MyRectangle)InterfaceUI.CurrentForm;

            return new MyRectangle(CurrentRectangle.x + 5, CurrentRectangle.y + 5, CurrentRectangle.width - 10, CurrentRectangle.height - 10);
        }
        return new MyRectangle(0, 0, 0, 0);
    }

    public Circle ObtainCircleInCurrentRectangle()
    {
        if (InterfaceUI.CurrentForm != null && InterfaceUI.CurrentForm.getClass() == MyRectangle.class)
        {
            MyRectangle Rect = new MyRectangle(((MyRectangle)InterfaceUI.CurrentForm));

            int cote = Rect.height - 10;
            int left = Rect.width - cote;
            Rect.width = cote;
            Rect.height = cote;
            Rect.x = (left / 2) + Rect.x;
            Rect.y = Rect.y + 5;

            return new Circle(Rect.getRectangle());

        }
        return null;
    }


    public Triangle ObtainTrangleEquilateral(Point P1, Point P2, Point PSommet)
    {
        Point P3 = new Point();
        P3.y = PSommet.y;
        P3.x = PSommet.x;
        for (int i = P1.x; i < P2.x; i++)
        {
            P3.x = i;
            if (calculdistance(P1, P3) == calculdistance(P3, P2) && calculdistance(P3, P2) == calculdistance(P2, P1))
            {
                return new Triangle(new Point[] { P1, P2, P3 });
            }
        }

        P3.x = P1.x + (calculdistance(P1, P2) / 2);
        return new Triangle(new Point[] { P1, P2, P3 });
    }

    public int calculdistance(Point P1, Point P2)
    {
        return (int)Math.sqrt(((P2.x - P1.x) * (P2.x - P1.x)) + ((P2.y - P1.y) * (P2.y - P1.y)));
    }
}
