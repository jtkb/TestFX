/*
 * Copyright 2013-2014 SmartBear Software
 * Copyright 2014-2017 The TestFX Contributors
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
 * European Commission - subsequent versions of the EUPL (the "Licence"); You may
 * not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */
package org.loadui.testfx.controls;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextInputControl;

import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesFoundException;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.BACK_SPACE;
import static javafx.scene.input.KeyCode.SHORTCUT;
import static org.loadui.testfx.GuiTest.find;

/**
 * Provides methods for interacting with TextInputControls.
 */
public class TextInputControls {

    /**
     * Clears all text in the given TextInputControl.
     *
     * @param textInputControl the textInputControl to clear
     */
    public static void clearTextIn(TextInputControl textInputControl) {
        textInputControl.clear();
    }

    /**
     * Clears all text in the TextInputControl defined by the given query. The
     * query is resolved by {@link org.loadui.testfx.GuiTest#find(String)}. If
     * the control is already empty, nothing will be done.
     *
     * @param textInputQuery the query to lookup the TextInputControl.
     */
    public static void clearTextIn(String textInputQuery) {
        Node node = find(textInputQuery);
        if (!(node instanceof TextInputControl)) {
            throw new NoNodesFoundException(textInputQuery + " selected " + node +
                " which is not a TextInputControl!");
        }

        TextInputControl textControl = (TextInputControl) node;
        if (textControl.getLength() == 0) {
            return;
        }

        GuiTest fx = new GuiTest() {
            @Override
            protected Parent getRootNode() {
                return null;
            }
        };

        fx.clickOn(textControl);
        fx.push(SHORTCUT, A).push(BACK_SPACE);
    }

}
