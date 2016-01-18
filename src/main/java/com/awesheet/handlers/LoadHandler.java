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
import org.cef.browser.CefBrowser;
import org.cef.handler.CefLoadHandler;

public class LoadHandler implements CefLoadHandler {
    @Override
    public void onLoadingStateChange(CefBrowser cefBrowser, boolean isLoading, boolean canGoBack, boolean canGoForward) {

    }

    @Override
    public void onLoadStart(CefBrowser cefBrowser, int frame) {

    }

    @Override
    public void onLoadEnd(CefBrowser cefBrowser, int frame, int httpStatusCode) {
        // If we failed to load then request exit.
        // If managers are not initialized the application will exit.
        if (httpStatusCode != 200) {
            MainFrame.getInstance().requestExit();
            return;
        }

        // Init our managers once the page has finished loading.
        MainFrame.getInstance().initManagers();
    }

    @Override
    public void onLoadError(CefBrowser cefBrowser, int frame, ErrorCode errorCode, String errorText, String failedUrl) {

    }
}
