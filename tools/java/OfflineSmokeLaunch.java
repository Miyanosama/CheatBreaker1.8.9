import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.security.Permission;

/** Test launcher only; not included in the rebuilt client. */
public final class OfflineSmokeLaunch {
    public static void main(String[] args) throws Throwable {
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
            }

            @Override
            public void checkConnect(String host, int port) {
                if (!"localhost".equalsIgnoreCase(host) && !"127.0.0.1".equals(host)
                        && !"::1".equals(host)) {
                    throw new SecurityException("Offline recovery smoke test: " + host);
                }
            }

            @Override
            public void checkConnect(String host, int port, Object context) {
                checkConnect(host, port);
            }
        });
        System.out.println("RECOVERY_SMOKE_OFFLINE_START");
        if (Boolean.getBoolean("recovery.smoke.probe")) {
            Class.forName("RecoverySmokeProbe").getMethod("start").invoke(null);
        }
        try {
            Class.forName("net.minecraft.client.main.Main")
                    .getMethod("main", String[].class).invoke(null, (Object) args);
        } catch (InvocationTargetException exception) {
            throw exception.getCause();
        }
    }
}
