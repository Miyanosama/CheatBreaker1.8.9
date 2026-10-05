package com.cheatbreaker.client;

import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.timeout.IdleStateHandler$AllIdleTimeoutTask;
import net.optifine.shaders.DefaultTexture;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$1;

public enum BuildBranch {
   field_0004("beta"),
   field_0007("?"),
   field_0006("unknown"),
   field_0001("master");

   public DefaultTexture field_0003;
   public IdleStateHandler$AllIdleTimeoutTask field_0000;
   public String field_0008;
   public CategoryNodeEditor$1 field_0005;
   public ByteToMessageCodec field_0009;

   public BuildBranch(String var3) {
      this.field_0008 = var3;
   }

   public boolean method_06000(BuildBranch var1) {
      return var1.ordinal() >= this.ordinal();
   }

   public static BuildBranch method_06001(String var0) {
      for (BuildBranch var4 : values()) {
         if (var4.method_05999().equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return field_0006;
   }

   public String method_05999() {
      return this.field_0008;
   }

   public static BuildBranch method_06002(String var0) {
      return Enum.valueOf(BuildBranch.class, var0);
   }
}
