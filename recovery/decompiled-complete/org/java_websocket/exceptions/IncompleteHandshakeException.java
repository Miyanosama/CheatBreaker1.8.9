package org.java_websocket.exceptions;

import com.cheatbreaker.client.module.type.NickHiderModule;
import junit.framework.TestSuite;
import net.minecraft.block.BlockSapling$1;

public class IncompleteHandshakeException extends RuntimeException {
   public TestSuite field_0002;
   public BlockSapling$1 field_0004;
   public int preferredSize;
   public NickHiderModule field_0003;
   public static long field_0000;

   public int getPreferredSize() {
      return this.preferredSize;
   }

   public IncompleteHandshakeException(int var1) {
      this.preferredSize = var1;
   }

   public IncompleteHandshakeException() {
      this.preferredSize = 0;
   }
}
