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

import com.awesheet.MainFrame;
import org.cef.CefApp;
import org.cef.CefApp.CefAppState;
import org.cef.callback.CefSchemeRegistrar;
import org.cef.handler.CefAppHandlerAdapter;

public class AppHandler extends CefAppHandlerAdapter {
    public AppHandler(String args[]) {
        super(args);
    }

    @Override
    public void onRegisterCustomSchemes(CefSchemeRegistrar registrar) {
        if (!registrar.addCustomScheme(MainSchemeHandler.scheme, true, false, false)) {
            MainFrame.getInstance().requestExit();
        }
    }

    @Override
    public void onContextInitialized() {
        CefApp cefApp = CefApp.getInstance();
        cefApp.registerSchemeHandlerFactory(MainSchemeHandler.scheme, MainSchemeHandler.domain, new SchemeHandlerFactory());
    }

    @Override
    public void stateHasChanged(CefAppState state) {
        // Shutdown the app if the native CEF part is terminated
        if (state == CefAppState.TERMINATED) {
            System.exit(0);
        }
    }
}
