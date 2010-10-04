package net.flx.bfs;

import net.flx.bfs.id.Identifiable;

public interface IProvider<T extends Identifiable<E>, E> {

	T create(final E id);

	T get(final E id);

	IProvider<T, E> link(final T c1, final T other);
}
