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
package org.testfx.toolkit;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.testfx.api.annotation.Unstable;

/**
 * The main application used during tests when a developer is not testing his/her own subclass of {@link Application}.
 * The {@code primaryStage} from {@link Application#start(Stage)} can be accessed via {@link #PRIMARY_STAGE_FUTURE}.
 */
@Unstable(reason = "needs more tests")
public class PrimaryStageApplication extends Application {

    //---------------------------------------------------------------------------------------------
    // STATIC FIELDS.
    //---------------------------------------------------------------------------------------------

    public static final PrimaryStageFuture PRIMARY_STAGE_FUTURE = PrimaryStageFuture.create();

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle(getClass().getSimpleName());
        PRIMARY_STAGE_FUTURE.set(primaryStage);
    }

}
