package org.apache.log4j.chainsaw;

import io.netty.channel.SimpleChannelInboundHandler;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import net.minecraft.client.particle.EntitySpellParticleFX$AmbientMobFactory;
import net.minecraft.entity.passive.EntityRabbit$AIRaidFarm;
import org.apache.log4j.spi.LoggingEvent;

public class LoggingReceiver$Slurper implements Runnable {
   public LoggingReceiver this$0;
   public SimpleChannelInboundHandler field_0004;
   public Socket mClient;
   public EntityRabbit$AIRaidFarm field_0003;
   public EntitySpellParticleFX$AmbientMobFactory field_0000;

   public LoggingReceiver$Slurper(LoggingReceiver var1, Socket var2) {
      this.this$0 = var1;
      super();
      this.mClient = var2;
   }

   public void run() {
      LoggingReceiver.access$000().debug("Starting to get data");

      try {
         ObjectInputStream var1 = new ObjectInputStream(this.mClient.getInputStream());

         while (true) {
            LoggingEvent var2 = (LoggingEvent)var1.readObject();
            LoggingReceiver.access$100(this.this$0).addEvent(new EventDetails(var2));
         }
      } catch (EOFException var4) {
         LoggingReceiver.access$000().info("Reached EOF, closing connection");
      } catch (SocketException var5) {
         LoggingReceiver.access$000().info("Caught SocketException, closing connection");
      } catch (IOException var6) {
         LoggingReceiver.access$000().warn("Got IOException, closing connection", var6);
      } catch (ClassNotFoundException var7) {
         LoggingReceiver.access$000().warn("Got ClassNotFoundException, closing connection", var7);
      }

      try {
         this.mClient.close();
      } catch (IOException var3) {
         LoggingReceiver.access$000().warn("Error closing connection", var3);
      }
   }
}
