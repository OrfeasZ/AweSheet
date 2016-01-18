/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.awesheet.handlers;

import com.awesheet.ui.actions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuHandler {
    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu viewMenu;
    private JMenu chartMenu;
    private JMenu helpMenu;

    private JMenuItem fileNewItem;
    private JMenuItem fileOpenItem;
    private JMenuItem fileSaveItem;
    private JMenuItem fileSaveAsItem;
    private JMenuItem fileImportItem;
    private JMenuItem fileExportItem;
    private JMenuItem fileExitItem;
    private JMenuItem editCopyItem;
    private JMenuItem editCutItem;
    private JMenuItem editPasteItem;
    private JMenuItem viewMaximizeItem;
    private JMenuItem viewMinimizeItem;
    private JMenuItem chartBarItem;
    private JMenuItem chartLineItem;
    private JMenuItem helpDocumentationItem;
    private JMenuItem helpOnlineDocumentationItem;
    private JMenuItem helpAboutItem;

    public MenuHandler() {
        initMenus();
    }

    private void initMenus() {
        // Init the menu bar.
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(600, 30));

        // Init the menus.
        initFileMenu();
        initEditMenu();
        initViewMenu();
        initChartMenu();
        initHelpMenu();

        // Add menus to menu bar.
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(chartMenu);
        menuBar.add(helpMenu);
    }

    private void initFileMenu() {
        fileMenu = new JMenu("   File   ");

        fileNewItem = new JMenuItem(new NewWorkbookAction());
        fileNewItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        fileNewItem.setPreferredSize(new Dimension(200, 26));

        fileOpenItem = new JMenuItem(new OpenWorkbookAction());
        fileOpenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileOpenItem.setPreferredSize(new Dimension(200, 26));

        fileSaveItem = new JMenuItem(new SaveWorkbookAction());
        fileSaveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        fileSaveItem.setEnabled(false);
        fileSaveItem.setPreferredSize(new Dimension(200, 26));

        fileSaveAsItem = new JMenuItem(new SaveWorkbookAsAction());
        fileSaveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        fileSaveAsItem.setEnabled(false);
        fileSaveAsItem.setPreferredSize(new Dimension(200, 26));

        fileImportItem = new JMenuItem(new ImportCSVAction());
        fileImportItem.setPreferredSize(new Dimension(200, 26));
        fileImportItem.setEnabled(false);

        fileExportItem = new JMenuItem(new ExportCSVAction());
        fileExportItem.setPreferredSize(new Dimension(200, 26));
        fileExportItem.setEnabled(false);

        fileExitItem = new JMenuItem(new ExitApplicationAction());
        fileExitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        fileExitItem.setPreferredSize(new Dimension(200, 26));

        fileMenu.add(fileNewItem);
        fileMenu.add(fileOpenItem);
        fileMenu.add(fileSaveItem);
        fileMenu.add(fileSaveAsItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileImportItem);
        fileMenu.add(fileExportItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileExitItem);
    }

    private void initEditMenu() {
        editMenu = new JMenu("   Edit   ");

        editCopyItem = new JMenuItem(new CopyCellAction());
        editCopyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        editCopyItem.setPreferredSize(new Dimension(200, 26));
        editCopyItem.setEnabled(false);

        editCutItem = new JMenuItem(new CutCellAction());
        editCutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        editCutItem.setPreferredSize(new Dimension(200, 26));
        editCutItem.setEnabled(false);

        editPasteItem = new JMenuItem(new PasteCellAction());
        editPasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        editPasteItem.setPreferredSize(new Dimension(200, 26));
        editPasteItem.setEnabled(false);

        editMenu.add(editCopyItem);
        editMenu.add(editCutItem);
        editMenu.add(editPasteItem);
    }

    private void initViewMenu() {
        viewMenu = new JMenu("   View   ");

        viewMaximizeItem = new JMenuItem(new MaximizeWindowAction());
        viewMaximizeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
        viewMaximizeItem.setPreferredSize(new Dimension(200, 26));

        viewMinimizeItem = new JMenuItem(new MinimizeWindowAction());
        viewMinimizeItem.setPreferredSize(new Dimension(200, 26));

        viewMenu.add(viewMaximizeItem);
        viewMenu.add(viewMinimizeItem);
    }

    private void initChartMenu() {
        chartMenu = new JMenu("   Chart   ");

        chartBarItem = new JMenuItem(new CreateBarChartAction());
        chartBarItem.setPreferredSize(new Dimension(200, 26));
        chartBarItem.setEnabled(false);

        chartLineItem = new JMenuItem(new CreateLineChartAction());
        chartLineItem.setPreferredSize(new Dimension(200, 26));
        chartLineItem.setEnabled(false);

        chartMenu.add(chartBarItem);
        chartMenu.add(chartLineItem);
    }

    private void initHelpMenu() {
        helpMenu = new JMenu("   Help   ");

        helpDocumentationItem = new JMenuItem(new ViewDocumentationAction());
        helpDocumentationItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
        helpDocumentationItem.setPreferredSize(new Dimension(200, 26));

        helpOnlineDocumentationItem = new JMenuItem(new ViewOnlineDocumentationAction());
        helpOnlineDocumentationItem.setPreferredSize(new Dimension(200, 26));

        helpAboutItem = new JMenuItem(new ViewAboutAction());
        helpAboutItem.setPreferredSize(new Dimension(200, 26));

        helpMenu.add(helpDocumentationItem);
        helpMenu.add(helpOnlineDocumentationItem);
        helpMenu.add(new JSeparator());
        helpMenu.add(helpAboutItem);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public JMenu getEditMenu() {
        return editMenu;
    }

    public JMenu getViewMenu() {
        return viewMenu;
    }

    public JMenu getChartMenu() {
        return chartMenu;
    }

    public JMenu getHelpMenu() {
        return helpMenu;
    }

    public JMenuItem getFileNewItem() {
        return fileNewItem;
    }

    public JMenuItem getFileOpenItem() {
        return fileOpenItem;
    }

    public JMenuItem getFileSaveItem() {
        return fileSaveItem;
    }

    public JMenuItem getFileSaveAsItem() {
        return fileSaveAsItem;
    }

    public JMenuItem getFileImportItem() {
        return fileImportItem;
    }

    public JMenuItem getFileExportItem() {
        return fileExportItem;
    }

    public JMenuItem getFileExitItem() {
        return fileExitItem;
    }

    public JMenuItem getEditCopyItem() {
        return editCopyItem;
    }

    public JMenuItem getEditCutItem() {
        return editCutItem;
    }

    public JMenuItem getEditPasteItem() {
        return editPasteItem;
    }

    public JMenuItem getViewMaximizeItem() {
        return viewMaximizeItem;
    }

    public JMenuItem getViewMinimizeItem() {
        return viewMinimizeItem;
    }

    public JMenuItem getChartBarItem() {
        return chartBarItem;
    }

    public JMenuItem getChartLineItem() {
        return chartLineItem;
    }

    public JMenuItem getHelpDocumentationItem() {
        return helpDocumentationItem;
    }

    public JMenuItem getHelpOnlineDocumentationItem() {
        return helpOnlineDocumentationItem;
    }

    public JMenuItem getHelpAboutItem() {
        return helpAboutItem;
    }
}
