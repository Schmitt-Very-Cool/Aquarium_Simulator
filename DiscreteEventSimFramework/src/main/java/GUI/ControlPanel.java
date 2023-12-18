package GUI;

import FishModels.FishTank;
import FishModels.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    JButton fishStoreButton = new JButton("Fish Store");
    JButton decorStoreButton = new JButton("Decor Store");
    JLabel dollarSign = new JLabel("$$$:");
    JLabel money = new JLabel("0");
    JLabel dirtiness = new JLabel("dirtiness:");
    JLabel hunger = new JLabel("Hunger:");
    public JProgressBar hungryBar = new JProgressBar();
    public JProgressBar dirtyBar = new JProgressBar();
    JButton cleanButton = new JButton("Clean");
    JButton feedButton = new JButton("Feed");
    JPanel moneyPanel = new JPanel();
    JPanel dirtyPanel = new JPanel();
    JPanel hungryPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    public ControlPanel(){
        setPreferredSize(new Dimension(1000,150));
        LineBorder b = new LineBorder(Color.BLACK,2);
        setBorder(b);
        buttonPanel.setBorder(b);
        moneyPanel.setBorder(b);
        hungryPanel.setBorder(b);
        dirtyPanel.setBorder(b);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4,4,4,4);

        dollarSign.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        money.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        moneyPanel.setLayout(new GridBagLayout());
        moneyPanel.add(dollarSign,gbc);
        gbc.weightx = 1;
        moneyPanel.add(money,gbc);

        dirtyBar.setPreferredSize(new Dimension(200,15));
        hungryBar.setPreferredSize(new Dimension(200,15));
        hungryBar.setMinimum(0);
        hungryBar.setMaximum(10);
        hungryBar.setForeground(Color.RED);
        dirtyBar.setMinimum(0);
        dirtyBar.setMaximum(10);
        dirtyBar.setForeground(Color.GREEN);


        dirtyPanel.setLayout(new GridBagLayout());
        hungryPanel.setLayout(new GridBagLayout());
        gbc.gridy++;
        dirtyPanel.add(dirtiness,gbc);
        hungryPanel.add(hunger,gbc);
        gbc.weightx = 1;
        gbc.gridy++;
        dirtyPanel.add(dirtyBar,gbc);
        hungryPanel.add(hungryBar,gbc);
        gbc.gridy++;
        dirtyPanel.add(cleanButton,gbc);
        hungryPanel.add(feedButton,gbc);

        buttonPanel.setLayout(new GridBagLayout());
        gbc.weighty = 1;
        buttonPanel.add(fishStoreButton,gbc);
        gbc.gridy++;
        buttonPanel.add(decorStoreButton,gbc);

        gbc.insets = new Insets(0,0,0,0);
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight=2;
        add(buttonPanel,gbc);
        gbc.gridheight=1;
        gbc.gridy = 0;
        gbc.gridx++;
        gbc.gridwidth = 2;
        add(moneyPanel, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(hungryPanel,gbc);
        gbc.gridx++;
        add(dirtyPanel,gbc);

        fishStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gui.swapToFishStore();
            }
        });

        decorStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.gui.swapToDecorStore();
            }
        });

        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FishTank.feed();
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FishTank.clean();
            }
        });
    }
}
