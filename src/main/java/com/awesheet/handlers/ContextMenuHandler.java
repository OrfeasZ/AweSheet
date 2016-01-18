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

import org.cef.browser.CefBrowser;
import org.cef.callback.CefContextMenuParams;
import org.cef.callback.CefMenuModel;
import org.cef.handler.CefContextMenuHandler;

public class ContextMenuHandler implements CefContextMenuHandler {
    @Override
    public void onBeforeContextMenu(CefBrowser cefBrowser, CefContextMenuParams cefContextMenuParams, CefMenuModel cefMenuModel) {
        cefMenuModel.clear();
    }

    @Override
    public boolean onContextMenuCommand(CefBrowser cefBrowser, CefContextMenuParams cefContextMenuParams, int i, int i1) {
        return false;
    }

    @Override
    public void onContextMenuDismissed(CefBrowser cefBrowser) {

    }
}
