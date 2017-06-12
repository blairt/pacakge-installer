package org.blair;

import org.junit.Test;

public class TestPackageInstaller
{
    @Test(expected = java.lang.NullPointerException.class)
    public void testNullPackages()
    {
        PackageInstaller pi = new PackageInstaller();
        pi.buildInstaller( null );
    }
}
