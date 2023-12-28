package Products;
import java.awt.*;

public interface IForme
{
    void Dessiner(Graphics2D graphics);

    void Colorer(Graphics2D graphics);

    void Deplacer(Graphics2D graphics, String direction);

    void setColored(Boolean colored);

    Boolean getColored();

    String toString();
}