/*
 * PjWikiView.java
 */

package pjwiki;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit;

/**
 * The application's main frame.
 */
public class PjWikiView extends FrameView {

    // TODO: Look into alternative html renderer. Mozilla's mayhaps? How about the chrome engine?


    /**
     *
     * @param app
     */
    public PjWikiView(SingleFrameApplication app, WikiWord launchWithWikiWord) throws Exception {
        super(app);

        if(launchWithWikiWord != null)
        {
            WikiWord.current = launchWithWikiWord;
        }
        else
        {
            WikiWord.current = new WikiWord(WikiWord.INDEX_TEXT);
        }
        currentWikiWordFile = new WikiWordFile(WikiWord.current);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        externalProtocols = new ArrayList<String>();
        externalProtocols.add("http");externalProtocols.add("https");
        externalProtocols.add("ftp");externalProtocols.add("file");
        externalProtocols.add("mail");

        currentText =
                "====== Test Text ======\r\n" +
                "I'm just testing **this** stuff. Maybe I should try to" +
                " link to [[http://www.google.com|Google]]. //Yeah?!//\r\n" +
                "\r\n" +
                "testing a new paragraph!\r\n" +
                "===== subsection =====\r\n" +
                "===== subsection2 =====\r\n" +
                "===== subsection3 =====\r\n" +
                "Need to add paragraph handling and such\r\n";


        wikiSyntaxManager = new WikiSyntaxManager();
        setEditState(state.VIEW);
    }

