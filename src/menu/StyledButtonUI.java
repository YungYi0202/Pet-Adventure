package menu;

import java.awt.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.*;
import javax.swing.border.*;



class StyledButtonUI extends BasicButtonUI {
    Color btnColor;
    public StyledButtonUI(int r, int g, int b){
        btnColor = new Color(r, g, b);
    }
    @Override
    public void installUI (JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setBorder(new EmptyBorder(5, 15, 5, 15));
    }

    @Override
    public void paint (Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        paintBackground(g, b, b.getModel().isPressed() ? 5 : 0);
        super.paint(g, c);
    }

    private void paintBackground (Graphics g, JComponent c, int yOffset) {
        Dimension size = c.getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(btnColor.darker());
        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
        g.setColor(btnColor);
        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 10, 10, 10);
    }
}