package com.cheatbreaker.client.util.cosmetic;

import com.cheatbreaker.client.util.ClientResourceManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;

/** Bundled cosmetics for the local player, independent of the assets server. */
public final class LocalCosmetics {
    private final List<ClientResourceManager> cosmetics = new ArrayList<>();
    private final File configFile;

    public LocalCosmetics(File configFile) {
        this.configFile = configFile;
        Properties selected = new Properties();
        if (configFile.isFile()) {
            try (InputStream input = Files.newInputStream(configFile.toPath())) {
                selected.load(input);
            } catch (IOException error) {
                LogManager.getLogger().warn("Could not load local cosmetics", error);
            }
        }
        try (InputStream input = LocalCosmetics.class.getResourceAsStream("/assets/minecraft/client/local-cosmetics.txt")) {
            if (input == null) throw new IOException("Missing local cosmetics catalog");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                String path;
                while ((path = reader.readLine()) != null) {
                    if (path.isEmpty()) continue;
                    CosmeticType type = path.startsWith("client/capes/") ? CosmeticType.CAPE : CosmeticType.WINGS;
                    String name = path.substring("client/".length(), path.length() - 4).replace('/', ' ');
                    cosmetics.add(new ClientResourceManager("local", name, type,
                        type == CosmeticType.WINGS ? 0.125F : 1.0F,
                        path.equals(selected.getProperty(type.name())), path));
                }
            }
        } catch (IOException error) {
            LogManager.getLogger().warn("Could not load local cosmetics catalog", error);
        }
    }

    public List<ClientResourceManager> getCosmetics() {
        return Collections.unmodifiableList(cosmetics);
    }

    public ClientResourceManager getEquipped(CosmeticType type) {
        for (ClientResourceManager cosmetic : cosmetics) {
            if (cosmetic.method_20848() == type && cosmetic.method_20849()) return cosmetic;
        }
        return null;
    }

    public void toggle(ClientResourceManager selected) {
        if (!cosmetics.contains(selected)) return;
        boolean equip = !selected.method_20849();
        for (ClientResourceManager cosmetic : cosmetics) {
            if (cosmetic.method_20848() == selected.method_20848()) {
                cosmetic.method_20857(cosmetic == selected && equip);
            }
        }
        Properties saved = new Properties();
        for (ClientResourceManager cosmetic : cosmetics) {
            if (cosmetic.method_20849()) {
                saved.setProperty(cosmetic.method_20848().name(),
                    cosmetic.method_20859().getResourcePath());
            }
        }
        try {
            Files.createDirectories(configFile.toPath().toAbsolutePath().getParent());
            File temporary = new File(configFile.getPath() + ".tmp");
            try (OutputStream output = Files.newOutputStream(temporary.toPath())) {
                saved.store(output, "Local cosmetic selection");
            }
            Files.move(temporary.toPath(), configFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException error) {
            LogManager.getLogger().warn("Could not save local cosmetics", error);
        }
    }
}
