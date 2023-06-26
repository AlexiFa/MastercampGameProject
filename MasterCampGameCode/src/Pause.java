import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
public class Pause extends JFrame{
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private int selected;
    private ArrayList<String> options = new ArrayList<String>();

    public Pause(){
        initComponents();
        setTitle("Pause");
        setLocationRelativeTo(null);
        //options.add("[Resume]");
        options.add("[Restart]");
        options.add("Quit");
        selected = 0;
        setText();
        selected = 0;
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(9);
        jTextArea1.setRows(9);
        jTextArea1.setFont(new Font("Courier New", 1, 15));
        jTextArea1.setForeground(Color.WHITE);
        jTextArea1.setBackground(Color.BLACK);
        jTextArea1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 725, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
    }

    private void jTextArea1KeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if (selected < options.size() - 1) {
                    selected++;
                    setSelected();
                    setText();
                }
                break;
            case KeyEvent.VK_UP:
                if (selected > 0) {
                    selected--;
                    setSelected();
                    setText();
                }
                break;
            case KeyEvent.VK_ENTER:
                switch (selected) {
                    case 0:
                        this.dispose();
                        new View().setVisible(true); // todo : faire marcher le load pour reprendre la partie
                        break;
                    case 2:
                        this.dispose();
                        new View().setVisible(true);
                        break;
                    case 1:
                        System.exit(0);
                        break;
                }
                break;
        }
    }
    public void setSelected(){
        for (String s: options){
            if (s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']'){
                options.set(options.indexOf(s), s.substring(1, s.length() - 1));
            }
        }
        options.set(selected, "[" + options.get(selected) + "]");
    }
    public void setText(){
        jTextArea1.setText("");
        for (String s : options) {
            jTextArea1.append(s + "\n");
        }
    }
}
