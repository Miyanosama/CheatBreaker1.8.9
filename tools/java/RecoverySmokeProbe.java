import com.cheatbreaker.client.CheatBreaker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/** External test harness. This class is never packaged into the recovered client. */
public final class RecoverySmokeProbe {
    public static void start() {
        Thread probe = new Thread(() -> {
            try {
                Minecraft mc = null;
                long deadline = System.currentTimeMillis() + 180000L;
                while (System.currentTimeMillis() < deadline) {
                    mc = Minecraft.getMinecraft();
                    if (mc != null && mc.currentScreen != null && CheatBreaker.getInstance() != null
                            && CheatBreaker.getInstance().getModuleManager() != null) break;
                    Thread.sleep(500L);
                }
                if (mc == null || mc.currentScreen == null) throw new AssertionError("Menu did not initialize");
                final Minecraft client = mc;
                final boolean previousPause = client.gameSettings.recoveredField2704;
                client.addScheduledTask(() -> client.gameSettings.recoveredField2704 = false).get(30, TimeUnit.SECONDS);
                Thread.sleep(4000L);
                client.addScheduledTask(() -> {
                    System.out.println("RECOVERY_SMOKE_MENU " + client.currentScreen.getClass().getName());
                    capture(client, "menu.png");
                }).get(30, TimeUnit.SECONDS);
                if (Boolean.getBoolean("recovery.smoke.world")) {
                    client.addScheduledTask(() -> client.launchIntegratedServer("RecoverySmokeWorld", "Recovery Smoke World",
                            new WorldSettings(123456L, WorldSettings.GameType.CREATIVE, false, false, WorldType.FLAT)));
                    deadline = System.currentTimeMillis() + 180000L;
                    while (client.theWorld == null || client.thePlayer == null) {
                        if (System.currentTimeMillis() >= deadline) throw new AssertionError("Local world did not load");
                        Thread.sleep(500L);
                    }
                    Thread.sleep(8000L);
                    client.addScheduledTask(() -> {
                        System.out.println("RECOVERY_SMOKE_WORLD player=" + client.thePlayer.getClass().getName()
                                + " world=" + client.theWorld.getClass().getName());
                        verifyServerRules();
                        java.lang.reflect.Field careerId = net.optifine.reflect.Reflector.EntityVillager_careerId.getTargetField();
                        java.lang.reflect.Field careerLevel = net.optifine.reflect.Reflector.EntityVillager_careerLevel.getTargetField();
                        if (careerId == null || !careerId.getName().equals("careerId")
                                || careerLevel == null || !careerLevel.getName().equals("careerLevel"))
                            throw new AssertionError("Villager reflection field identity");
                        System.out.println("RECOVERY_SMOKE_REFLECTION_PASSED");
                        capture(client, "world.png");
                    }).get(30, TimeUnit.SECONDS);
                    if (Boolean.getBoolean("recovery.smoke.hud")) {
                        java.util.Map<com.cheatbreaker.client.module.AbstractModule, Boolean> states = new java.util.LinkedHashMap<>();
                        java.util.Map<com.cheatbreaker.client.module.AbstractModule, float[]> positions = new java.util.LinkedHashMap<>();
                        java.util.Map<com.cheatbreaker.client.module.AbstractModule, com.cheatbreaker.client.ui.module.CBGuiAnchor> anchors = new java.util.LinkedHashMap<>();
                        client.addScheduledTask(() -> {
                            int slot = 0;
                            for (com.cheatbreaker.client.module.AbstractModule module : CheatBreaker.getInstance().getModuleManager().recoveredField1706) {
                                if (module instanceof com.cheatbreaker.client.module.type.keystrokes.KeystrokesModule
                                        || module instanceof com.cheatbreaker.client.module.type.TextHudModule) {
                                    states.put(module, module.isEnabled());
                                    module.setState(true);
                                    if (module instanceof com.cheatbreaker.client.module.type.TextHudModule) {
                                        positions.put(module, new float[]{module.xTranslation, module.yTranslation});
                                        anchors.put(module, module.guiAnchor);
                                        module.guiAnchor = com.cheatbreaker.client.ui.module.CBGuiAnchor.LEFT_TOP;
                                        module.xTranslation = 85 + (slot / 7) * 130;
                                        module.yTranslation = 5 + (slot % 7) * 24;
                                        slot++;
                                    }
                                }
                            }
                            client.displayGuiScreen(null);
                        }).get(30, TimeUnit.SECONDS);
                        Thread.sleep(4000L);
                        client.addScheduledTask(() -> {
                            capture(client, "hud.png");
                            for (com.cheatbreaker.client.module.AbstractModule module : states.keySet()) {
                                if (!Float.isFinite(module.recoveredField3889) || !Float.isFinite(module.recoveredField3894)
                                        || module.recoveredField3889 < 0 || module.recoveredField3894 < 0)
                                    throw new AssertionError("Invalid HUD bounds: " + module.getName());
                                System.out.println("RECOVERY_SMOKE_HUD " + module.getName() + " bounds="
                                        + module.recoveredField3889 + "x" + module.recoveredField3894);
                            }
                            client.displayGuiScreen(new com.cheatbreaker.client.ui.module.CBModulesGui());
                        }).get(30, TimeUnit.SECONDS);
                        Thread.sleep(4000L);
                        client.addScheduledTask(() -> {
                            capture(client, "hud-editor.png");
                            client.displayGuiScreen(null);
                            for (java.util.Map.Entry<com.cheatbreaker.client.module.AbstractModule, Boolean> state : states.entrySet()) {
                                float[] position = positions.get(state.getKey());
                                if (position != null) {
                                    state.getKey().xTranslation = position[0];
                                    state.getKey().yTranslation = position[1];
                                    state.getKey().guiAnchor = anchors.get(state.getKey());
                                }
                                state.getKey().setState(state.getValue());
                            }
                            System.out.println("RECOVERY_SMOKE_HUD_PASSED");
                        }).get(30, TimeUnit.SECONDS);
                    }
                }
                System.out.println("RECOVERY_SMOKE_PASSED");
                if (Boolean.getBoolean("recovery.smoke.exit")) client.addScheduledTask(() -> {
                    client.gameSettings.recoveredField2704 = previousPause;
                    client.shutdown();
                });
            } catch (Throwable failure) {
                System.err.println("RECOVERY_SMOKE_FAILED");
                failure.printStackTrace();
            }
        }, "Recovery smoke probe");
        probe.setDaemon(true);
        probe.start();
    }

