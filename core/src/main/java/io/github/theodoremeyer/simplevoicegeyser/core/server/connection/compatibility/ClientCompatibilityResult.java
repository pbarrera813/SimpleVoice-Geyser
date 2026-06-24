package io.github.theodoremeyer.simplevoicegeyser.core.server.connection.compatibility;

/**
 * Result of websocket client compatibility validation.
 * @param accepted whether the client can continue to authentication
 * @param identity validated client identity when accepted
 * @param message public error message when rejected
 * @param closeCode websocket close code when rejected
 * @param closeReason websocket close reason when rejected
 */
public record ClientCompatibilityResult(
        boolean accepted,
        ClientIdentity identity,
        String message,
        int closeCode,
        String closeReason
) {

    /**
     * Accepted compatibility result.
     * @param identity validated identity
     * @return accepted result
     */
    public static ClientCompatibilityResult accepted(ClientIdentity identity) {
        return new ClientCompatibilityResult(true, identity, "", 0, "");
    }

    /**
     * Rejected compatibility result.
     * @param message public error message
     * @param closeCode close code
     * @param closeReason close reason
     * @return rejected result
     */
    public static ClientCompatibilityResult rejected(String message, int closeCode, String closeReason) {
        return new ClientCompatibilityResult(false, null, message, closeCode, closeReason);
    }
}
