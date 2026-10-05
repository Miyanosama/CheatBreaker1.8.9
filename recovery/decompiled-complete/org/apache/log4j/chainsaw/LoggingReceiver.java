package org.apache.log4j.chainsaw;

import com.cheatbreaker.client.ui.element.module.ModulePreviewContainer;
import io.netty.buffer.AdvancedLeakAwareByteBuf;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import net.minecraft.block.BlockFarmland;
import net.minecraft.client.renderer.ActiveRenderInfo;
import org.apache.log4j.Logger;

public class LoggingReceiver extends Thread {
   public BlockFarmland field_0003;
   public ModulePreviewContainer field_0006;
   public ActiveRenderInfo field_0002;
   public static Logger LOG = Logger.getLogger(
      LoggingReceiver.class$org$apache$log4j$chainsaw$LoggingReceiver == null
         ? (LoggingReceiver.class$org$apache$log4j$chainsaw$LoggingReceiver = class$("org.apache.log4j.chainsaw.LoggingReceiver"))
         : LoggingReceiver.class$org$apache$log4j$chainsaw$LoggingReceiver
   );
   public AdvancedLeakAwareByteBuf field_0000;
   public ServerSocket mSvrSock;
   public MyTableModel mModel;
   public static Class class$org$apache$log4j$chainsaw$LoggingReceiver;

   public LoggingReceiver(MyTableModel var1, int var2) {
      this.setDaemon(true);
      this.mModel = var1;
      this.mSvrSock = new ServerSocket(var2);
   }

   public static MyTableModel access$100(LoggingReceiver var0) {
      return var0.mModel;
   }

   public void run() {
      LOG.info("Thread started");

      try {
         while (true) {
            LOG.debug("Waiting for a connection");
            Socket var1 = this.mSvrSock.accept();
            LOG.debug("Got a connection from " + var1.getInetAddress().getHostName());
            Thread var2 = new Thread(new LoggingReceiver$Slurper(this, var1));
            var2.setDaemon(true);
            var2.start();
         }
      } catch (IOException var3) {
         LOG.error("Error in accepting connections, stopping.", var3);
      }
   }

   public static Logger access$000() {
      return LOG;
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }
}
