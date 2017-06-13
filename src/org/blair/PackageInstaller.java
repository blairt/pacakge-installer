package org.blair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageInstaller
{
    public String buildInstaller( String[] packages2Install )
    {
        // holds the node packages.
        List < String > installNodePackages = new ArrayList < String >();

        // Holds the edge node packages
        List < String[] > installEdgePackages = new ArrayList < String[] >();

        for ( String installPackage : packages2Install )
        {
            String[] packs = installPackage.split( ": " );
            installNodePackages.add( packs[0] );
            if ( packs.length > 1 )
            {
                installNodePackages.add( packs[1] );
                installEdgePackages.add( packs );
            }
        }

        DirectedGraph < String > packages = new DirectedGraph < String >();

        for ( String installPackages : installNodePackages )
        { // add nodes
            packages.addNode( installPackages );
        }

        for ( String[] packageEdgeNodes : installEdgePackages )
        {
            // add dependencies
            packages.addEdge( packageEdgeNodes[0], packageEdgeNodes[1] );
        }

        List < String > orderedPackages = TopologicalSort.sort( packages, true );
        return convertList2String( orderedPackages );
    }

    private String convertList2String( List < String > packages )
    {
        // The string builder used to construct the string
        StringBuilder commaSepValueBuilder = new StringBuilder();

        // Looping through the list
        for ( int i = 0; i < packages.size(); i++ )
        {
            // append the value into the builder
            commaSepValueBuilder.append( packages.get( i ) );

            // if the value is not the last element of the list
            // then append the comma(,) as well
            if ( i != packages.size() - 1 )
            {
                commaSepValueBuilder.append( ", " );
            }
        }
        return commaSepValueBuilder.toString();
    }
}
