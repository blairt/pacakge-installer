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
    
    public int size()
    {
        return mGraph.size();
    }

    public boolean isEmpty()
    {
        return mGraph.isEmpty();
    }
    
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

    public boolean edgeExists( T start, T end )
    {
        // Confirm both endpoints exist.
        if ( !mGraph.containsKey( start ) || !mGraph.containsKey( end ) )
        {
            throw new NoSuchElementException( "Both nodes must be present." );
        }
        return mGraph.get( start ).contains( end );
    }

    public void removeEdge( T start, T dest )
    {
        // Confirm both endpoints exist.
        if ( !mGraph.containsKey( start ) || !mGraph.containsKey( dest ) )
        {
            throw new NoSuchElementException( "Both nodes must be present." );
        }
        mGraph.get( start ).remove( dest );
    }

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
