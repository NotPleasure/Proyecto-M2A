package utils;

public class Session {

    private static int currentUserId;

    private Session() {
    }

    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }
}
