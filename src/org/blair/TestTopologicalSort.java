package org.blair;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestTopologicalSort
{
    boolean left2Right = true;
    
    @Test(expected = java.lang.NullPointerException.class)
    public void testNullGraph()
    {
        TopologicalSort.sort( null, true );
    }

    @Test
    public void testEmptyGraph()
    {
        DirectedGraph < String > dg = new DirectedGraph < String >();
        List < String > orderedPackages = TopologicalSort.sort( dg, left2Right );
        assertEquals( 0, orderedPackages.size() );
    }
    
    @Test
    public void testSingleNode()
    {
        final DirectedGraph < String > dg = new DirectedGraph < String >();
        dg.addNode( "TestNodeOne" );
        final List < String > orderedPackages = TopologicalSort.sort( dg, left2Right );
        assertEquals( 1, orderedPackages.size() );
        assertTrue( orderedPackages.contains( "TestNodeOne" ) );
    }
    
    @Test
    public void testMultipleNodes()
    {
        final DirectedGraph < String > dg = new DirectedGraph < String >();
        dg.addNode( "TestNodeOne" );
        dg.addNode( "TestNodeTwo" );
        final List < String > orderedPackages = TopologicalSort.sort( dg, left2Right);
        assertEquals( 2, orderedPackages.size() );
        assertTrue( orderedPackages.contains( "TestNodeOne" ) );
        assertTrue( orderedPackages.contains( "TestNodeTwo" ) );
    }
    
    @Test(expected = java.util.NoSuchElementException.class)
    public void testBogusEdge()
    {
        final DirectedGraph < String > dg = new DirectedGraph < String >();
        dg.addNode( "TestNodeOne" );
        dg.addEdge( "TestNodeTwo", "TestNodeOne" );
    }
    
    @Test
    public void testPakageGraph()
    {
        final DirectedGraph < String > packages = new DirectedGraph < String >();
        packages.addNode( "TestNodeOne" );
        packages.addNode( "TestNodeTwo" );
        packages.addNode( "TestNodeThree" );
        packages.addNode( "TestNodeFour" );
        packages.addNode( "TestNodeFive" );
        packages.addNode( "TestNodeSix" );
        packages.addEdge( "TestNodeTwo", "TestNodeThree" ); // TestNodeTwo depends on TestNodeThree
        packages.addEdge( "TestNodeThree", "TestNodeFour" ); // TestNodeThree depends on TestNodeFour
        packages.addEdge( "TestNodeFive", "TestNodeOne" ); // TestNodeFive depends on TestNodeOne
        packages.addEdge( "TestNodeSix", "TestNodeTwo" ); // TestNodeSix depends on TestNodeTwo

        final List < String > orderedPackages = TopologicalSort.sort( packages, left2Right );
        assertEquals( 6, orderedPackages.size() );
        assertTrue( orderedPackages.indexOf( "TestNodeThree" ) < orderedPackages.indexOf( "TestNodeTwo" ) );
        assertTrue( orderedPackages.indexOf( "TestNodeFour" ) < orderedPackages.indexOf( "TestNodeThree" ) );
        assertTrue( orderedPackages.indexOf( "TestNodeOne" ) < orderedPackages.indexOf( "TestNodeFive" ) );
        assertTrue( orderedPackages.indexOf( "TestNodetwo" ) < orderedPackages.indexOf( "TestNodeSix" ) );

        assertTrue( orderedPackages.get( 0 ).equals( "TestNodeOne" ) );
        assertTrue( orderedPackages.get( orderedPackages.size() - 1 ).equals( "TestNodeSix" ) );
    }
    
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testCyclePakageGraph()
    {
        final DirectedGraph < String > packages = new DirectedGraph < String >();
        packages.addNode( "KittenService" );
        packages.addNode( "Leetmeme" );
        packages.addNode( "Cyberportal" );
        packages.addNode( "Ice" );
        packages.addNode( "CamelCaser" );
        packages.addNode( "Fraudstream" );
        packages.addEdge( "Leetmeme", "Cyberportal" );
        packages.addEdge( "Cyberportal", "Ice" );
        packages.addEdge( "CamelCaser", "KittenService" );
        packages.addEdge( "Ice", "Leetmeme" );

        TopologicalSort.sort( packages, left2Right ); // triggers the exception
    }
}
