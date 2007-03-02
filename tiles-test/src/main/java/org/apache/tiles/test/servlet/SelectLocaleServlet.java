/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.apache.tiles.test.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.TilesException;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.ComponentConstants;

/**
 * @version $Rev$ $Date$
 */
public class SelectLocaleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        process(request, response);
    }

    private void process(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String localeParameter = (String) request.getParameter("locale");
        HttpSession session = request.getSession();
        Locale locale = null;
        if (localeParameter != null && localeParameter.trim().length() > 0) {
            String[] localeStrings = localeParameter.split("_");
            if (localeStrings.length == 1) {
                locale = new Locale(localeStrings[0]);
            } else if (localeStrings.length == 2) {
                locale = new Locale(localeStrings[0], localeStrings[1]);
            } else if (localeStrings.length == 3) {
                locale = new Locale(localeStrings[0], localeStrings[1], localeStrings[2]);
            }
        }
        session.setAttribute(ComponentConstants.LOCALE_KEY, locale);
        TilesContainer container = TilesAccess.getContainer(request
                .getSession().getServletContext());
        try {
            container.render("test.localized.definition", request, response);
        } catch (TilesException e) {
            throw new ServletException("Cannot render 'test.localized.definition' definition", e);
        }
    }
}
