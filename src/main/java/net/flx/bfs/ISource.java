package net.flx.bfs;

import java.util.Collection;

import net.flx.bfs.id.Identifiable;

public interface ISource<T extends Identifiable<E>, E> extends IProvider<T, E> {

	/**
	 * Returns elements related to the <code>origin</code>.
	 * 
	 * @param origin
	 * @return
	 */
	Collection<T> related(final T origin);

}
