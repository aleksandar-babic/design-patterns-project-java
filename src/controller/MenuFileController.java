package controller;

import java.util.ArrayList;
import java.util.Deque;

import app.MainFrame;
import io.exporter.ExportManager;
import io.exporter.SaveLogToFile;
import io.exporter.SerializeShapesToFile;
import io.importer.ImportManager;
import io.importer.LoadDrawingFromFile;
import io.importer.LoadLogFromFile;
import model.LoggerModel;
import model.ShapeModel;
import shapes.Command;
import shapes.Shape;
import util.FileOperationsHelper;
import util.Logger;

public class MenuFileController {
	private MainFrame frame;
	private ShapeModel model;
	private LoggerModel loggerModel;

	public MenuFileController(MainFrame frame, ShapeModel model, LoggerModel loggerModel) {
		this.frame = frame;
		this.model = model;
		this.loggerModel = loggerModel;
	}

	/**
	 * Bundles all 3 shape collections to Object ArrayList, opens File Dialog and
	 * calls export to file method
	 */
	public void handleExportToFile() {
		ArrayList<Object> bundle = new ArrayList<Object>();
		bundle.add(model.getShapesList());
		bundle.add(ShapeModel.getUndoStack());
		bundle.add(ShapeModel.getRedoStack());
		ExportManager manager = new ExportManager(new SerializeShapesToFile());
		manager.exportData(bundle, FileOperationsHelper.showFileDialog("drwg"));
	}

	/**
	 * Adds log lines list to bundle, opens FIle Dialog and calls save log to file
	 * method
	 */
	public void handleExportToLog() {
		ArrayList<Object> bundle = new ArrayList<Object>();
		bundle.add(loggerModel.getLogLines());
		ExportManager manager = new ExportManager(new SaveLogToFile());
		manager.exportData(bundle, FileOperationsHelper.showFileDialog("log"));
	}

	/**
	 * Imports all log lines from log file
	 */
	@SuppressWarnings("unchecked")
	public void handleImportFromLog() {
		ImportManager manager = new ImportManager(new LoadLogFromFile());
		ArrayList<Object> bundle = manager.importData(FileOperationsHelper.showFileDialog("log"));
		for (String s : (ArrayList<String>) bundle.get(0)) {
			// TODO Implement this function fully
			Logger.getInstance().log("IMPORT", s, false);
		}
	}
	
	/**
	 * Imports whole drawing from serialized file with .drwg extension
	 */
	@SuppressWarnings("unchecked")
	public void handleImportFromFile() {
		ImportManager manager = new ImportManager(new LoadDrawingFromFile());
		ArrayList<Object> bundle = manager.importData(FileOperationsHelper.showFileDialog("drwg"));
		model.setShapesList((ArrayList<Shape>) bundle.get(0));
		ShapeModel.setUndoStack((Deque<Command>) bundle.get(1));
		ShapeModel.setRedoStack((Deque<Command>) bundle.get(2));
	}

}
