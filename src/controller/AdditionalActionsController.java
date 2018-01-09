package controller;

import java.io.Serializable;

import app.MainFrame;
import model.ShapeModel;
import util.ZAxisHelper;
import zaxis.BringToBackCommand;
import zaxis.BringToFrontCommand;
import zaxis.ToBackCommand;
import zaxis.ToFrontCommand;

@SuppressWarnings("serial")
public class AdditionalActionsController implements Serializable {
	private ShapeModel model;
	private MainFrame frame;

	public AdditionalActionsController(ShapeModel model, MainFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void doBringToFront() {
		if(!frame.getAdditionalActionsView().getBtnBringToFront().isEnabled()) return;
		
		int selectedIndex = ZAxisHelper.getSelectedShapeIndex(model);
		BringToFrontCommand btfCommand = new BringToFrontCommand(selectedIndex, model, frame);
		btfCommand.execute();
		ShapeModel.getUndoStack().offerLast(btfCommand);
	}

	public void doBringToBack() {
		if(!frame.getAdditionalActionsView().getBtnBringToBack().isEnabled()) return;
		
		int selectedIndex = ZAxisHelper.getSelectedShapeIndex(model);
		BringToBackCommand btbCommand = new BringToBackCommand(selectedIndex, model, frame);
		btbCommand.execute();
		ShapeModel.getUndoStack().offerLast(btbCommand);
	}

	public void doToFront() {
		if(!frame.getAdditionalActionsView().getBtnToFront().isEnabled()) return;
		
		int selectedIndex = ZAxisHelper.getSelectedShapeIndex(model);
		ToFrontCommand tfCommand = new ToFrontCommand(selectedIndex, model, frame);
		tfCommand.execute();
		ShapeModel.getUndoStack().offerLast(tfCommand);
	}

	public void doToBack() {
		if(!frame.getAdditionalActionsView().getBtnToBack().isEnabled()) return;
		
		int selectedIndex = ZAxisHelper.getSelectedShapeIndex(model);
		ToBackCommand tbCommand = new ToBackCommand(selectedIndex, model, frame);
		tbCommand.execute();
		ShapeModel.getUndoStack().offerLast(tbCommand);
	}

}
