package net.optifine.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.netty.handler.codec.compression.DecompressionException;
import io.netty.handler.codec.spdy.SpdySession;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$KeyIterator;
import javazoom.jl.player.NullAudioDevice;
import net.minecraft.src.Config;
import net.optifine.http.IFileDownloadListener;
import recovered.unidentified.UnidentifiedClass0499;

public class PlayerConfigurationReceiver implements IFileDownloadListener {
   public StringUtil field_0003;
   public ConcurrentHashMapV8$KeyIterator field_0005;
   public UnidentifiedClass0499 field_0002;
   public String player = null;
   public NullAudioDevice field_0000;
   public DecompressionException field_0001;
   public SpdySession field_0006;

   public PlayerConfigurationReceiver(String var1) {
      this.player = var1;
   }

   @Override
   public void fileDownloadFinished(String var1, byte[] var2, Throwable var3) {
      if (var2 != null) {
         try {
            String var4 = new String(var2, "ASCII");
            JsonParser var5 = new JsonParser();
            JsonElement var6 = var5.parse(var4);
            PlayerConfigurationParser var7 = new PlayerConfigurationParser(this.player);
            PlayerConfiguration var8 = var7.parsePlayerConfiguration(var6);
            if (var8 != null) {
               var8.setInitialized(true);
               PlayerConfigurations.setPlayerConfiguration(this.player, var8);
            }
         } catch (Exception var9) {
            Config.dbg("Error parsing configuration: " + var1 + ", " + var9.getClass().getName() + ": " + var9.getMessage());
         }
      }
   }
}
