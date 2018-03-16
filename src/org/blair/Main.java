package org.blair;

public class Main
{

    public static void main( String[] args )
    {
        String[] packages2Install =
            { "KittenService: ", "Leetmeme: Cyberportal",
              "Cyberportal: Ice", "CamelCaser: KittenService",
              "Fraudstream: Leetmeme", "Ice: " };
        
        PackageInstaller packInstall = new PackageInstaller();
        String packageOrder = packInstall.buildInstaller( packages2Install );
        
        System.out.println("Packages to Install -> " + packageOrder);

    }

}
