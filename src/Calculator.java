package src;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import src.Operations.*;

/** Class representing the interface of a simple calculator
 * @author Anton Vaiciukevič
 * @version 1.1
 */
public class Calculator extends JFrame 
                    implements ActionListener, KeyListener
{
    

    private LinkedList<Operation> calculateList = new LinkedList<Operation>();
    private String bNames[] = {"AC", "+/-", "%", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "=", "Save", "Upload"};
    private final int width = 330, height = 535;
    private JButton[] Buttons = new JButton[21];
    private Operation op;
    private JTextField field = new JTextField();
    private JPanel panel = new JPanel();
    private String arg1 = "", arg2 = "", operand = "";
    private JTabbedPane tab = new JTabbedPane();
    private JPanel operations = new JPanel();
    private String fInOut = "save.bin";

    /**Constructor for creating a class object, 
     * creating a window and all other widgets
     * @see Calculator#Calculator()
     */
    public Calculator()
    {
        super("Calculator");
        field.setEditable(false);
        field.setBackground(Color.WHITE);
        field.setPreferredSize(new Dimension(width, height/8));
        field.setFont(new Font("", Font.BOLD, 30));
        field.setHorizontalAlignment(SwingConstants.RIGHT);;
        panel.add(tab, BorderLayout.CENTER);
        panel.add(field);
        getContentPane().add(tab);


        
        for(Integer i = 0; i < 19; i++){
            Buttons[i] = new JButton(bNames[i]);
            Buttons[i].setPreferredSize(new Dimension(75, 75));
            Buttons[i].addActionListener(this);
            panel.add(Buttons[i]);
        }

        Buttons[18].setPreferredSize(new Dimension(155, 75));
        Buttons[19] = new JButton(bNames[19]);
        Buttons[20] = new JButton(bNames[20]);
        Buttons[19].setPreferredSize(new Dimension(width/2 - 10, 55));
        Buttons[19].addActionListener(this);
        Buttons[20].setPreferredSize(new Dimension(width/2 - 10, 55));
        Buttons[20].addActionListener(this);
        operations.add(Buttons[19]);
        operations.add(Buttons[20]);
        tab.addTab("Calculator", panel);
        tab.addTab("Operations",operations);
        setFocusable(true);
        addKeyListener(this);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  
    private void setArg(String action)
    {
        if (!operand.equals("")){
            arg2 += action; 
            field.setText(arg2);
        }
        else{
            arg1 += action;
            field.setText(arg1);
        }
            
    }

    private void saveTh()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                try{
                    Save();
                }catch(Exception ex){}
            }
        });
    }

    private void negate()
    {
        if(operand == ""){
            StringBuffer a = new StringBuffer(arg1);
            a.insert(0, '-');
            arg1 = a.toString();
            field.setText(arg1);
        }
        else{
            StringBuffer a = new StringBuffer(arg2);
            a.insert(0, '-');
            arg2 = a.toString();
            field.setText(arg2);

        }
    }

    private void uploadTh()
    {
        SwingUtilities.invokeLater(
            new Runnable() {
                @Override
                public void run()
                {
                    try{
                        Upload();
                    }catch(Exception ex){}
                }
            }
        );
    }

    private void setOperand(String action)
    {
         if(!operand.isEmpty()){
             calculate();
         }
        
         if (operand.equals("") || arg2.equals(""))
             operand = action;
    }
    
    private void calculate()
    {
        switch (operand) {
            case "+":
                op = new SumOperation(arg1, arg2);
                break;
            case "*":
                op = new MulOperation(arg1, arg2);
                break;
            case "/":
                op = new DivOperation(arg1, arg2);
                break;
            case "-":
                op = new SubOperation(arg1, arg2);
                break;
            case "%":
                op = new ModOperation(arg1, arg2);
                break;
            default:
                return;
        }
        field.setText(op.calculate().toString());
        arg1 = op.getResult().toString();
        calculateList.add(op);
        operand = arg2 = "";
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        char ch = e.getKeyChar();

        if( ch>= '0' &&  ch <= '9' || ch == '.')
            setArg(String.valueOf(ch));
        else if(code == e.VK_ENTER)
            calculate();
        else if(ch == '+' || ch == '*' || ch == '-' || ch == '/' || ch == '%')
            setOperand(String.valueOf(ch));
    }
    @Override
	public void keyReleased(KeyEvent e){}
	@Override

	public void keyTyped(KeyEvent e){}

    public void actionPerformed(ActionEvent e)
    {
        String action = e.getActionCommand();
        
        if ((action.charAt(0) >= '0' && action.charAt(0) <= '9') || action.charAt(0) == '.')
            setArg(action);

        else if (action.equals("AC")) {
            operand = arg1 = arg2 = "";            
            field.setText("");
        }
        else if(action.equals("Upload"))
            uploadTh();
        else if(action.equals("Save"))
            saveTh();
        else if(action.equals("+/-")){
            negate();
        }
        else if (action.equals("="))
            calculate();
        else 
            setOperand(action);

    }
    private void Upload() throws Exception
    {
        FileInputStream fileIn = new FileInputStream(fInOut);
        ObjectInputStream oInS = new ObjectInputStream(fileIn);
        
        calculateList = (LinkedList<Operation>) oInS.readObject();
        
        operations.removeAll();
        operations.add(Buttons[19]);
        operations.add(Buttons[20]);

        for(Integer i = 1; i < calculateList.size() + 1; i++){
            JLabel tmp = new JLabel(i + ")" + calculateList.get(i - 1), SwingConstants.LEFT);
            tmp.setPreferredSize(new Dimension(width - 30, height/20));
            tmp.setFont(new Font("", Font.BOLD, 20));
            operations.add(tmp);   
        }

        oInS.close();
        this.repaint();
    }

    private void Save() throws Exception
    {
        FileOutputStream fileOut = new FileOutputStream(fInOut);
        ObjectOutputStream oOutS = new ObjectOutputStream(fileOut);
        oOutS.reset();
        System.out.println(calculateList);
        oOutS.writeObject(calculateList);

        oOutS.close();
    }
}
    



   