    /**
     *
     */
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = PjWikiApp.getApplication().getMainFrame();
            aboutBox = new PjWikiAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        PjWikiApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentTextPane = new javax.swing.JTextPane();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        navBackButton = new javax.swing.JButton();
        navLocationTextField1 = new javax.swing.JTextField();
        navGoButton = new javax.swing.JButton();
        navForwardButton = new javax.swing.JButton();
        navEditSeparator = new javax.swing.JToolBar.Separator();
        editButton = new javax.swing.JButton();
        editSearchSeparator = new javax.swing.JToolBar.Separator();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tocTextPane = new javax.swing.JTextPane();
        jToolBar2 = new javax.swing.JToolBar();
        homeButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        contentTextPane.setDoubleBuffered(true);
        contentTextPane.setName("contentTextPane"); // NOI18N
        contentTextPane.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                contentTextPaneHyperlinkUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(contentTextPane);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N
        jToolBar1.setPreferredSize(new java.awt.Dimension(446, 52));
        jToolBar1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jToolBar1ComponentRemoved(evt);
            }
        });

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(444, 25));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        navBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pjwiki/resources/icons16/onebit_50-28.png"))); // NOI18N
        navBackButton.setFocusable(false);
        navBackButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        navBackButton.setName("navBackButton"); // NOI18N
        navBackButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(navBackButton);

        navLocationTextField1.setName("navLocationTextField1"); // NOI18N
        navLocationTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                navLocationTextField1KeyReleased(evt);
            }
        });
        jPanel4.add(navLocationTextField1);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pjwiki.PjWikiApp.class).getContext().getResourceMap(PjWikiView.class);
        navGoButton.setText(resourceMap.getString("navGoButton.text")); // NOI18N
        navGoButton.setFocusable(false);
        navGoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        navGoButton.setMinimumSize(new java.awt.Dimension(30, 23));
        navGoButton.setName("navGoButton"); // NOI18N
        navGoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        navGoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navGoButtonActionPerformed(evt);
            }
        });
        jPanel4.add(navGoButton);

        navForwardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pjwiki/resources/icons16/onebit_50-26.png"))); // NOI18N
        navForwardButton.setFocusable(false);
        navForwardButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        navForwardButton.setName("navForwardButton"); // NOI18N
        navForwardButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(navForwardButton);

        navEditSeparator.setName("navEditSeparator"); // NOI18N
        jPanel4.add(navEditSeparator);

        editButton.setText(resourceMap.getString("editButton.text")); // NOI18N
        editButton.setFocusable(false);
        editButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editButton.setName("editButton"); // NOI18N
        editButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        jPanel4.add(editButton);

        editSearchSeparator.setMaximumSize(new java.awt.Dimension(6, 0));
        editSearchSeparator.setName("editSearchSeparator"); // NOI18N
        jPanel4.add(editSearchSeparator);

        searchTextField.setName("searchTextField"); // NOI18N
        jPanel4.add(searchTextField);

        searchButton.setText(resourceMap.getString("searchButton.text")); // NOI18N
        searchButton.setFocusable(false);
        searchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchButton.setName("searchButton"); // NOI18N
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(searchButton);

        jPanel3.add(jPanel4);

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setText(resourceMap.getString("previewButton.text")); // NOI18N
        jButton1.setName("previewButton"); // NOI18N
        jPanel5.add(jButton1);

        jPanel3.add(jPanel5);

        jToolBar1.add(jPanel3);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setRightComponent(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tocTextPane.setName("tocTextPane"); // NOI18N
        jScrollPane2.setViewportView(tocTextPane);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setName("jToolBar2"); // NOI18N

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pjwiki/resources/icons16/onebit_50-0.png"))); // NOI18N
        homeButton.setFocusable(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setName("homeButton"); // NOI18N
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(homeButton);

        jPanel2.add(jToolBar2, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setLeftComponent(jPanel2);

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(pjwiki.PjWikiApp.class).getContext().getActionMap(PjWikiView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
            .add(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(statusMessageLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 528, Short.MAX_VALUE)
                .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelLayout.createSequentialGroup()
                .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(statusMessageLabel)
                    .add(statusAnimationLabel)
                    .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void navGoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navGoButtonActionPerformed

    }//GEN-LAST:event_navGoButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if(editState == state.VIEW)
        { 
            setEditState(state.EDIT);
        }else{
            setEditState(state.VIEW);
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void jToolBar1ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jToolBar1ComponentRemoved

    }//GEN-LAST:event_jToolBar1ComponentRemoved

    private void contentTextPaneHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_contentTextPaneHyperlinkUpdate
        if(evt.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED)
        {
            
            URL url = evt.getURL();
            if(url != null && !url.sameFile(contentTextPane.getPage()) && externalProtocols.contains(url.getProtocol()))
            {
                String osName = System.getProperty("os.name");
                if (osName.startsWith("Windows"))
                   try {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                    // JAVA 6: java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                } catch (IOException ex) {
                    Logger.getLogger(PjWikiView.class.getName()).log(Level.SEVERE, null, ex);
                }
                // JAVA 6: java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            }
            else
            {
                if(evt.getDescription().charAt(0) == '#')
                {
                    try {
                        contentTextPane.setPage(contentTextPane.getPage().toString().split("#")[0] + evt.getDescription());
                    } catch (IOException ex) {
                        Logger.getLogger(PjWikiView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_contentTextPaneHyperlinkUpdate

    private void navLocationTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_navLocationTextField1KeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE)
        {
            navLocationTextField1.setText(WikiWord.current.toString());
        }
    }//GEN-LAST:event_navLocationTextField1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane contentTextPane;
    private javax.swing.JButton editButton;
    private javax.swing.JToolBar.Separator editSearchSeparator;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton navBackButton;
    private javax.swing.JToolBar.Separator navEditSeparator;
    private javax.swing.JButton navForwardButton;
    private javax.swing.JButton navGoButton;
    private javax.swing.JTextField navLocationTextField1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextPane tocTextPane;
    // End of variables declaration//GEN-END:variables

    private void renderText()
    {
        if(contentTextPane.getContentType().contentEquals("text/html") && editState == state.EDIT)
        {
            try{
                contentTextPane.setEditorKit(new StyledEditorKit());
                contentTextPane.setEditable(true);
                contentTextPane.setText(currentText);
            }catch(Exception e){
                JOptionPane.showMessageDialog(PjWikiApp.getApplication().getMainFrame(),e.toString());
            }
        }
        else
        {
            try{
                WikiSyntaxParserFormatting w = new WikiSyntaxParserFormatting();
                WikiSyntaxParserHeaders w2 = new WikiSyntaxParserHeaders();
                String text = WikiHtmlFormatter.format(wikiSyntaxManager.format(currentText));
                //contentTextPane.setContentType("text/html");
                contentTextPane.setEditable(false);
                contentTextPane.setContentType("text/html");
                File tempFile = File.createTempFile("wiki", ".html");
                FileWriter fstream = new FileWriter(tempFile);
                fstream.write(text);
                fstream.close();
                contentTextPane.setPage(tempFile.toURL());
            }catch(Exception e){
                JOptionPane.showMessageDialog(PjWikiApp.getApplication().getMainFrame(),e.toString());
            }
        }
        contentTextPane.revalidate();
    }

    private void setEditState(state s)
    {
        GridLayout l = (GridLayout)jPanel3.getLayout();
        Dimension d = jToolBar1.getPreferredSize();
        if(s == state.VIEW)
        {
            if(jPanel5.getParent() != null)
            {
                jPanel3.remove(jPanel5);
            }
            d.height = 27;
            l.setRows(1);
        }
        else if(s == state.PREVIEW || s == state.EDIT )
        {
            l.setRows(2);
            d.height = 52;
            if(jPanel5.getParent() == null)
            {
                jPanel3.add(jPanel5);
            }
        }

        if((s== state.PREVIEW || s == state.VIEW) && editState == state.EDIT)
        {
            currentText = contentTextPane.getText();
        }else{
            contentTextPane.setText(currentText);
        }
        editState = s;

        renderText();

        jToolBar1.setPreferredSize(d);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    private WikiWordFile currentWikiWordFile;
    enum state{
        VIEW,
        EDIT,
        PREVIEW;
    }
    private state editState;

    private String currentText;

    private WikiSyntaxManager wikiSyntaxManager;

    private List<String> externalProtocols;
    
}
