package com.ncms.comm.base.enums;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class BaseEnum<E> implements Serializable {

	private static final long serialVersionUID = 1L;
	private final TreeMap<E, String> enumMap = new TreeMap<E, String>();

	public void putEnum(E value, String name) {
		this.enumMap.put(value, name);
	}

	public Map<E, String> getAllEnum() {
		return this.enumMap;
	}

	public String getName(E value) {
		return ((String) this.enumMap.get(value));
	}

	public String[] getAllNames() {
		String[] names = new String[this.enumMap.size()];
		this.enumMap.values().toArray(names);
		return names;
	}

	public E getValue(String name) {
		E value = null;
		if (this.enumMap.containsValue(name)) {
			Iterator<E> i = this.enumMap.keySet().iterator();
			while (i.hasNext()) {
				E key = i.next();
				String enumName = (String) this.enumMap.get(key);
				if (enumName.equals(name)) {
					value = key;
					break;
				}
			}
		}
		return value;
	}
}
