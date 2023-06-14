public class View extends javax.swing.JFrame{

    private Map map = new Map();
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    public View(){
        initComponents();
        setTitle("Interface Jeu");
        setLocationRelativeTo(null);
        jTextArea1.setText(map.toString());
    }


    private void initComponents(){

        //Zone défilement
        jScrollPane1 = new javax.swing.JScrollPane();
        //Zone de texte
        jTextArea1 = new javax.swing.JTextArea();

        //Lorsque l'on ferme la fenêtre
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(9);
        jTextArea1.setRows(9);
        jTextArea1.setFont(new java.awt.Font("Courier", 1, 36));
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {
        map.move(evt.getKeyCode());
        jTextArea1.setText(map.toString());
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

}
