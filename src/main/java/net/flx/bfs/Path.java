package net.flx.bfs;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Path<E> {

	private transient ArrayDeque<E> stack = new ArrayDeque<E>();

	private transient final E target;

	private boolean resolved = false;

	public Path(final E to) {
		target = to;
	}

	public void addAll(final Iterable<E> elements) {
		for (final E element : elements) {
			add(element);
		}
	}

	public void add(final E element) {
		if (!resolved) {
			stack.addLast(element);
			if (target == element) {
				resolved = true;
			}
		}
	}

	public void remove() {
		if (!resolved) {
			stack.poll();
		}
	}

	public boolean isResolved() {
		return resolved;
	}

	@Override
	public String toString() {
		final StringBuilder printable = new StringBuilder();
		for (final Iterator<E> it = stack.iterator(); it.hasNext();) {
			final E element = it.next();
			printable.append(element);
			if (it.hasNext()) {
				printable.append(" -> ");
			}
		}
		return printable.toString();
	}

	public int length() {
		return stack.size();
	}

	public void clear() {
		stack.clear();
	}

	public Collection<E> copy() {
		return new LinkedList<E>(stack);
	}

}
