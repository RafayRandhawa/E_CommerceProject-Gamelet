package org.example.e_commerceproject.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {

    private final HttpSession httpSession;

    // Injecting the HttpSession object
    public SessionService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    /**
     * Sets an attribute in the HTTP session.
     *
     * @param key   the name of the attribute
     * @param value the value to set
     */
    public void setAttribute(String key, Object value) {
        httpSession.setAttribute(key, value);
    }

    /**
     * Gets an attribute from the HTTP session.
     *
     * @param key the name of the attribute
     * @return the value of the attribute or null if not present
     */
    public Object getAttribute(String key) {
        return httpSession.getAttribute(key);
    }

    /**
     * Removes an attribute from the HTTP session.
     *
     * @param key the name of the attribute to remove
     */
    public void removeAttribute(String key) {
        httpSession.removeAttribute(key);
    }

    /**
     * Invalidates the current HTTP session, clearing all attributes.
     */
    public void invalidateSession() {
        httpSession.invalidate();
    }

    /**
     * Checks if the session is new.
     *
     * @return true if the session is new, false otherwise
     */
    public boolean isSessionNew() {
        return httpSession.isNew();
    }

    /**
     * Gets the current session ID.
     *
     * @return the session ID as a string
     */
    public String getSessionId() {
        return httpSession.getId();
    }
}
