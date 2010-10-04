package net.flx.bfs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import net.flx.bfs.IPathFinder;
import net.flx.bfs.Node;
import net.flx.bfs.Path;
import net.flx.bfs.PathFinder;

import org.junit.Test;

public class PathFinderTest {

	@Test
	public void findPath() {
		final MockContactSource<String> source = new MockContactSource<String>();

		final Node<String> nodeA = source.create("A");
		final Node<String> nodeB = source.create("B");
		final Node<String> nodeC = source.create("C");
		final Node<String> nodeD = source.create("D");
		final Node<String> nodeE = source.create("E");
		final Node<String> nodeF = source.create("F");

		/*
		 *     A
		 *    / \
		 *   B   C
		 *  / \   \
		 * D   E   F
		 */

		source.link(nodeA, nodeB).link(nodeA, nodeC);
		source.link(nodeB, nodeD).link(nodeB, nodeE);
		source.link(nodeC, nodeF);

		final IPathFinder<Node<String>> finder = new PathFinder<String>(source);
		final Path<Node<String>> path = finder.find(nodeF, nodeE);
		assertNotNull(path);
		assertTrue("No path found", path.isResolved());
		assertEquals(5, path.length());
	}

}
