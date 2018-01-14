package util;

import java.util.Deque;

import model.ShapeModel;
import shapes.Command;

public class UndoRedoHelper {
	private static Deque<Command> undoStack = ShapeModel.getUndoStack();
	private static Deque<Command> redoStack = ShapeModel.getRedoStack();

	/**
	 * Undo last action
	 */
	public static void undoAction() {
		if (!undoStack.isEmpty()) {
			Command previousAction = undoStack.pollLast();
			redoStack.offerLast(previousAction);
			previousAction.unexecute();
			
		} else {
			DialogsHelper.showErrorMessage("Undo stack is empty, nothing to undo.");
		}
	}

	/**
	 * Redo last action
	 */
	public static void redoAction() {
		if (!redoStack.isEmpty()) {
			Command previousAction = redoStack.pollLast();
			undoStack.offerLast(previousAction);
			previousAction.execute();
		} else {
			DialogsHelper.showErrorMessage("Redo stack is empty, nothing to redo.");
		}
	}
}
