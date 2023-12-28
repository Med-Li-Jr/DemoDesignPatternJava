import Products.IForme;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompositeForme implements IForme
{
    List<IForme> AllForme = new ArrayList<>();

    public void Colorer(Graphics2D graphics)
    {
        IForme lastForm = getLastForm();

        if (lastForm != null)
            lastForm.Colorer(graphics);
    }

    public void Deplacer(Graphics2D graphics, String direction)
    {
        for (IForme row : AllForme)
        {
            if (row != null)
            {
                row.Deplacer(graphics, direction);
            }
        }
        InterfaceUI.CurrentForm = getLastForm();

    }

    public void Dessiner(Graphics2D graphics)
    {
        for (IForme row : AllForme)
        {
            if(row != null)
                row.Dessiner(graphics);
        }

    }

    public Boolean getColored()
    {
        IForme lastForm = getLastForm();

        if (lastForm != null)
            return lastForm.getColored();
        return false;
    }

    public void setColored(Boolean colored)
    {
        IForme lastForm = getLastForm();

        if (lastForm != null)
            lastForm.setColored(colored);
    }

    public void addForm(IForme newForme)
    {
        AllForme.add(newForme);
    }

    public IForme getLastForm()
    {
        return AllForme.getLast();
    }
}