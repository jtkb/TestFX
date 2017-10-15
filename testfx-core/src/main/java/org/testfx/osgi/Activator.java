package org.testfx.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.testfx.osgi.service.TestFx;

public class Activator implements BundleActivator
{
    private ServiceRegistration<TestFx> serviceRegistration;
    @Override
    public void start(final BundleContext bundleContext) throws Exception
    {
        this.serviceRegistration = bundleContext.registerService(TestFx.class, () -> true, null);
    }

    @Override
    public void stop(final BundleContext bundleContext) throws Exception
    {
        this.serviceRegistration.unregister();
    }
}
