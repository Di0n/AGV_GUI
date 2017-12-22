import java.awt.*;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class GUI extends JFrame
{
    private ArrayList<Integer> data;

    private int width = 5;
    private int height = 5;
    private boolean firstClick = true;
    private int lastX;
    private int lastY;
    private int secondLastX;
    private int secondLastY;

    Cell field[][] = new Cell[25][25]; 
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
        data = new ArrayList<Integer>();

        //Reset Grid
        resetButton.addActionListener(e-> 
            {
                gamePanel.removeAll();
                gamePanel.revalidate();
                makeGUI();
                firstClick = true;
                data.clear();

            });

        //Stuur Route
        route.addActionListener(e ->
            {
                data.add(0x00);
                System.out.println("einde");
                // for(Integer command : data)
                //     System.out.println(command);

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
                field[y][x].getButton().addActionListener(e ->
                    { 
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
        if(firstClick == true)
        {
            field[y][x].getButton().setIcon(gridPhotos.get(1));
            data.add(0x3e);
            data.add(0x66);
            System.out.println("begin");
            System.out.println("F");

            lastX = x;
            lastY = y;
        }            
        else if(isNextToPrevious(x, y))
        {
            field[y][x].getButton().setIcon(gridPhotos.get(1));
            makeRouteCommand(x, y);
            secondLastX = lastX;
            lastX = x;
            secondLastY = lastY;
            lastY = y;
        }

        firstClick = false;
    }

    private boolean isNextToPrevious(int x, int y)
    {
        boolean isNextTo = false;
        if((x-1 == lastX) && (y == lastY) && x > 0 )
        {
            isNextTo = true;
        }
        else if((y-1 == lastY) && (x == lastX) && y > 0)
        {    
            isNextTo = true;
        }
        else if((x+1 == lastX)&& (y == lastY) && x < 4 )
        {
            isNextTo = true;
        }
        else if((y+1 == lastY) && (x == lastX) && y < 4)
        {
            isNextTo = true;
        }
        else
            isNextTo = false;

        return isNextTo;
    }

    private void makeRouteCommand(int x, int y)
    {
        Integer command;
        if(isGoingStraight(x, y))
        {
            command = 0x66;
            System.out.println("F");
        }
        else if(isGoingLeft(x, y))
        {   
            command = 0x72;
            System.out.println("L");
        }
        else if(isGoingRight(x, y))
        {
            command = 0x6c;
            System.out.println("R");
        }
        else
        {
            command = 0x66;
            System.out.println("moet gefixt worden");
            /* stap na de eerste kruising
             * kan nog niet bepaald worden..
             */ 
        }    
        data.add(command);
        
    }

    private boolean isGoingStraight(int x, int y)
    {
        boolean straight = false;
        if((x == lastX) && (x == secondLastX)) 
            straight = true;
        else if((y == lastY) && (y == secondLastY))
            straight = true;
        else
            straight = false;
        return straight;
    }

    
    /*
     * Volgende twee methodenamen zijn goed
     * Variabelenamen in de methode zijn verkeerd
     * Had ze verkeerdom geimplementeerd
     * en heb geen zin om alles aan te passen..
     */
    
    private boolean isGoingRight(int x, int y)
    {
        boolean left = false;
        if(((x+1 == secondLastX) && (y+1 == secondLastY)) && (x == lastX))
            left = true;
        else if(((x-1 == secondLastX) && (y+1 == secondLastY)) && (y == lastY))
            left = true;
        else if(((x-1 == secondLastX) && (y-1 == secondLastY)) && (x == lastX))
            left = true;
        else if(((x+1 == secondLastX) && (y-1 == secondLastY)) && (y == lastY))
            left = true;
        else
            left = false;

        return left;
    }

    private boolean isGoingLeft(int x, int y)
    {
        boolean right = false;
        if(((x+1 == secondLastX) && (y-1 == secondLastY)) && (x == lastX))
            right = true;
        else if(((x+1 == secondLastX) && (y+1 == secondLastY)) && (y == lastY))
            right = true;
        else if(((x-1 == secondLastX) && (y+1 == secondLastY)) && (x == lastX))
            right = true;
        else if(((x-1 == secondLastX) && (y-1 == secondLastY)) && (y == lastY))
            right = true;
        else
            right = false;
            
        return right;
    }
}
