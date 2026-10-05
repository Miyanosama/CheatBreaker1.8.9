package io.netty.handler.codec.rtsp;

import io.netty.channel.local.LocalChannel$2;
import io.netty.handler.codec.http.HttpVersion;
import net.minecraft.command.CommandWorldBorder;
import net.minecraft.util.EnumFacing$Plane;
import net.optifine.expr.Token;
import net.optifine.util.IntArray;

public class RtspVersions {
   public static HttpVersion RTSP_1_0 = new HttpVersion("RTSP", 1, 0, true);
   public LocalChannel$2 __junk4982320862201153578;
   public Token __junk5998276450627430736;
   public EnumFacing$Plane __junk5975713327711229879;
   public CommandWorldBorder __junk3767588612602577449;
   public IntArray __junk935694024967339317;

   public static HttpVersion valueOf(String var0) {
      if (var0 == null) {
         throw new NullPointerException("text");
      } else {
         var0 = var0.trim().toUpperCase();
         return "RTSP/1.0".equals(var0) ? RTSP_1_0 : new HttpVersion(var0, true);
      }
   }
}
