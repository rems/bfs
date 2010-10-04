package net.flx.bfs;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import net.flx.bfs.id.Identifiable;

/**
 * Find a path between two nodes using a breadth-first algorithm.
 * 
 * @author remi fouilloux
 */
public class PathFinder<T> implements IPathFinder<Node<T>> {

	private ISource<Node<T>, T> source;

	private Set<T> alreadyVisited;

	public PathFinder(final ISource<Node<T>, T> source) {
		this.source = source;
	}

	public Path<Node<T>> find(final Node<T> from, final Node<T> to) {
		return find(from, to, MAX_DEPTH);
	}

	public Path<Node<T>> find(final Node<T> from, final Node<T> target, final int maxDepth) {
		alreadyVisited = new HashSet<T>();
		final Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(from);
		int depth = 0;
		boolean found = false;
		while (!queue.isEmpty() && !found) {
			final Node<T> node = queue.poll();
			if (accepts(node)) {
				if (target.equals(node)) {
					/*
					 * found the targeted node
					 */
					found = true;
				} else {
					/*
					 * Try deeper
					 */
					if (++depth < maxDepth) {
						for (final Node<T> c : source.related(node)) {
							if (!alreadyVisited.contains(c.getId())) {
								c.setParent(node);
								queue.add(c);
							}
						}
					} else {
						/*
						 * Too deep, skip
						 */
					}
				}
			} else {
				/*
				 * Already visited, skip
				 */
			}
		}
		final Path<Node<T>> path = new Path<Node<T>>(target);
		if (found) {
			final List<Node<T>> nodes = new LinkedList<Node<T>>();
			Node<T> node = target;
			nodes.add(node);
			while (null != node.getParent()) {
				node = node.getParent();
				nodes.add(node);
			}
			Collections.reverse(nodes);
			path.addAll(nodes);
		}
		return path;
	}

	private boolean accepts(final Identifiable<T> node) {
		if (!alreadyVisited.contains(node.getId())) {
			alreadyVisited.add(node.getId());
			return true;
		}
		return false;
	}

}
