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

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testfx.TestFXRule;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import static javafx.collections.FXCollections.observableArrayList;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListViewMatchersTest extends FxRobot {

    @Rule
    public TestFXRule testFXRule = new TestFXRule();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    public ListView<String> listView;

    @BeforeClass
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Before
    public void setup() throws Exception {
        FxToolkit.setupSceneRoot(() -> {
            listView = new ListView<>();
            listView.setItems(observableArrayList("alice", "bob", "carol", "dave"));
            listView.setPlaceholder(new Label("Empty!"));
            return new StackPane(listView);
        });
        FxToolkit.showStage();
    }

    @Test
    public void hasListCell() {
        // expect:
        assertThat(listView, ListViewMatchers.hasListCell("alice"));
    }

    @Test
    public void hasListCell_with_null_fails() {
        // expect:
        exception.expect(AssertionError.class);
        exception.expectMessage("Expected: ListView has list cell \"null\"\n");

        assertThat(listView, ListViewMatchers.hasListCell(null));
    }

    @Test
    public void hasListCell_fails() {
        // expect:
        exception.expect(AssertionError.class);
        exception.expectMessage("Expected: ListView has list cell \"foobar\"\n");

        assertThat(listView, ListViewMatchers.hasListCell("foobar"));
    }

    @Test
    public void hasItems() {
        // expect:
        assertThat(listView, ListViewMatchers.hasItems(4));
    }

    @Test
    public void hasItems_fails() {
        // expect:
        exception.expect(AssertionError.class);
        exception.expectMessage("Expected: ListView has 0 items\n");

        assertThat(listView, ListViewMatchers.hasItems(0));
    }

    @Test
    public void hasPlaceholder() {
        // expect:
        assertThat(listView, ListViewMatchers.hasPlaceholder(new Label("Empty!")));
    }

    @Test
    public void hasPlaceholder_fails() {
        // expect:
        exception.expect(AssertionError.class);
        exception.expectMessage("Expected: ListView has labeled placeholder containing text: \"foobar\"\n");

        assertThat(listView, ListViewMatchers.hasPlaceholder(new Label("foobar")));
    }
}
