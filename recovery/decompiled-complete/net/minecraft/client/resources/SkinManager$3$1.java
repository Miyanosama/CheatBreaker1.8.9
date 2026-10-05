package net.minecraft.client.resources;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchKeysTask;
import java.util.Map;
import net.minecraft.crash.CrashReport$2;

public class SkinManager$3$1 implements Runnable {
   public CrashReport$2 field_0001;
   public ConcurrentHashMapV8$SearchKeysTask field_0000;

   public SkinManager$3$1(SkinManager$3 var1, Map var2) {
      this.field_152804_b = var1;
      this.field_152803_a = var2;
      super();
   }

   @Override
   public void run() {
      if (this.field_152803_a.containsKey(Type.SKIN)) {
         this.field_152804_b
            .field_152802_d
            .loadSkin((MinecraftProfileTexture)this.field_152803_a.get(Type.SKIN), Type.SKIN, this.field_152804_b.field_152801_c);
      }

      if (this.field_152803_a.containsKey(Type.CAPE)) {
         this.field_152804_b
            .field_152802_d
            .loadSkin((MinecraftProfileTexture)this.field_152803_a.get(Type.CAPE), Type.CAPE, this.field_152804_b.field_152801_c);
      }
   }
}
