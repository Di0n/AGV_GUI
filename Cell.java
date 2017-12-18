import javax.swing.*;
import java.awt.*;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Cell extends JButton
{
    private JButton button;
    public Cell(JButton button)
    {
        this.button = button;
        button.setIcon(new ImageIcon("grid1.png"));
        button.setMargin(new Insets(0,0,0,0));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        //button.setBorder(new EmptyBorder(0,0,0,0));
    }
    
    public JButton getButton()
    {
        return button;
    }

}
