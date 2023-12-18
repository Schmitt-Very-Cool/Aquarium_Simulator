package GUI;

import FishModels.Fish;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StatsPanel extends JPanel {
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JLabel name = new JLabel("???");
    JLabel type = new JLabel("Type:");
    JLabel typeName = new JLabel("???");
    JLabel schooling = new JLabel("Schooling:");
    JLabel schoolingName = new JLabel("???");
    JLabel favDecor = new JLabel("Favorite Decor:");
    JLabel favDecorName = new JLabel("???");
    JLabel happiness = new JLabel("Happiness:");
    JProgressBar happyBar = new JProgressBar();
    JPanel namePanel = new JPanel();
    Fish selectedFish;
    public StatsPanel(){
        setPreferredSize(new Dimension(250,450));
        setBorder(new LineBorder(Color.BLACK, 2));

        happyBar.setMinimum(0);
        happyBar.setMaximum(10);
        happyBar.setForeground(Color.YELLOW);
        happyBar.setPreferredSize(new Dimension(125,15));

        namePanel.add(name);
        name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        namePanel.setBorder(new LineBorder(Color.BLACK, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        leftPanel.setLayout(new GridBagLayout());
        rightPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,10,10);
        gbc.weighty = 1;
        gbc.gridy = 0;
        leftPanel.add(type,gbc);
        rightPanel.add(typeName,gbc);
        gbc.gridy++;
        leftPanel.add(schooling,gbc);
        rightPanel.add(schoolingName,gbc);
        gbc.gridy++;
        leftPanel.add(favDecor,gbc);
        rightPanel.add(favDecorName,gbc);
        gbc.gridy++;
        leftPanel.add(happiness,gbc);
        rightPanel.add(happyBar,gbc);


        setLayout(new BorderLayout());
        add(namePanel,"North");
        add(leftPanel,"West");
        add(rightPanel,"East");
    }

    void setDetails(Fish f){
        selectedFish = f;
        name.setText(f.name);
        typeName.setText(f.typeName);
        schoolingName.setText(String.valueOf(f.schooling));
        favDecorName.setText(f.favDecor);
        happyBar.setValue(f.happiness);
    }
}
