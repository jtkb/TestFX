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
package org.testfx.matcher.control;

import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.testfx.api.FxAssert;
import org.testfx.api.annotation.Unstable;
import org.testfx.service.finder.NodeFinder;
import org.testfx.service.query.NodeQuery;

import static org.testfx.matcher.base.GeneralMatchers.typeSafeMatcher;

/**
 * TestFX matchers for {@link ListView}
 */
@Unstable(reason = "needs more tests")
public class ListViewMatchers {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    private static final String SELECTOR_LIST_CELL = ".list-cell";

    //---------------------------------------------------------------------------------------------
    // STATIC METHODS.
    //---------------------------------------------------------------------------------------------

    /**
     * Creates a matcher that matches all {@link ListView}s that has one cell that equals the given {@code value}.
     */
    @Factory
    public static Matcher<Node> hasListCell(Object value) {
        String descriptionText = "has list cell \"" + value + "\"";
        return typeSafeMatcher(ListView.class, descriptionText, node -> hasListCell(node, value));
    }

    /**
     * Creates a matcher that matches all {@link ListView}s that has exactly {@code amount} items.
     */
    @Factory
    public static Matcher<Node> hasItems(int amount) {
        String descriptionText = "has " + amount + " items";
        return typeSafeMatcher(ListView.class, descriptionText, node -> hasItems(node, amount));
    }

    /**
     * Creates a matcher that matches all {@link ListView}s whose {@link ListView#getItems() list of items}
     * is empty.
     */
    @Factory
    public static Matcher<Node> isEmpty() {
        String descriptionText = "is empty (has no items)";
        return typeSafeMatcher(ListView.class, descriptionText, ListViewMatchers::isListEmpty);
    }

    /**
     * Creates a matcher that matches all {@link ListView}s in two situations: if both the
     * {@link ListView#getPlaceholder() ListView's placeholder} and the given {@code placeHolder} are
     * {@link Labeled} objects or subclasses thereof, it matches if the two placeholder's texts equal,
     * and it matches if the two placeholders, when they are not {@link Labeled} objects or subclasses,
     * are equal.
     */
    @Factory
    public static Matcher<Node> hasPlaceholder(Node placeHolder) {
        String descriptionText = "has ";
        // better description messages for Labeled nodes
        if (Labeled.class.isAssignableFrom(placeHolder.getClass())) {
            descriptionText += "labeled placeholder containing text: \"" +
                    ((Labeled) placeHolder).getText() + "\"";
        } else {
            descriptionText += "placeholder " + placeHolder;
        }
        return typeSafeMatcher(ListView.class, descriptionText, node -> hasPlaceholder(node, placeHolder));
    }

    /**
     * Creates a matcher that matches all {@link ListView}s whose {@link ListView#getPlaceholder() placeholder}
     * is visible in two situations: if both the {@link ListView#getPlaceholder() ListView's placeholder}
     * and the given {@code placeHolder} are {@link Labeled} objects or subclasses thereof, it matches if the
     * two placeholder's texts equal; otherwise, it matches if the two placeholders are equal.
     */
    @Factory
    public static Matcher<Node> hasVisiblePlaceholder(Node placeHolder) {
        String descriptionText = "has visible";
        // better description messages for Labeled nodes
        if (Labeled.class.isAssignableFrom(placeHolder.getClass())) {
            descriptionText += "labeled placeholder containing text: \"" +
                    ((Labeled) placeHolder).getText() + "\"";
        } else {
            descriptionText += "placeholder " + placeHolder;
        }
        return typeSafeMatcher(ListView.class, descriptionText, node -> hasVisiblePlaceholder(node, placeHolder));
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE STATIC METHODS.
    //---------------------------------------------------------------------------------------------

    private static boolean hasListCell(ListView listView,
                                       Object value) {
        NodeFinder nodeFinder = FxAssert.assertContext().getNodeFinder();
        NodeQuery nodeQuery = nodeFinder.from(listView);
        return nodeQuery.lookup(SELECTOR_LIST_CELL)
            .<Cell>match(cell -> hasCellValue(cell, value))
            .tryQuery().isPresent();
    }

    private static boolean hasItems(ListView listView,
                                    int amount) {
        return listView.getItems().size() == amount;
    }

    private static boolean hasCellValue(Cell cell,
                                        Object value) {
        return !cell.isEmpty() && Objects.equals(cell.getItem(), value);
    }

    private static boolean isListEmpty(ListView listView) {
        return listView.getItems().isEmpty();
    }

    private static boolean hasPlaceholder(ListView listView,
                                          Node placeHolder) {
        if (Labeled.class.isAssignableFrom(placeHolder.getClass()) &&
                Labeled.class.isAssignableFrom(listView.getPlaceholder().getClass())) {
            return ((Labeled) listView.getPlaceholder()).getText()
                    .equals(((Labeled) placeHolder).getText());
        } else {
            return Objects.equals(listView.getPlaceholder(), placeHolder);
        }
    }

    private static boolean hasVisiblePlaceholder(ListView listView,
                                                 Node placeHolder) {
        return listView.getPlaceholder().isVisible() &&
                hasPlaceholder(listView, placeHolder);
    }
}
