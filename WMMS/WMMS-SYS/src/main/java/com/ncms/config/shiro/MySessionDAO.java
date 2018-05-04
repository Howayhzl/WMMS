package com.ncms.config.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

public class MySessionDAO extends AbstractSessionDAO {
	private Map<Serializable, Session> map = new HashMap<Serializable, Session>();

	public void update(Session session) throws UnknownSessionException {
		map.put(session.getId(), session);
	}

	public void delete(Session session) {
		map.remove(session.getId());
	}

	public Collection<Session> getActiveSessions() {
		return map.values();
	}

	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		map.put(sessionId, session);

		return sessionId;
	}

	protected Session doReadSession(Serializable sessionId) {
		return map.get(sessionId);
	}
}