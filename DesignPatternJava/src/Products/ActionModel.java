package Products;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.Date;

public class ActionModel
{
    public String nameAction;
    public String forme;
    public String date;

    public ActionModel()
    {

    }

    public ActionModel(String nomAction, String nomForme, LocalDate dateAction)
    {
        nameAction = nomAction;
        forme = nomForme;

        date = dateAction.toString();
    }

    public ActionModel(ActionModel NewAction)
    {
        nameAction = NewAction.nameAction;
        forme = NewAction.forme;
        date = NewAction.date;

    }

    public String ToString()
    {
        return " Action : " + nameAction + " Forme : " + forme + " date : " + date;
    }


}