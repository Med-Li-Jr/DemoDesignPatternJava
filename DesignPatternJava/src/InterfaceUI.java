import Products.ActionModel;
import Products.IForme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class InterfaceUI extends JComponent implements ActionListener {

    private final int width;
    private final int height;
    Graphics2D graphics;
    JFrame frame;
    public static IForme CurrentForm = null;

    // Represente la liste des différentes actions ( Creation, Colorer, Deplacement )
    ArrayList<ActionModel> AllActions = new ArrayList<>();

    // Pour le Factory Pattern
    FactoryForme factoryForme = new FactoryForme();

    // Pour le Composite Pattern
    CompositeForme compositeForme = new CompositeForme();
    JButton creerButton, colorerButton, saveButton;
    JButton hautButton, basButton, droiteButton, gaucheButton;
    JRadioButton rbRectangle, rbTriangle, rbCircle;
    String currentCheckBox = "";

    public InterfaceUI(JFrame frame, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.frame = frame;

        creerButton = new JButton("Creer");
        colorerButton = new JButton("Colorer");
        saveButton = new JButton("Sauvegarde");
        hautButton = new JButton("Haut");
        basButton = new JButton("Bas");
        droiteButton = new JButton("Droite");
        gaucheButton = new JButton("Gauche");


        currentCheckBox = "rectangle";
        rbRectangle = new JRadioButton("Rectangle", true);
        rbRectangle.setBounds(50, 25, 100, 15);
        rbTriangle = new JRadioButton("Triangle");
        rbTriangle.setBounds(160, 25, 100, 15);
        rbCircle = new JRadioButton("Circle");
        rbCircle.setBounds(270, 25, 100, 15);

        ButtonGroup groupButton = new ButtonGroup();
        groupButton.add(rbRectangle);
        groupButton.add(rbTriangle);
        groupButton.add(rbCircle);

        rbCircle.addActionListener(this);
        rbTriangle.addActionListener(this);
        rbRectangle.addActionListener(this);


        creerButton.setBounds(400, 25, 75, 30);
        colorerButton.setBounds(500, 25, 75, 30);
        saveButton.setBounds(600, 25, 150, 30);
        creerButton.addActionListener(this);
        colorerButton.addActionListener(this);
        saveButton.addActionListener(this);
        hautButton.addActionListener(this);
        basButton.addActionListener(this);
        droiteButton.addActionListener(this);
        gaucheButton.addActionListener(this);
        hautButton.setBounds(620, 200, 90, 35);
        gaucheButton.setBounds(550, 250, 90, 35);
        basButton.setBounds(620, 300, 90, 35);
        droiteButton.setBounds(670, 250, 90, 35);


        frame.add(rbCircle);
        frame.add(rbRectangle);
        frame.add(rbTriangle);
        frame.add(creerButton);
        frame.add(colorerButton);
        frame.add(saveButton);
        frame.add(hautButton);
        frame.add(basButton);
        frame.add(droiteButton);
        frame.add(gaucheButton);

    }

    @Override // evenement lorsqu'on click sur un boutton
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == rbCircle){
            currentCheckBox = "cercle";
        }
        else if(e.getSource() == rbTriangle){
            currentCheckBox = "triangle";
        }
        else if(e.getSource() == rbRectangle){
            currentCheckBox = "rectangle";
        }


        if(e.getSource() == creerButton){

            // creation d'une nouvelle Forme avec le Factory Pattern
            IForme newForme = factoryForme.createForme(currentCheckBox);

            // Ajoute de la nouvelle forme dans le Composite
            compositeForme.addForm(newForme);

            if(!compositeForme.AllForme.isEmpty())
                AllActions.add(new ActionModel("Creer", currentCheckBox, LocalDate.now()));
        }
        else if (e.getSource() == colorerButton){

            // Colorier la dernière forme grace au Composite Pattern
            compositeForme.Colorer(graphics);
            if(!compositeForme.AllForme.isEmpty())
                AllActions.add(new ActionModel("Colorer", currentCheckBox, LocalDate.now()));
        }
        else if (e.getSource() == saveButton){
            SaveActions();
        }
        else if (e.getSource() == hautButton){

            // Faire le deplacement de toutes les formes grace au Composite Pattern
            compositeForme.Deplacer(graphics, "haut");
            if(!compositeForme.AllForme.isEmpty())
                AllActions.add(new ActionModel("Deplacer Haut", currentCheckBox, LocalDate.now()));
        }
        else if (e.getSource() == basButton){

            // Faire le deplacement de toutes les formes grace au Composite Pattern
            compositeForme.Deplacer(graphics, "bas");
            if(!compositeForme.AllForme.isEmpty())
                AllActions.add(new ActionModel("Deplacer Bas", currentCheckBox, LocalDate.now()));
        }
        else if (e.getSource() == droiteButton){

            // Faire le deplacement de toutes les formes grace au Composite Pattern
            compositeForme.Deplacer(graphics, "droite");
            if(!compositeForme.AllForme.isEmpty())
                AllActions.add(new ActionModel("Deplacer Droite", currentCheckBox, LocalDate.now()));
        }
        else if (e.getSource() == gaucheButton){

            // Faire le deplacement de toutes les formes grace au Composite Pattern
            compositeForme.Deplacer(graphics, "gauche");
            if(!compositeForme.AllForme.isEmpty())
                AllActions.add(new ActionModel("Deplacer Gauche", currentCheckBox, LocalDate.now()));
        }

        frame.repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        graphics = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHints(rh);

        compositeForme.Dessiner(graphics);
    }

    private void SaveActions()
    {

        if (!AllActions.isEmpty())
        {
            SingletonBD singletonBD = SingletonBD.getInstance();
            String text = "";
            for (ActionModel row : AllActions)
            {
                text += row.ToString() + " // " + row.date + "\n";

                singletonBD.InsertionTable("insert into ActionTB (nameAction, forme, date)" +
                        "values ('" + row.nameAction + "', '" + row.forme + "', '" + row.date + "')");
            }
            AllActions.clear();
            JOptionPane.showMessageDialog(null, text, "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Creer une nouvelle forme", "Information", JOptionPane.ERROR_MESSAGE);
        }

        frame.repaint();
    }
}
