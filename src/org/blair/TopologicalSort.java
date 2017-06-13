package org.blair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TopologicalSort
{
    public static < T > List < T > sort( DirectedGraph < T > dg )
    {
        // Construct the reverse graph from the input graph.
        DirectedGraph < T > dgRev = reverseGraph( dg );
        
        // Maintain two structures - a set of visited nodes (so that once we've
        // added a node to the list, we don't label it again), and a list of
        // nodes that actually holds the topological ordering.
        List < T > result = new ArrayList < T >();
        Set < T > visited = new HashSet < T >();

        // Also maintain a third set consisting of all nodes that have
        // been fully expanded. If the graph contains a cycle, then we can
        // detect this by noting that a node has been explored but not fully
        // expanded.
        Set < T > expanded = new HashSet < T >();
       
        // Fire off a Depth-First Search from each node in the graph.
        for ( T node : dgRev )
        {
            explore( node, dgRev, result, visited, expanded );
        }

        return result;
    }
    
    private static < T > DirectedGraph < T > reverseGraph( DirectedGraph < T > dg )
    {
        DirectedGraph < T > result = new DirectedGraph < T >();

        // Add nodes from the original graph.
        for ( T node : dg )
        {
            result.addNode( node );
        }

        // Scan over all the edges in the graph, adding their reverse to the
        // reverse graph.
        for ( T node : dg )
        {
            for ( T endpoint : dg.edgesFrom( node ) )
            {
                result.addEdge( endpoint, node );
            }
        }
        return result;
    }
    
    private static < T > void explore( T node, DirectedGraph < T > dg,
                                       List < T > ordering, Set < T > visited,
                                       Set < T > expanded )
    {
        // Check whether we've been here before.
        // If so, we should stop the search.
        if ( visited.contains( node ) )
        {
            // Two cases to consider. First, if this node has already
            // been expanded, then it's already been assigned a position in the
            // final topological sort and we don't need to explore it again.
            // Second, if it hasn't been expanded, it means that we've just
            // found a node that is currently being explored, and therefore is
            // part of a cycle. In that case, we should report an error.

            if ( expanded.contains( node ) ) return;

            throw new IllegalArgumentException( "Graph contains a cycle." );
        }

        // Mark that we've been here
        visited.add( node );

        // Recursively explore all of the node's predecessors.
        for ( T predecessor : dg.edgesFrom( node ) )
        {
            explore( predecessor, dg, ordering, visited, expanded );
        }

        // Having explored all of the node's predecessors, we can now add this
        // node to the sorted ordering.
        ordering.add( node );

        // Similarly, mark that this node is done being expanded.
        expanded.add( node );
    }
}
