package net.flx.bfs;

import net.flx.bfs.id.Identifiable;

public class Node<T> implements Identifiable<T> {

	private final T id;

	private transient Node<T> parent = null;

	public Node(final T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public Node<T> setParent(final Node<T> parent) {
		if (this != parent) {
			/*
			 * Avoid recursion
			 */
			this.parent = parent;
		}
		return this;
	}

	public Node<T> getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

}
