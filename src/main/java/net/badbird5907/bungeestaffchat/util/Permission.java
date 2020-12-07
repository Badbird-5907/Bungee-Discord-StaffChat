package net.badbird5907.bungeestaffchat.util;

public enum Permission {
    BROATCAST_JOIN("broadcast.join"),
    BROADCAST_LEAVE("broadcast.leave"),
    BROADCAST_SWITCH("broadcast.switch"),
    STAFF_CHAT("staffchat"),
    ADMIN_CHAT("adminchat")
    ;

    public final String node;

    Permission(final String node) {
        this.node = "bungeesc." + node;
    }
}
