package org.mannayakasha.model;

/**
 * Enum of priority types, that represents of {@link org.mannayakasha.model.User}.
 *
 * @author Pavel
 * @version 1.0 03.08.2017.
 */
public enum AccessType {
    ADMIN("admin"), USER("user"), GUEST("guest");

    private String access;

    AccessType(String access) {
        this.access = access;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public static AccessType getById(Integer id) {
        return AccessType.values()[id];
    }
}
