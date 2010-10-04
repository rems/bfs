package net.flx.bfs;

import java.util.Iterator;

public class IterableUtils {

	public static <T extends Comparable<T>> boolean same(final Iterable<T> actual, final Iterable<T> expected) {
		assert null != actual;
		assert null != expected;
		final Iterator<T> it1 = actual.iterator();
		final Iterator<T> it2 = expected.iterator();
		int offset = 0;
		while (it1.hasNext()) {
			if (it2.hasNext()) {
				final T d1 = it1.next();
				final T d2 = it2.next();
				if (0 != d1.compareTo(d2)) {
					return false;
				}
			} else {
				return false;
			}
			offset++;
		}
		if (it2.hasNext()) {
			return false;
		}
		return true;
	}

}
