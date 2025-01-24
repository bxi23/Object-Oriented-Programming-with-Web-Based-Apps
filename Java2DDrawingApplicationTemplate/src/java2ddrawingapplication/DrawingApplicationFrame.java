/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawingapplication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{
    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.
    private final JPanel buttonLine1 = new JPanel();
    private final JPanel buttonLine2 = new JPanel();
    private final JPanel topPanel = new JPanel();
    private DrawPanel drawPanel = new DrawPanel();
    
    
    // create the widgets for the firstLine Panel.
    private final JButton undo;
    private final JButton clear;
    private final JLabel shapeListLabel;
    private final JComboBox<String> shapeCombo;
    private final JCheckBox filledBox;
    
    //create the widgets for the secondLine Panel.
    private final JCheckBox gradientBox;
    private final JButton color1;
    private final JButton color2;
    private final JLabel lineWidthLabel;
    private final JTextField lineWidthField;
    private final JLabel dashLengthLabel;
    private final JTextField dashLengthField;
    private final JCheckBox dashedBox;
    
    //ShapeCombo names
    private static final String[] names ={"Line","Oval","Rectangle"};
    
    // Variables for drawPanel.
    private Color firstColor=Color.BLACK;
    private Color secondColor=Color.BLACK;
    private Paint currentPaint = firstColor;
    private MyShapes[] shapes= new MyShapes[250];
    private int itemCount=0;
    private String shapeType;
    private MyShapes currentShape;
    private Stroke currentStroke;
    private int lineWidth;
    private float[] dashLength= new float[1];
    private boolean filledStatus;
    private boolean gradientStatus;
    private boolean dashedStatus;
        
          // add status label
    private JLabel statusLabel;
    private int mouseX, mouseY = 0;
    
    
    // Constructor for DrawingApplicationFrame 

    public DrawingApplicationFrame()
    {   super("Java 2D Drawings");
        // add widgets to panels
        ColorSelect1 color1Handler =new ColorSelect1();
        ColorSelect2 color2Handler =new ColorSelect2();
        UndoHandler undoHandler = new UndoHandler();
        ClearHandler clearHandler = new ClearHandler();
        //Line1
        undo = new JButton("Undo");
                    undo.addActionListener(undoHandler);
        clear = new JButton("Clear");
                    clear.addActionListener(clearHandler);
        shapeListLabel= new JLabel("Shape:");
        shapeCombo= new JComboBox<String>(names);
                    shapeCombo.setMaximumRowCount(3);
        filledBox = new JCheckBox("Filled");
        
        //Line2
        gradientBox= new JCheckBox("Use Gradient");
        color1 = new JButton("1st Color...");
            color1.addActionListener(color1Handler);
        color2 = new JButton ("2nd Color...");
            color2.addActionListener(color2Handler);
        lineWidthLabel = new JLabel("Line Width:");
        lineWidthField= new JTextField(2);
            lineWidthField.setText("10");
        dashLengthLabel = new JLabel("Dash Length:");
        dashLengthField = new JTextField(2);
            dashLengthField.setText("15");
        dashedBox= new JCheckBox("Dashed");
        
        //StatusLabel
        statusLabel= new JLabel(String.format("(%s,%s)", mouseX,mouseY));

        
        
        // firstLine widgets
        buttonLine1.setLayout(new FlowLayout());
        buttonLine1.add(undo);
        buttonLine1.add(clear);
        buttonLine1.add(shapeListLabel);
        buttonLine1.add(shapeCombo);
        buttonLine1.add(filledBox);
        
        // secondLine widgets
        buttonLine2.setLayout(new FlowLayout());
        buttonLine2.add(gradientBox);
        buttonLine2.add(color1);
        buttonLine2.add(color2);
        buttonLine2.add(lineWidthLabel);
        buttonLine2.add(lineWidthField);
        buttonLine2.add(dashLengthLabel);
        buttonLine2.add(dashLengthField);
        buttonLine2.add(dashedBox);
        
        // add top panel of two panels
        topPanel.setLayout(new BorderLayout());
        topPanel.add(buttonLine1,"North");
        topPanel.add(buttonLine2,"South");

        
        // add topPanel to North, drawPanel to Center, and statusLabel to South
        setLayout(new BorderLayout());
        add(topPanel, "North");
        add(drawPanel, "Center");
        add(statusLabel, "South");
        //add listeners and event handlers
    }

    // Create event handlers, if needed
    // Color Selection Handlers
    private class ColorSelect1 implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent event) {
            
            firstColor = JColorChooser.showDialog(topPanel, "Choose a Color", firstColor);  
            if (firstColor==null)
           {firstColor=Color.WHITE;}
        }
    }
    
    private class ColorSelect2 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

        secondColor = JColorChooser.showDialog(topPanel, "Choose a Color", secondColor);
        if (secondColor==null)
        {secondColor=Color.WHITE;}

    }
}

    //Undo
    
    private class UndoHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
          if (itemCount!=0){
              shapes[itemCount]=null;
              itemCount--;
              repaint();
          }
        }
    }
    
    private class ClearHandler implements ActionListener { 
        @Override
        public void actionPerformed(ActionEvent event){
            shapes= new MyShapes[250];
            itemCount=0;
            repaint();
        }
    }
    
    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {

        private int count=0;
        
        public DrawPanel()
        {
            itemCount=0;
            setBackground(Color.WHITE);
            addMouseMotionListener(new MouseHandler());   
            addMouseListener(new MouseHandler());
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

                int count=0;
                while (itemCount>count)
                {
                    shapes[count].draw(g2d);
                    //g.drawLine(count*20, count*10, 2, 2);
                    count++;
                }
                if (currentShape!=null)
                {currentShape.draw(g2d);}
            
                    
            
        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {

            public void mousePressed(MouseEvent event)
            {
                mouseX=event.getX();
                mouseY=event.getY();
                statusLabel.setText( String.format("(%s,%s)", mouseX,mouseY));
                
                //check boxes
                if (filledBox.isSelected())
                {filledStatus=true;}
                else
                {filledStatus=false;}
                
                if (gradientBox.isSelected())
                {gradientStatus=true;}
                else
                {gradientStatus=false;}
                
                if (dashedBox.isSelected())
                {dashedStatus=true;}
                else
                {dashedStatus=false;}
                
                //Combo Box
                shapeType=shapeCombo.getSelectedItem().toString();
                
                //Lengths and Widths
                lineWidth=Integer.parseInt(lineWidthField.getText());
                dashLength[0]=Float.parseFloat(dashLengthField.getText());
                
                //paint
                if (gradientStatus==true)
                {currentPaint = new GradientPaint(0, 0, firstColor, 50, 50, secondColor, true);}
                else
                {currentPaint= firstColor;}
                

                //stroke
                currentStroke= new BasicStroke(lineWidth);
                if (dashedStatus==true)
                {
                    currentStroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashLength, 0);
                } else
                {
                    currentStroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                }
                //draw
                if (shapeType=="Line")
                {
                    
                    currentShape= new MyLine(new Point(event.getX(),event.getY()),new Point(event.getX(),event.getY()), currentPaint, currentStroke );
                    
                }
                else if (shapeType=="Rectangle")
                {
                    currentShape= new MyRectangle(new Point(event.getX(),event.getY()), new Point(event.getX(),event.getY()), currentPaint, currentStroke, filledStatus);
                }
                else
                {
                    currentShape= new MyOval(new Point(event.getX(),event.getY()),new Point(event.getX(),event.getY()), currentPaint, currentStroke, filledStatus);
                }
                
               repaint();
            }

            public void mouseReleased(MouseEvent event)
            {
                mouseX=event.getX();
                mouseY=event.getY();
                statusLabel.setText(String.format("(%s,%s)", mouseX,mouseY));
                
                shapes[itemCount]=currentShape;
                itemCount++;
                currentShape=null;
                repaint();

            }

            @Override
            public void mouseDragged(MouseEvent event)
            {
 
                if (shapeType=="Line")
                {
                    
                    currentShape= new MyLine(currentShape.getStartPoint(),new Point(event.getX(),event.getY()), currentPaint, currentStroke );
                    
                }
                else if (shapeType=="Rectangle")
                {
                    currentShape= new MyRectangle(currentShape.getStartPoint(), new Point(event.getX(),event.getY()), currentPaint, currentStroke, filledStatus);
                }
                else
                {
                    currentShape= new MyOval(currentShape.getStartPoint(),new Point(event.getX(),event.getY()), currentPaint, currentStroke, filledStatus);
                }

            repaint();
            }

            @Override
            public void mouseMoved(MouseEvent event)
                    
            {
                mouseX=event.getX();
                mouseY=event.getY();
                statusLabel.setText(String.format("(%s,%s)", mouseX,mouseY));
            }
        }

    }
}
