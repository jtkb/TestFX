package org.testfx.osgi.service;

/**
 * Test classes should declare a service dependency on this interface to ensure they do not
 * execute before the TestFx bundle is resolved.
 */
public interface TestFx
{
    /**
     * Will always return TRUE.
     * @return the value TRUE.
     */
    boolean isActive();
}
