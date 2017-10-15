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
package org.testfx.api;

import org.testfx.api.annotation.Unstable;
import org.testfx.robot.BaseRobot;
import org.testfx.robot.impl.BaseRobotImpl;
import org.testfx.service.finder.NodeFinder;
import org.testfx.service.finder.WindowFinder;
import org.testfx.service.finder.impl.NodeFinderImpl;
import org.testfx.service.finder.impl.WindowFinderImpl;
import org.testfx.service.support.CaptureSupport;
import org.testfx.service.support.WaitUntilSupport;
import org.testfx.service.support.impl.CaptureSupportImpl;


/**
 * Stores the following objects:
 * <ul>
 *     <li>a {@link WindowFinder}</li>
 *     <li>a {@link NodeFinder}</li>
 *     <li>a {@link BaseRobot}</li>
 *     <li>{@link CaptureSupport}</li>
 *     <li>and {@link WaitUntilSupport}</li>
 * </ul>
 */
@Unstable(reason = "class was recently added")
public class FxServiceContext {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private WindowFinder windowFinder = new WindowFinderImpl();

    private NodeFinder nodeFinder = new NodeFinderImpl(windowFinder);

    private BaseRobot baseRobot = new BaseRobotImpl();

    private CaptureSupport captureSupport = new CaptureSupportImpl(baseRobot);

    private WaitUntilSupport waitUntilSupport = new WaitUntilSupport();

    //---------------------------------------------------------------------------------------------
    // GETTER AND SETTER.
    //---------------------------------------------------------------------------------------------

    public WindowFinder getWindowFinder() {
        return windowFinder;
    }

    public void setWindowFinder(WindowFinder windowFinder) {
        this.windowFinder = windowFinder;
    }

    public NodeFinder getNodeFinder() {
        return nodeFinder;
    }

    public void setNodeFinder(NodeFinder nodeFinder) {
        this.nodeFinder = nodeFinder;
    }

    public BaseRobot getBaseRobot() {
        return baseRobot;
    }

    public void setBaseRobot(BaseRobot baseRobot) {
        this.baseRobot = baseRobot;
    }

    public CaptureSupport getCaptureSupport() {
        return captureSupport;
    }

    public void setCaptureSupport(CaptureSupport captureSupport) {
        this.captureSupport = captureSupport;
    }

    public WaitUntilSupport getWaitUntilSupport() {
        return waitUntilSupport;
    }

    public void setWaitUntilSupport(WaitUntilSupport waitUntilSupport) {
        this.waitUntilSupport = waitUntilSupport;
    }

}
