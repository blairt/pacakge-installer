package org.blair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

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
        
        System.out.println( packageOrder );
        assertTrue( packageOrder.indexOf( "Cyberportal" ) < packageOrder.indexOf( "Leetmeme" ) );
    }
}
