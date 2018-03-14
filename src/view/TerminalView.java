package view;

import java.awt.*;

import javax.swing.*;

import view.notesviews.NotesPanel;

public class TerminalView extends JFrame {

  private MainPanel mp;
  private TwitterPanel tp;
  private ForexPanel fp;
  private BiblePanel bp;
  private PortfolioPanel pp;
  private NotesPanel np;
  private JTabbedPane tabbedPane;

  public TerminalView() {
    super();
    setSize(500, 500);
    setPreferredSize(new Dimension(500, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setBackground(Color.BLACK);

    tabbedPane = new JTabbedPane();


    mp = new MainPanel();
    tp = new TwitterPanel();
    //fp = new ForexPanel();
    bp = new BiblePanel();
    pp = new PortfolioPanel();
    np = new NotesPanel();
    tabbedPane.add("main", mp);
    tabbedPane.add("twitter", tp);
    //tabbedPane.add("forex", fp);
    tabbedPane.add("bible", bp);
    tabbedPane.add("portfolio", pp);
    tabbedPane.add("notes", np);
    add(tabbedPane);
    getRootPane().setDefaultButton(mp.getEnterButton());
    //getRootPane().setDefaultButton(tp.getSearchButton());
    pack();
    setVisible(true);
  }

}
