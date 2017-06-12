package org.blair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class TestDirectedGraph
{
    @Test
    public void testEmptyGraph()
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        assertTrue( "Graph is not Empty", dag.isEmpty() );
    }

    @Test (expected = java.util.NoSuchElementException.class)
    public void testEdgeExistsException( )
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.edgeExists( "TestNode2","TestNode1" );        
    }

    @Test
    public void testAddNode()
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.addNode("TestNode");
        assertEquals( 1, dag.size() );
    }
    
    @Test (expected = java.util.NoSuchElementException.class)
    public void testAddEdgeNodeException()
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.addEdge("TestNodeTwo","TestNodeOne");
    }
    
    @Test (expected = java.util.NoSuchElementException.class)
    public void testRemoveEdgeNodeException()
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.removeEdge( "TestNode2","TestNode1" );
    }
    
    @Test (expected = java.util.NoSuchElementException.class)
    public void testEdgesFromException( )
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.edgesFrom( "TestNode2" );  
    }

    @Test
    public void testEdgeExists( )
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.addNode( "TestNodeOne" );
        dag.addNode( "TestNodeTwo" );       
        dag.addEdge("TestNodeTwo","TestNodeOne");
        assertTrue( dag.edgeExists( "TestNodeTwo","TestNodeOne" ) );
    }
    
    @Test
    public void testAddEdgeNode()
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.addNode( "TestNodeOne" );
        dag.addNode( "TestNodeTwo" );
        dag.addEdge("TestNodeTwo","TestNodeOne");
        assertEquals( 2, dag.size() );
    }
    
    @Test
    public void testRemoveEdgeNode()
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.addNode( "TestNodeOne" );
        dag.addNode( "TestNodeTwo" );
        dag.addEdge("TestNodeTwo","TestNodeOne");
        dag.removeEdge( "TestNodeTwo","TestNodeOne" );
        assertFalse( dag.edgeExists( "TestNodeTwo","TestNodeOne" ) );
    }
    
    @Test
    public void testEdgesFrom( )
    {
        DirectedGraph < String > dag = new DirectedGraph < String >();
        dag.addNode( "TestNode1" );
        dag.addNode( "TestNode2" ); 
        dag.addNode( "TestNode3" );
        dag.addNode( "TestNode4" );
        dag.edgeExists( "TestNode4","TestNode3" );
        dag.edgeExists( "TestNode2","TestNode1" );
         Set<String> edges = dag.edgesFrom( "TestNode3" );  
        assertEquals(0,edges.size() );        
    }
}
