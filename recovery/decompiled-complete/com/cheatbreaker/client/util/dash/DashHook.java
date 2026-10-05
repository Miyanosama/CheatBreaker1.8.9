package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.client.nethandler.server.PacketCooldown;
import com.cheatbreaker.client.util.cbagent.CBAgentResources;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.vecmath.Matrix4d;
import javazoom.jl.decoder.JavaLayerHook;
import javazoom.jl.decoder.JavaLayerUtils;
import net.minecraft.client.renderer.block.model.BlockPartFace$Deserializer;
import net.minecraft.client.resources.AbstractResourcePack;

public class DashHook implements JavaLayerHook {
   public BlockPartFace$Deserializer field_0001;
   public Matrix4d field_0003;
   public AbstractResourcePack field_0000;
   public PacketCooldown field_0002;

   @Override
   public InputStream getResourceAsStream(String var1) {
      Class<JavaLayerUtils> var2 = JavaLayerUtils.class;
      Object var3 = var2.getResourceAsStream(var1);
      if (var3 == null) {
         String var4 = "javazoom/jl/decoder/" + var1;
         System.out.println("Retrieving: " + var4);
         if (CBAgentResources.existsBytes(var4)) {
            var3 = new ByteArrayInputStream(CBAgentResources.getBytesNative(var4));
         }
      }

      return (InputStream)var3;
   }
}
