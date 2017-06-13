package org.blair;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestTopologicalSort
{
    @Test(expected = java.lang.NullPointerException.class)
    public void testNullGraph()
    {
        TopologicalSort.sort( null );
    }

    @Test
    public void testEmptyGraph()
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        List < String > orderedPackages = TopologicalSort.sort( dag );
        assertEquals( 0, orderedPackages.size() );
    }
    
    @Test
    public void testSingleNode()
    {
        final DirectedGraph < String > dg = new DirectedGraph < String >();
        dg.addNode( "TestNodeOne" );
        final List < String > orderedPackages = TopologicalSort.sort( dg );
        assertEquals( 1, orderedPackages.size() );
        assertTrue( orderedPackages.contains( "TestNodeOne" ) );
    }


}
