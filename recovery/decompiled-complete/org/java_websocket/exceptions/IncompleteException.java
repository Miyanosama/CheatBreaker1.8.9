package org.java_websocket.exceptions;

import io.netty.handler.codec.http.HttpContentEncoder;
import javax.vecmath.Tuple4f;
import net.minecraft.client.audio.GuardianSound;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.optifine.util.CompactArrayList;

public class IncompleteException extends Exception {
   public GuardianSound field_0003;
   public CompactArrayList field_0005;
   public S36PacketSignEditorOpen field_0002;
   public int preferredSize;
   public static long field_0000;
   public Tuple4f field_0001;
   public HttpContentEncoder field_0006;

   public int getPreferredSize() {
      return this.preferredSize;
   }

   public IncompleteException(int var1) {
      this.preferredSize = var1;
   }
}
