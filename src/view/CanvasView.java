package view;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import lombok.Setter;
import model.ShapeModel;
import shapes.Shape;

@SuppressWarnings("serial")
public class CanvasView extends JPanel {
	private @Setter ShapeModel model;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getShapesList().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
		}
		repaint();
	}
}