    private static void verifyServerRules() {
        com.cheatbreaker.client.nethandler.NetHandler handler = new com.cheatbreaker.client.nethandler.NetHandler();
        com.cheatbreaker.client.module.ModuleRule previous = com.cheatbreaker.client.module.type.MiniMapModule.state;
        try {
            handler.method_11450(new com.cheatbreaker.client.nethandler.server.PacketServerRule(
                    com.cheatbreaker.client.nethandler.obj.ServerRule.VOICE_ENABLED, true));
            if (!handler.recoveredField1478 || handler.recoveredField1476 || handler.recoveredField1474)
                throw new AssertionError("Voice rule routing");
            handler.method_11450(new com.cheatbreaker.client.nethandler.server.PacketServerRule(
                    com.cheatbreaker.client.nethandler.obj.ServerRule.MINIMAP_STATUS, "FORCED_OFF"));
            if (com.cheatbreaker.client.module.type.MiniMapModule.state != com.cheatbreaker.client.module.ModuleRule.FORCED_OFF)
                throw new AssertionError("Minimap rule routing");
            handler.method_11450(new com.cheatbreaker.client.nethandler.server.PacketServerRule(
                    com.cheatbreaker.client.nethandler.obj.ServerRule.SERVER_HANDLES_WAYPOINTS, true));
            handler.method_11450(new com.cheatbreaker.client.nethandler.server.PacketServerRule(
                    com.cheatbreaker.client.nethandler.obj.ServerRule.COMPETITIVE_GAMEMODE, true));
            if (!handler.recoveredField1476 || !handler.recoveredField1474)
                throw new AssertionError("Waypoint/competitive rule routing");
            handler.method_11450(new com.cheatbreaker.client.nethandler.server.PacketServerRule(
                    com.cheatbreaker.client.nethandler.obj.ServerRule.LEGACY_COMBAT, false));
            if (!handler.recoveredField1474) throw new AssertionError("Unexpected legacy rule fallthrough");
            System.out.println("RECOVERY_SMOKE_PROTOCOL_PASSED");
        } finally { com.cheatbreaker.client.module.type.MiniMapModule.state = previous; }
    }

    private static void capture(Minecraft mc, String name) {
        try {
            Framebuffer framebuffer = mc.getFramebuffer();
            int width = framebuffer.framebufferTextureWidth;
            int height = framebuffer.framebufferTextureHeight;
            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
            int previous = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer.framebufferTexture);
            GL11.glGetTexImage(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, previous);
            BufferedImage image = new BufferedImage(framebuffer.framebufferWidth, framebuffer.framebufferHeight,
                    BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int i = ((height - 1 - y) * width + x) * 4;
                    image.setRGB(x, y, (pixels.get(i) & 255) << 16 | (pixels.get(i + 1) & 255) << 8 | pixels.get(i + 2) & 255);
                }
            }
            File output = new File(mc.mcDataDir, "smoke-evidence/" + name);
            output.getParentFile().mkdirs();
            ImageIO.write(image, "png", output);
            System.out.println("RECOVERY_SMOKE_CAPTURE " + output);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}

