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
<<<<<<< HEAD
    private boolean firstClick = true;
    private int lastX;
    private int lastY;

    Cell field[][] = new Cell[25][25]; 
=======
    
    Cell field[][] = new Cell[5][5]; 
>>>>>>> develop
    ArrayList<ImageIcon> gridPhotos;
    JPanel gamePanel;

    public GUI()
    {
        super("GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1250,650));
        setMaximumSize(new Dimension(1250,650));
        //setSize(1250,650);
        //setPreferredSize(new Dimension(400, 300));

        JPanel contentPane = new JPanel(new GridLayout(0,2,0,0));
        JPanel grid = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(3,1));

        setContentPane(contentPane);
        contentPane.add(grid);
        contentPane.add(buttons);

        //Grid afbeeldingen
        gridPhotos = new ArrayList<ImageIcon>();
        gridPhotos.add(new ImageIcon("grid1.png"));
        gridPhotos.add(new ImageIcon("grid2.png"));
<<<<<<< HEAD

=======
        
>>>>>>> develop
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

        makeGUI();

        //Reset Grid
        resetButton.addActionListener(e-> 
            {
                gamePanel.removeAll();
                gamePanel.revalidate();
                makeGUI();
                firstClick = true;
            });

        //Stuur Route
        route.addActionListener(e ->
            {

            });

        setVisible(true);

    }

    private void makeGUI()
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                final int xx = x;
                final int yy = y;
                field[y][x] = new Cell(new JButton(""));

                gamePanel.add(field[y][x].getButton());
                field[y][x].getButton().addActionListener(e ->{ 
                        click(xx,yy);
                    });
            }
        }
    }

    private void reset()
    {
    }

    private void click(int x, int y)
    {
<<<<<<< HEAD
        if(firstClick == true)
        {
            field[y][x].getButton().setIcon(gridPhotos.get(1));
            lastX = x;
            lastY = y;
        }            
        else if(isNextToPrevious(x, y))
        {
            field[y][x].getButton().setIcon(gridPhotos.get(1));
            lastX = x;
            lastY = y;
        }
        

        firstClick = false;
    }
    
    private boolean isNextToPrevious(int x, int y)
    {
        boolean isNextTo = false;
        if((x-1 == lastX) && (y == lastY) && x > 0 && field[y][x-1].getButton().getIcon().equals(gridPhotos.get(1)))
            isNextTo = true;
        else if((y-1 == lastY) && (x == lastX) && y > 0 && field[y-1][x].getButton().getIcon().equals(gridPhotos.get(1)))
            isNextTo = true;
        else if((x+1 == lastX)&& (y == lastY) && x < 4 && field[y][x+1].getButton().getIcon().equals(gridPhotos.get(1)))
            isNextTo = true;
        else if((y+1 == lastY) && (x == lastX) && y < 4 && field[y+1][x].getButton().getIcon().equals(gridPhotos.get(1)))
            isNextTo = true;
        else
            isNextTo = false;
        
        
        return isNextTo;
    }

=======
        field[y][x].getButton().setIcon(gridPhotos.get(1));
        System.out.println(field[y][x].getX());
    }
>>>>>>> develop
}
