
import java.io.File;
import java.io.IOException;
import static java.lang.Math.pow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication4.mutimedia.VectorQuantizer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import multimedia.LZ77;

/**
 * @author Abdussalam AbduHelal Al-Shouibi
 */
public class Choosen extends javax.swing.JFrame {

    int numberOfbits = 0;
    String algo = "";
    LZ77 objgui;
    LZW lzw;
    Huffman huff;
    VectorQuantizer vecQuantizer = new VectorQuantizer();
    UniformQuantizer quantization;
    private boolean isSelected;
    private String outputFilePath;

    public Choosen() {
        initComponents();
        objgui = new LZ77();
        lzw = new LZW();
        huff = new Huffman();
        quantization = new UniformQuantizer();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputAddress = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputText = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        outputAddress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 102, 102));
        jPanel1.setEnabled(false);

        jLabel1.setText("Adress");

        inputAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputAddressActionPerformed(evt);
            }
        });

        jButton1.setText("Browes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        inputText.setColumns(20);
        inputText.setForeground(new java.awt.Color(51, 204, 0));
        inputText.setRows(5);
        inputText.setName(""); // NOI18N
        jScrollPane1.setViewportView(inputText);

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        jButton2.setText("Compress");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Decompress");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cancel.setText("Close");
        cancel.setDefaultCapable(false);
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        outputAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputAddressActionPerformed(evt);
            }
        });

        jLabel2.setText("Adress");

        jButton4.setText("Browes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        save.setText("Save");
        save.setVerifyInputWhenFocusTarget(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(outputAddress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(cancel)
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jButton3)
                    .addComponent(cancel)
                    .addComponent(save))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setForeground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        outputText.setColumns(20);
        outputText.setRows(5);
        jScrollPane2.setViewportView(outputText);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(inputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItem1.setText("LZ77");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("LZW");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Huffman");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("UniformQuantizer");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("VectorQuantizer");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        algo = "LZ77";
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void outputAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outputAddressActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        switch (algo) {
            case "LZ77":
                objgui.clear();
                objgui.fileStore = inputText.getText();
                objgui.deCompression();
                outputText.setText(objgui.decompressed);
                break;
            case "LZW":
                lzw.clear();
                lzw.fileStore = inputText.getText();
                lzw.splitAscii();
                lzw.deCompresion();
                outputText.setText(lzw.decompressed);
                break;
            case "Huffman":
                huff.clear();

                huff.fileStore = inputText.getText();
                try {
                    String decompressed = huff.deComperssion();
                } catch (IOException ex) {
                    Logger.getLogger(Choosen.class.getName()).log(Level.SEVERE, null, ex);
                }
                outputText.setText(huff.decompressed);
            case "UniformQuantization":
                try {

                    quantization.openFile("quantization.txt");
                    outputText.setText(quantization.print());
                    quantization.numberOfbits = inputText.getText();
                    int go = Integer.parseInt(quantization.numberOfbits);
                    go = (int) pow(2, go);
                    System.out.println("go:" + go);
                    quantization.deComperssion(go);

                } catch (IOException ex) {
                    Logger.getLogger(Choosen.class.getName()).log(Level.SEVERE, null, ex);
                }

                outputText.setText(quantization.deCompress.toString());
                break;

            case "VectorQuantizer":

                String numberOfbits = inputText.getText();
                int go = Integer.parseInt(numberOfbits);
                System.out.println("go:" + go);
                vecQuantizer.deCompress();

                outputText.setText(quantization.deCompress.toString());
                break;

            default:
                System.out.println("CHOOSE STH!!");
                break;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    //          TODO add your handling code here:
        
        switch (algo) {
            case "LZ77":

                objgui.clear();
                objgui.fileStore = inputText.getText();
                objgui.compression();
                outputText.setText(objgui.print());
                break;
            case "LZW":
                lzw.clear();
                lzw.fileStore = inputText.getText();
                lzw.comprision();
                outputText.setText(lzw.print());
                break;
            case "Huffman":
                huff.clear();
                huff.fileStore = inputText.getText();
                huff.removeDup();
                String temp = "";
                try {
                    temp = huff.comprision();
                    outputText.setText(huff.dictionary.toString() + temp);
                } catch (IOException ex) {
                    Logger.getLogger(Choosen.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case "UniformQuantization":
                quantization.fileStore = inputText.getText();
                try {
                    quantization.numberOfbits = inputText.getText();
                    int go = Integer.parseInt(quantization.numberOfbits);
                    go = (int) pow(2, go);
                    quantization.comperssion(go);
                } catch (IOException ex) {
                    Logger.getLogger(Choosen.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "VectorQuantizer":
                System.out.println("Hello");
                String numberOfbits = inputText.getText();
                System.out.println("Hello2"+numberOfbits);
                if (numberOfbits.equals("")) {
                    System.out.println("Hello3");
                    JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                    JOptionPane.showMessageDialog(frame, "Please Enter Number Of bits  !");
                   // System.exit(0);
                    //00967774625738
                } else {
                    System.out.println("Hello5");
                    int go = Integer.parseInt(numberOfbits);
                    // go = (int) pow(2, go);
                    vecQuantizer.compress(go);
                    break;
                }
            default:
                System.out.println("CHOOSE STH");
                break;

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void inputAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputAddressActionPerformed
        switch (algo) {
            case "LZ77":
                System.out.print(inputAddress.getText());
                try {
                    objgui.openFile(inputAddress.getText());
                } catch (Exception e) {
                }
                break;
            case "LZW":
                System.out.print(inputAddress.getText());
                try {
                    lzw.openFile(inputAddress.getText());
                } catch (Exception e) {
                }
                break;
            case "Huffman":
                System.out.print("hhhhhhhh " + inputAddress.getText());
                try {
                    huff.openFile(inputAddress.getText());
                } catch (Exception e) {

                }
        }
    }//GEN-LAST:event_inputAddressActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\Abdussalam AbduHelal\\Desktop"));
        int returnval = chooser.showOpenDialog(this);
        if (returnval == JFileChooser.APPROVE_OPTION) {
            final String path = chooser.getSelectedFile().getPath();
            inputAddress.setText(path);
            inputText.setText(objgui.fileStore);
            switch (algo) {
                case "UniformQuantization":
                    inputAddress.setText(path);
                    quantization.readImage(path);
                    break;
                //inputText.setText("Enter the number of bits");

                case "VectorQuantizer":
                    inputAddress.setText(path);
                    vecQuantizer.readImage(path);
                    break;
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed

    }//GEN-LAST:event_jFileChooser1ActionPerformed
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if (isSelected) {
            switch (algo) {
                case "LZ77":
                    objgui.FileWrite(outputFilePath);
                    break;
                case "LZW":
                    lzw.FileWrite(outputFilePath);
                    break;
                case "Huffman":
                    try {
                        huff.FileWrite(outputFilePath);
                    } catch (IOException ex) {
                        Logger.getLogger(Choosen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }

        } else {

            throw new ExceptionInInitializerError("output address is not specified !!");
        }
    }//GEN-LAST:event_saveActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\Abdussalam AbduHelal\\Desktop"));
        int returnval = chooser.showOpenDialog(this);
        if (returnval == JFileChooser.APPROVE_OPTION) {

            outputFilePath = chooser.getSelectedFile().getPath();
            outputAddress.setText(outputFilePath);
            isSelected = true;
        }
    }

        //    isSelected = true;    }//GEN-LAST:event_jButton4ActionPerformed
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        algo = "LZW";
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        algo = "Huffman";
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        algo = "UniformQuantization";
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        algo = "VectorQuantizer";
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                Choosen c = new Choosen();
                c.setIconImage(null);
                c.setTitle("Abdussalam Compresser");
                c.setSize(480, 350);///[428, 426]
                c.setResizable(false);
                c.setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JTextField inputAddress;
    private javax.swing.JTextArea inputText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField outputAddress;
    private javax.swing.JTextArea outputText;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
