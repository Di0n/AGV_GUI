import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GUI extends JFrame
{
    private int width = 5;
    private int height = 5;
    
    Cell field[][] = new Cell[5][5]; 
    ArrayList<ImageIcon> gridPhotos;
    JPanel gamePanel;
    
    public GUI()
    {
        super("GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel contentPane = new JPanel(new GridLayout(0,2));
        JPanel grid = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new BorderLayout());
        
        setContentPane(contentPane);
        contentPane.add(grid);
        contentPane.add(buttons);
        
        //Grid afbeeldingen
        gridPhotos = new ArrayList();
        gridPhotos.add(new ImageIcon("grid1.png"));
        gridPhotos.add(new ImageIcon("grid2.png"));
        
        //Grid:
        grid.add(new JLabel("Grid:"), BorderLayout.NORTH);
        gamePanel = new JPanel(new GridLayout(height, width));
        grid.add(gamePanel, BorderLayout.CENTER);
        
        
        //Buttons
        buttons.add(new JLabel("Knoppen"), BorderLayout.NORTH);
        
        reset();
        
        setSize(800,600);
        setVisible(true);
    }
    
    private void reset()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                final int xx = x;
                final int yy = y;
                field[y][x] = new Cell(new JButton(""));
                gamePanel.add(field[y][x].getButton());
                field[y][x].getButton().addActionListener(e -> click(xx,yy));
            }
        }
    }
    
    private void click(int x, int y)
    {
        field[y][x].getButton().setIcon(new ImageIcon());
    }
    
}
