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
package org.loadui.testfx;

import javafx.geometry.VerticalDirection;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;
import org.loadui.testfx.controls.impl.VisibleNodesMatcher;

import static org.hamcrest.Matchers.not;
import static org.loadui.testfx.Assertions.verifyThat;

@Ignore
public class ScrollPaneTest extends GuiTest {
    public static final int IMAGE_WIDTH = 525;

    public static final int IMAGE_HEIGHT = 448;

    public static final String IMAGE_RESOURCE = "java-duke-guitar.png";

    private static final String ID1 = "ID1";

    private static final String ID2 = "ID2";

    @Override
    protected Parent getRootNode() {
        ScrollPane sp = new ScrollPane();
        VBox vb = new VBox();
        sp.setContent(vb);
        sp.setVmax(IMAGE_HEIGHT * 2);
        ImageView img1 = new ImageView(new Image(getClass()
                .getResourceAsStream(IMAGE_RESOURCE)));
        img1.setId(ID1);
        ImageView img2 = new ImageView(new Image(getClass()
                .getResourceAsStream(IMAGE_RESOURCE)));
        img2.setId(ID2);
        vb.getChildren().add(img1);
        vb.getChildren().add(img2);
        sp.setPrefWidth(IMAGE_WIDTH);
        sp.setPrefHeight(IMAGE_HEIGHT - 50);
        return sp;
    }

    @Test
    public void testScroll() {
        String id1 = "#" + ID1;
        String id2 = "#" + ID2;

        verifyThat(id1, isVisible());
        verifyThat(id2, not(isVisible()));

        moveTo(id1);
        scroll(50, VerticalDirection.DOWN);
        
        verifyThat(id2, isVisible());
        verifyThat(id1, not(isVisible()));
    }

    /**
     * Maybe should be added to Commons.java
     * 
     * @param text
     * @return
     */
    @Factory
    public static Matcher<Object> isVisible() {
        return VisibleNodesMatcher.visible();
    }

}
