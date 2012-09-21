/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * simpleGUI.java
 *
 * Created on 14-mrt-2010, 12:07:36
 */

package com.rs2hd.tools;


import org.apache.mina.common.IoFuture;
import org.apache.mina.common.IoFutureListener;

import com.rs2hd.packetbuilder.StaticPacketBuilder;
import com.rs2hd.model.World;
import com.rs2hd.model.Player;
import com.rs2hd.Server;

/**
 *
 * @author Eigenaar
 */
@SuppressWarnings({ "serial", "unused" })
public class simpleGUI extends javax.swing.JFrame {

    /** Creates new form simpleGUI */
    public simpleGUI() {
        initComponents();
	setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Mass Kick (Save)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        for(Player p : World.getInstance().getPlayerList()) {
				p.getActionSender().sendMessage("Disconnecting everyone... please logout to avoid rollbacks.");
				p.getSession().write(new StaticPacketBuilder().setId(236).toPacket()).addListener(new IoFutureListener() {
			public void operationComplete(IoFuture arg0) {
				arg0.getSession().close();
			}
		});
			}

World.getInstance().engine().setIsRunning(false);



    }


    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    // End of variables declaration

}
