package drawing;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

/**
 * Classe Interface graphique pour l'application de dessin
 */
public class Paint implements Observer{

	private JFrame frame;
	private JButton clearButton;
	private JButton circleButton;
	private JButton rectangleButton;
	private JButton Regroup;
	//private JButton nbr;
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private Drawing drawing;
	//private JTextField counterFld;
	private JLabel Label1;
	private JLabel Label2;
	private JLabel LabelCount;
	private JPanel BarreStatut;
	private JButton DuplicateButton;
	public Paint(Controleur controller) {
		clearButton = new JButton("Clear");
		circleButton = new JButton("Circle");
		rectangleButton = new JButton("Rectangle");
		Regroup = new JButton("Regroup");
		Label1 = new JLabel();
		LabelCount = new JLabel();
		Label2= new JLabel();
		DuplicateButton = new JButton("Dupliquer");
		// listeners pour les boutons compteur
		circleButton.addActionListener(controller);
		rectangleButton.addActionListener(controller);
		clearButton.addActionListener(controller);
		Regroup.addActionListener(controller);
		DuplicateButton.addActionListener(controller);

	}
	// methode run stats des elements du paint
	public void run() {
		frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.BLACK);
		BarreStatut = new JPanel();
		BarreStatut.setBorder(new BevelBorder(BevelBorder.RAISED));
		frame.add(BarreStatut, BorderLayout.SOUTH);
		BarreStatut.setPreferredSize(new Dimension(frame.getWidth(), 50));
		BarreStatut.setLayout(new BoxLayout(BarreStatut, BoxLayout.X_AXIS));
		Label1 = new JLabel("aucune ");
		Label1.setHorizontalAlignment(SwingConstants.LEFT);
		LabelCount = new JLabel(" forme dessin�e ");
		Label2 = new JLabel("0");
		Label2.setHorizontalAlignment(SwingConstants.RIGHT);
		drawing = new Drawing();
		drawing.setBackground(Color.WHITE);
		buttonPanel = new JPanel();
		buttonPanel.add(clearButton);
		buttonPanel.add(circleButton);
		buttonPanel.add(rectangleButton);
		buttonPanel.add(Regroup);
		buttonPanel.add(DuplicateButton);
		BarreStatut.add(Label1);
		BarreStatut.add(LabelCount);
		BarreStatut.add(Label2);

		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(drawing, BorderLayout.CENTER);


		clearButton.addActionListener(new ClearButtonListener(drawing));
		circleButton.addActionListener(new CircleButtonListener(drawing));
		rectangleButton.addActionListener(new RectangleButtonListener(drawing));
		DuplicateButton.addActionListener(new DuplicateButtonListner(drawing));

		DrawingMouseListener l = new DrawingMouseListener(drawing);
		drawing.addMouseListener(l);
		drawing.addMouseMotionListener(l);

		frame.getContentPane().add(mainPanel);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
	public static Drawing Former = new Drawing();
	public static Controleur Controler = new Controleur(Former);

	public static void main(String[] args){
		Paint lancer = new Paint(Controler);
		Former.addObserver(lancer);
		lancer.run();
	}
	//modifier la valeur du label on lui affectant la valeur du compteur
	public void update(int value) {
		Label1.setText(Integer.toString(value));
	}
	public void updateSelectShape(int value){
		Label2.setText(Integer.toString(value));

	}

}
