package io.github.theodoremeyer.simplevoicegeyser.core.server.connection.compatibility;

/**
 * Validated client identity metadata for diagnostics.
 * @param kind client kind
 * @param version display version
 * @param protocol native app protocol
 */
public record ClientIdentity(String kind, String version, int protocol) {

    private static final ClientIdentity BROWSER = new ClientIdentity("browser", "", 0);

    /**
     * Browser client identity.
     * @return browser identity
     */
    public static ClientIdentity browser() {
        return BROWSER;
    }

    /**
     * Safe representation for logs.
     * @return log-safe identity string
     */
    public String toLogString() {
        if (this.equals(BROWSER)) {
            return "browser";
        }

        return kind + " version=" + sanitizeForLog(version) + " protocol=" + protocol;
    }

    private static String sanitizeForLog(String value) {
        StringBuilder sanitized = new StringBuilder(value.length());

        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            sanitized.append(ch < 0x20 || ch == 0x7F ? '?' : ch);
        }

        return sanitized.toString();
    }
}
