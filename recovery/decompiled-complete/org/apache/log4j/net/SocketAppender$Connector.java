package org.apache.log4j.net;

import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$SeekAheadNoBackArrayException;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$TreeBin;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import net.minecraft.client.Minecraft$7;
import net.minecraft.client.audio.SoundManager$2;
import net.minecraft.realms.DisconnectedRealmsScreen;
import net.minecraft.tileentity.TileEntitySkull;
import net.optifine.entity.model.ModelAdapterBoat;
import net.optifine.shaders.config.ShaderOptionVariable;
import org.apache.log4j.helpers.LogLog;

public class SocketAppender$Connector extends Thread {
   public ModelAdapterBoat field_0004;
   public boolean interrupted;
   public Minecraft$7 field_0003;
   public ConcurrentHashMapV8$TreeBin field_0006;
   public SocketAppender this$0;
   public SoundManager$2 field_0001;
   public ShaderOptionVariable field_0008;
   public TileEntitySkull field_0005;
   public HttpPostBodyUtil$SeekAheadNoBackArrayException field_0002;
   public DisconnectedRealmsScreen field_0009;

   public void run() {
      while (!this.interrupted) {
         try {
            sleep(this.this$0.reconnectionDelay);
            LogLog.debug("Attempting connection to " + this.this$0.address.getHostName());
            Socket var1 = new Socket(this.this$0.address, this.this$0.port);
            synchronized (this) {
               this.this$0.oos = new ObjectOutputStream(var1.getOutputStream());
               SocketAppender.access$002(this.this$0, null);
               LogLog.debug("Connection established. Exiting connector thread.");
               break;
            }
         } catch (InterruptedException var5) {
            LogLog.debug("Connector interrupted. Leaving loop.");
            return;
         } catch (ConnectException var6) {
            LogLog.debug("Remote host " + this.this$0.address.getHostName() + " refused connection.");
         } catch (IOException var7) {
            if (var7 instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            LogLog.debug("Could not connect to " + this.this$0.address.getHostName() + ". Exception is " + var7);
         }
      }
   }

   public SocketAppender$Connector(SocketAppender var1) {
      this.this$0 = var1;
      super();
      this.interrupted = false;
   }
}
