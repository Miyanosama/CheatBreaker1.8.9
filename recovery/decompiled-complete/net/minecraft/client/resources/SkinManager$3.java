package net.minecraft.client.resources;

import com.cheatbreaker.client.ui.module.CBPositionEnum;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.server.CommandStop;
import org.java_websocket.exceptions.LimitExceededException;

public class SkinManager$3 implements Runnable {
   public LimitExceededException field_0003;
   public CBPositionEnum field_0002;
   public NetHandlerPlayClient field_0005;
   public CommandStop field_0001;

   @Override
   public void run() {
      HashMap var1 = Maps.newHashMap();

      try {
         var1.putAll(SkinManager.access$000(this.field_152802_d).getTextures(this.field_152799_a, this.field_152800_b));
      } catch (InsecureTextureException var3) {
      }

      if (var1.isEmpty() && this.field_152799_a.getId().equals(Minecraft.getMinecraft().getSession().getProfile().getId())) {
         this.field_152799_a.getProperties().clear();
         this.field_152799_a.getProperties().putAll(Minecraft.getMinecraft().getProfileProperties());
         var1.putAll(SkinManager.access$000(this.field_152802_d).getTextures(this.field_152799_a, false));
      }

      Minecraft.getMinecraft().addScheduledTask(new SkinManager$3$1(this, var1));
   }

   public SkinManager$3(SkinManager var1, GameProfile var2, boolean var3, SkinManager$SkinAvailableCallback var4) {
      this.field_152802_d = var1;
      this.field_152799_a = var2;
      this.field_152800_b = var3;
      this.field_152801_c = var4;
      super();
   }
}
