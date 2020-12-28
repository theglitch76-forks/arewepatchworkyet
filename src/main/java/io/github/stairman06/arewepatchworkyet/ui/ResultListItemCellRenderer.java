package io.github.stairman06.arewepatchworkyet.ui;

import io.github.stairman06.arewepatchworkyet.AreWePatchworkYetGui;
import io.github.stairman06.arewepatchworkyet.MappingUtils;
import io.github.stairman06.arewepatchworkyet.analyze.Method;

import javax.swing.*;
import java.awt.*;

public class ResultListItemCellRenderer extends JButton implements ListCellRenderer<ResultListItem> {

    // Make the button look like a JLabel https://stackoverflow.com/a/3026065
    @Override
    public boolean isFocusPainted() {
        return false;
    }

    @Override
    public boolean isBorderPainted() {
        return false;
    }

    @Override
    public Insets getMargin() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public int getHorizontalAlignment() {
        return SwingConstants.LEFT;
    }

    @Override
    public boolean isContentAreaFilled() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return true;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ResultListItem> list, ResultListItem item, int index, boolean isSelected, boolean cellHasFocus) {
        String mappings = AreWePatchworkYetGui.getCurrentMappings();

        if(isSelected) {
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }

        ResultListItem.Type type = item.getType();
        if(type == ResultListItem.Type.CLASS) {
            String className = (String) item.getObject();
            if(AreWePatchworkYetGui.getCurrentMappings().equals("yarn")) {
                className = MappingUtils.getYarnClassName(className);
            }
            setText(className);
        } else if (type == ResultListItem.Type.METHOD) {
            Method method = (Method) item.getObject();
            String descriptorName = method.descriptor;
            if(AreWePatchworkYetGui.getCurrentMappings().equals("yarn")) {
                descriptorName = MappingUtils.remapDescriptorToYarn(descriptorName);
            }
            setText("        " + method.name + descriptorName);
        }

        return this;
    }

}