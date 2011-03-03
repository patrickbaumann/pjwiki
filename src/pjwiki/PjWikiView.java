/*
 * PjWikiView.java
 */

package pjwiki;

import java.awt.Dimension;
import java.awt.GridLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private WikiWordPageBase currentWikiWordPage;
    private List<WikiWordPageBase> history;
    private int historyLocation;
    private WikiSyntaxManager wikiSyntaxManager;
    private WikiWordPageFactoryBase pageFactory;
    private List<String> externalProtocols;
    private PjWikiStateMachine stateMachine;
    private String currentText;

    /**
     *
     * @param app
     */
    public PjWikiView(
            SingleFrameApplication app,
            WikiWordPageFactoryBase pageFactory,
            WikiWord launchWithWikiWord) throws Exception
    {
        super(app);

        this.pageFactory = pageFactory;
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

        if(launchWithWikiWord != null)
        {
            currentWikiWordPage = pageFactory.getWikiWordPage(launchWithWikiWord);
        } else {
            currentWikiWordPage = pageFactory.getWikiWordPage(WikiWord.INDEX_TEXT);
        }

        externalProtocols = new ArrayList<String>();
        externalProtocols.add("http");
        externalProtocols.add("https");
        externalProtocols.add("ftp");
        externalProtocols.add("file");
        externalProtocols.add("mail");

        history = new ArrayList<WikiWordPageBase>();
        wikiSyntaxManager = new WikiSyntaxManager();
        stateMachine = new PjWikiStateMachine(this);
        
        stateMachine.transitionNavigate(currentWikiWordPage);      
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
        wikiViewSplitPane = new javax.swing.JSplitPane();
        contentPanel = new javax.swing.JPanel();
        contentScrollPane = new javax.swing.JScrollPane();
        contentTextPane = new javax.swing.JTextPane();
        contentToolbar = new javax.swing.JToolBar();
        contentToolbarPane = new javax.swing.JPanel();
        navigationToolbar = new javax.swing.JPanel();
        navBackButton = new javax.swing.JButton();
        navLocationTextField1 = new javax.swing.JTextField();
        navGoButton = new javax.swing.JButton();
        navForwardButton = new javax.swing.JButton();
        navEditSeparator = new javax.swing.JToolBar.Separator();
        editButton = new javax.swing.JButton();
        editSearchSeparator = new javax.swing.JToolBar.Separator();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        editToolbar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        tableOfContentsJPanel = new javax.swing.JPanel();
        tableOfContentsScrollPane = new javax.swing.JScrollPane();
        tocTextPane = new javax.swing.JTextPane();
        tableOfContentsToolbar = new javax.swing.JToolBar();
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

        wikiViewSplitPane.setDividerLocation(200);
        wikiViewSplitPane.setName("wikiViewSplitPane"); // NOI18N

        contentPanel.setName("contentPanel"); // NOI18N
        contentPanel.setLayout(new java.awt.BorderLayout());

        contentScrollPane.setName("contentScrollPane"); // NOI18N

        contentTextPane.setDoubleBuffered(true);
        contentTextPane.setName("contentTextPane"); // NOI18N
        contentTextPane.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                contentTextPaneHyperlinkUpdate(evt);
            }
        });
        contentScrollPane.setViewportView(contentTextPane);

        contentPanel.add(contentScrollPane, java.awt.BorderLayout.CENTER);

        contentToolbar.setFloatable(false);
        contentToolbar.setRollover(true);
        contentToolbar.setName("contentToolbar"); // NOI18N
        contentToolbar.setPreferredSize(new java.awt.Dimension(446, 52));
        contentToolbar.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                contentToolbarComponentRemoved(evt);
            }
        });

        contentToolbarPane.setName("contentToolbarPane"); // NOI18N
        contentToolbarPane.setPreferredSize(new java.awt.Dimension(444, 25));
        contentToolbarPane.setLayout(new java.awt.GridLayout(2, 0));

        navigationToolbar.setName("navigationToolbar"); // NOI18N
        navigationToolbar.setLayout(new javax.swing.BoxLayout(navigationToolbar, javax.swing.BoxLayout.LINE_AXIS));

        navBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pjwiki/resources/icons16/onebit_50-28.png"))); // NOI18N
        navBackButton.setFocusable(false);
        navBackButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        navBackButton.setName("navBackButton"); // NOI18N
        navBackButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        navigationToolbar.add(navBackButton);

        navLocationTextField1.setName("navLocationTextField1"); // NOI18N
        navLocationTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                navLocationTextField1KeyReleased(evt);
            }
        });
        navigationToolbar.add(navLocationTextField1);

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
        navigationToolbar.add(navGoButton);

        navForwardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pjwiki/resources/icons16/onebit_50-26.png"))); // NOI18N
        navForwardButton.setFocusable(false);
        navForwardButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        navForwardButton.setName("navForwardButton"); // NOI18N
        navForwardButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        navigationToolbar.add(navForwardButton);

        navEditSeparator.setName("navEditSeparator"); // NOI18N
        navigationToolbar.add(navEditSeparator);

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
        navigationToolbar.add(editButton);

        editSearchSeparator.setMaximumSize(new java.awt.Dimension(6, 0));
        editSearchSeparator.setName("editSearchSeparator"); // NOI18N
        navigationToolbar.add(editSearchSeparator);

        searchTextField.setName("searchTextField"); // NOI18N
        navigationToolbar.add(searchTextField);

        searchButton.setText(resourceMap.getString("searchButton.text")); // NOI18N
        searchButton.setFocusable(false);
        searchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchButton.setName("searchButton"); // NOI18N
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        navigationToolbar.add(searchButton);

        contentToolbarPane.add(navigationToolbar);

        editToolbar.setName("editToolbar"); // NOI18N
        editToolbar.setLayout(new javax.swing.BoxLayout(editToolbar, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setText(resourceMap.getString("previewButton.text")); // NOI18N
        jButton1.setName("previewButton"); // NOI18N
        editToolbar.add(jButton1);

        contentToolbarPane.add(editToolbar);

        contentToolbar.add(contentToolbarPane);

        contentPanel.add(contentToolbar, java.awt.BorderLayout.PAGE_START);

        wikiViewSplitPane.setRightComponent(contentPanel);

        tableOfContentsJPanel.setName("tableOfContentsJPanel"); // NOI18N
        tableOfContentsJPanel.setLayout(new java.awt.BorderLayout());

        tableOfContentsScrollPane.setName("tableOfContentsScrollPane"); // NOI18N

        tocTextPane.setName("tocTextPane"); // NOI18N
        tableOfContentsScrollPane.setViewportView(tocTextPane);

        tableOfContentsJPanel.add(tableOfContentsScrollPane, java.awt.BorderLayout.CENTER);

        tableOfContentsToolbar.setFloatable(false);
        tableOfContentsToolbar.setRollover(true);
        tableOfContentsToolbar.setName("tableOfContentsToolbar"); // NOI18N

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pjwiki/resources/icons16/onebit_50-0.png"))); // NOI18N
        homeButton.setFocusable(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setName("homeButton"); // NOI18N
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tableOfContentsToolbar.add(homeButton);

        tableOfContentsJPanel.add(tableOfContentsToolbar, java.awt.BorderLayout.PAGE_START);

        wikiViewSplitPane.setLeftComponent(tableOfContentsJPanel);

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(wikiViewSplitPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(wikiViewSplitPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 482, Short.MAX_VALUE)
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
   //     if(editState == state.VIEW)
   //     { 
   //         enterState(state.EDIT);
   //     }else{
  //          enterState(state.VIEW);
   //     }
    }//GEN-LAST:event_editButtonActionPerformed

    private void contentToolbarComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_contentToolbarComponentRemoved

    }//GEN-LAST:event_contentToolbarComponentRemoved

    private void contentTextPaneHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_contentTextPaneHyperlinkUpdate
        if(evt.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {

            URL url = evt.getURL();
            if(url != null && !url.sameFile(contentTextPane.getPage()) && externalProtocols.contains(url.getProtocol())) {
                String osName = System.getProperty("os.name");
                if (osName.startsWith("Windows"))
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                        // JAVA 6: java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                    } catch (IOException ex) {
                        Logger.getLogger(PjWikiView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                // JAVA 6: java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            } else {
                if(evt.getDescription().charAt(0) == '#') {
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
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private javax.swing.JTextPane contentTextPane;
    private javax.swing.JToolBar contentToolbar;
    private javax.swing.JPanel contentToolbarPane;
    private javax.swing.JButton editButton;
    private javax.swing.JToolBar.Separator editSearchSeparator;
    private javax.swing.JPanel editToolbar;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton navBackButton;
    private javax.swing.JToolBar.Separator navEditSeparator;
    private javax.swing.JButton navForwardButton;
    private javax.swing.JButton navGoButton;
    private javax.swing.JTextField navLocationTextField1;
    private javax.swing.JPanel navigationToolbar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JPanel tableOfContentsJPanel;
    private javax.swing.JScrollPane tableOfContentsScrollPane;
    private javax.swing.JToolBar tableOfContentsToolbar;
    private javax.swing.JTextPane tocTextPane;
    private javax.swing.JSplitPane wikiViewSplitPane;
    // End of variables declaration//GEN-END:variables

    private void displayException(Exception e)
    {
        displayMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
    }
    private void displayMessage(String s, int JOptionPaneType)
    {
        JOptionPane.showMessageDialog(this.getFrame(),
            s,
            (JOptionPane.ERROR_MESSAGE == JOptionPaneType? "Error" : "Warning"),
            JOptionPaneType);
    }

    private int displayYesNoCancel(String title, String text)
    {
        String[] options = {"Yes", "No", "Cancel"};
        int n = JOptionPane.showOptionDialog(
                this.getFrame(),
                text,
                title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[2]);
        return n;
    }
    
    public void loadContent() throws Exception
    {
        currentText = currentWikiWordPage.load();
    }
    public void showViewing()
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
        contentTextPane.revalidate();
    }
    public void showEditing()
    {
        try{
            contentTextPane.setEditorKit(new StyledEditorKit());
            contentTextPane.setEditable(true);
            contentTextPane.setText(currentText);
        }catch(Exception e){
            JOptionPane.showMessageDialog(PjWikiApp.getApplication().getMainFrame(),e.toString());
        }
        contentTextPane.revalidate();
    }
    public void showPreviewing()
    {
        try{
            WikiSyntaxParserFormatting w = new WikiSyntaxParserFormatting();
            WikiSyntaxParserHeaders w2 = new WikiSyntaxParserHeaders();
            String text = WikiHtmlFormatter.format(wikiSyntaxManager.format(contentTextPane.getText()));
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
        contentTextPane.revalidate();    
    }

    public boolean displayCreateNewWordDialog(WikiWordPageBase word)
    {
        return 0 == displayYesNoCancel(
                "Create "+word.getWord().name() + "?",
                word.getWord().name() + 
                    " does not exist. Would you like to create it?");
    }
    
    public void setCurrentWikiWordPage(WikiWordPageBase word)
    {
        try{
            if(!history.get(historyLocation).equals(word))
            {
                history = history.subList(0, historyLocation);
                history.add(word);
                historyLocation++;
            }
        } catch (Exception e)
        {
            history.add(word);
        }
        currentWikiWordPage = word;
    }
    boolean setCurrentWikiWordPageFromHistory(int i) {
        try
        {
            currentWikiWordPage = history.get(historyLocation + i);
            historyLocation += i;
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;

}
