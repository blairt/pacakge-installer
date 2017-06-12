package org.blair;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class DirectedGraph < T >
    implements Iterable < T >
{
    // A map from nodes in the graph to sets of outgoing edges. 
    // Each set of edges is represented by a map from edges to doubles.
    private final Map < T, Set < T > > mGraph = new HashMap < T, Set < T > >();

    @Override
    public Iterator < T > iterator()
    {
        return mGraph.keySet().iterator();
    }
    
    //Returns the number of nodes.
    public int size()
    {
        return mGraph.size();
    }

    // Checks if the graph is empty
    public boolean isEmpty()
    {
        return mGraph.isEmpty();
    }
    
    // Adds a new node to the graph
    public boolean addNode( T node )
    {
        // Return if the node already exists.
        if ( mGraph.containsKey( node ) ) 
        {
            return false;
        }
        //Add the node with an empty set of outgoing edges.
        mGraph.put( node, new HashSet < T >() );        
        return true;
    }
    
    // Given a start node, and a destination, adds an arc from the start node to
    // the destination. If an arc already exists, this operation is a no-op. If
    // either endpoint does not exist in the graph, throws a NoSuchElementException.
    public void addEdge( T start, T dest )
    {
        // Confirm both endpoints exist.
        if ( !mGraph.containsKey( start ) || !mGraph.containsKey( dest ) )
        {
            throw new NoSuchElementException( "Both nodes must be present." );
        }
        // Add the edge
        mGraph.get( start ).add( dest );
    }

    // Given two nodes in the graph, returns whether there is an edge from the
    // first node to the second node. If either node does not exist in the
    // graph, throws a NoSuchElementException. 
    public boolean edgeExists( T start, T end )
    {
        // Confirm both endpoints exist.
        if ( !mGraph.containsKey( start ) || !mGraph.containsKey( end ) )
        {
            throw new NoSuchElementException( "Both nodes must be present." );
        }
        return mGraph.get( start ).contains( end );
    }

    // Removes the edge from start to dest from the graph. If the edge does not
    // exist, this operation is a no-op. If either endpoint does not exist, this
    // throws a NoSuchElementException.
    public void removeEdge( T start, T dest )
    {
        // Confirm both endpoints exist.
        if ( !mGraph.containsKey( start ) || !mGraph.containsKey( dest ) )
        {
            throw new NoSuchElementException( "Both nodes must be present." );
        }
        mGraph.get( start ).remove( dest );
    }

    // Given a node in the graph, returns an immutable view of the edges 
    // leaving that node as a set of endpoints.
    public Set < T > edgesFrom( T node )
    {
        //Check that the node exists.
        Set < T > arcs = mGraph.get( node );
        if ( arcs == null )
        {
            throw new NoSuchElementException( "Source node does not exist." );
        }
        return Collections.unmodifiableSet( arcs );
    }
}
