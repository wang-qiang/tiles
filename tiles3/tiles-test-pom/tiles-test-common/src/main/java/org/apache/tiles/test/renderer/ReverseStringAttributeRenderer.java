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
 */
package org.apache.tiles.test.renderer;

import java.io.IOException;

import org.apache.tiles.Attribute;
import org.apache.tiles.renderer.impl.AbstractBaseAttributeRenderer;
import org.apache.tiles.request.Request;

/**
 * A simple test <code>AttributeRenderer</code>.
 *
 * @version $Rev$ $Date$
 */
public class ReverseStringAttributeRenderer extends AbstractBaseAttributeRenderer {

    /** {@inheritDoc} */
    @Override
    public void write(Object value, Attribute attribute,
            Request request)
            throws IOException {
        String original = attribute.getValue().toString();
        char[] array = original.toCharArray();
        char[] newArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[array.length - i - 1] = array[i];
        }
        request.getWriter().write(String.valueOf(newArray));
    }
}
