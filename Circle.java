//author Darien

import java.awt.Graphics;

public class Circle extends Component {
    private Component component;

    public Circle(Component component) {
        this.component = component;
    }

    
    @Override public void draw (Graphics g, int x, int y) {
        component.draw(g, x, y);  // Draw the base shape

        g.setColor(Color.BLACK);
        g.drawArc(x + 10, y + 30, 30, 20, 0, -180);  // Draw a smile
    }


}

