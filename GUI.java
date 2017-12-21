import java.awt.*;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class GUI extends JFrame
{
    private int width = 5;
    private int height = 5;
    
    Cell field[][] = new Cell[25][25]; 
    ArrayList<ImageIcon> gridPhotos;
    JPanel gamePanel;
    
    public GUI()
    {
        super("GUI");
        
        JPanel contentPane = new JPanel(new GridLayout(0,2,0,0));
        JPanel grid = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(3,1));
        
        setContentPane(contentPane);
        contentPane.add(grid);
        contentPane.add(buttons);
        
        //Grid afbeeldingen
        gridPhotos = new ArrayList();
        gridPhotos.add(new ImageIcon("grid1.png"));
        gridPhotos.add(new ImageIcon("grid2.png"));
        gridPhotos.add(new ImageIcon("grind3.png"));
        
        //Grid:
        grid.add(new JLabel("Grid:"), BorderLayout.NORTH);
        gamePanel = new JPanel(new GridLayout(height, width,0,0));
        grid.add(gamePanel, BorderLayout.CENTER);
        //JLabel gridPhoto = new JLabel(gridPhotos.get(0));
        
        //Buttons
        buttons.add(new JLabel("Tekst voor de knopper of informatie over de GUI"));
        JButton resetButton = new JButton("Reset");
        JButton route = new JButton("Stuur Route naar BoeBot:");
        buttons.add(resetButton);
        buttons.add(route);
        
        //Reset Grid
        resetButton.addActionListener(e-> 
        {

            
        });
        
        //Stuur Route
        route.addActionListener(e ->
        {
            
        });
        
        reset();
        validate();
        setSize(1250,650);
        //setPreferredSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        field[y][x].getButton().setIcon(gridPhotos.get(1));
    }
    
}
