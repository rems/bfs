package net.flx.bfs;

public interface IPathFinder<E> {

	public static final int MAX_DEPTH = 6;

	Path<E> find(final E from, final E to);

	Path<E> find(final E from, final E to, final int depth);

}
