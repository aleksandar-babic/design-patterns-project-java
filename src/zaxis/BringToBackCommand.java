package zaxis;

import app.MainFrame;
import model.ShapeModel;
import shapes.Command;
import util.Logger;
import util.ZAxisHelper;

public class BringToBackCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1523731526797266070L;
	private int selectedIndex = -1;
	private int newIndex = -1;
	private ShapeModel model;
	private MainFrame frame;

	public BringToBackCommand(int selectedIndex, ShapeModel model, MainFrame frame) {
		this.selectedIndex = selectedIndex;
		this.model = model;
		this.frame = frame;
	}

	@Override
	public void execute() {
		newIndex = ZAxisHelper.doBringToBack(selectedIndex, model, frame);

		Logger.getInstance().log(getClass().getSimpleName() + "_execute", model.get(selectedIndex).toString(), true);
	}

	@Override
	public void unexecute() {
		// TODO Fix this
		// ZAxisHelper.moveShapeToIndex(selectedIndex,
		// model.getShapesList().get(newIndex), model);

		Logger.getInstance().log(getClass().getSimpleName() + "_unexecute", model.get(selectedIndex).toString(), true);
	}

}
