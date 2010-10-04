package net.flx.bfs;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.flx.bfs.ISource;
import net.flx.bfs.Node;

public class MockContactSource<T> implements ISource<Node<T>, T> {

	private final Map<T, Node<T>> elements = new HashMap<T, Node<T>>();

	private final Map<T, Collection<T>> links = new HashMap<T, Collection<T>>();

	public Collection<Node<T>> related(final Node<T> element) {
		final Collection<Node<T>> related = new LinkedList<Node<T>>();
		for (final T id : links.get(element.getId())) {
			related.add(get(id));
		}
		return related;
	}

	public Node<T> get(final T id) {
		return elements.get(id);
	}

	public Node<T> create(final T id) {
		final Node<T> c = new Node<T>(id);
		elements.put(c.getId(), c);
		return c;
	}

	public MockContactSource<T> link(final Node<T> c1, final Node<T> other) {
		final T id1 = c1.getId();
		if (!links.containsKey(id1)) {
			links.put(id1, new LinkedList<T>());
		}
		final T id2 = other.getId();
		links.get(id1).add(id2);
		if (!links.containsKey(id2)) {
			links.put(id2, new LinkedList<T>());
		}
		links.get(id2).add(id1);
		return this;
	}

}
