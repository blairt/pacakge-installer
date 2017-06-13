package org.blair;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestPackageInstaller
{
    @Test(expected = java.lang.NullPointerException.class)
    public void testNullPackages()
    {
        PackageInstaller pi = new PackageInstaller();
        pi.buildInstaller( null );
    }
    
    @Test
    public void testPackageOrdering()
    {
        String[] packages2Install =
            { "KittenService: ", "Leetmeme: Cyberportal",
              "Cyberportal: Ice", "CamelCaser: KittenService",
              "Fraudstream: Leetmeme", "Ice: " };
        
        PackageInstaller packInstall = new PackageInstaller();
        String packageOrder = packInstall.buildInstaller( packages2Install );
        // Check to see that Cyberportal is before Leetmeme.
        assertTrue( packageOrder.indexOf( "Cyberportal" ) < packageOrder.indexOf( "Leetmeme" ) );
     // Check to see that KittenService is before KittenService.
        assertTrue( packageOrder.indexOf( "KittenService" ) < packageOrder.indexOf( "CamelCaser" ) );
    }
    
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testPackageOrderingWithCycle()
    {
        String[] packages2Install =
            { "KittenService: ", "Leetmeme: Cyberportal", "Cyberportal: Ice", 
              "CamelCaser: KittenService", "Fraudstream: ","Ice: Leetmeme" };
    
        PackageInstaller packInstall = new PackageInstaller();
        // will throw llegalArgumentException for Cycle Error
        packInstall.buildInstaller( packages2Install );    
    }
}
