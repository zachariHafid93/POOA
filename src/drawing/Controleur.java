package drawing;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener {

	Drawing draw;
	Shape sh;

	public Controleur(Drawing draw) {
		this.draw = draw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Circle") {
			draw.addShape(sh);
		} else if (e.getActionCommand() == "Rectangle") {
			draw.addShape(sh);
		} else if (e.getActionCommand() == "Clear") {
			draw.clear();
		}
	}
}