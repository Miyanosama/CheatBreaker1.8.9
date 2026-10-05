package net.minecraft.client.network;

import io.netty.channel.AbstractChannelHandlerContext$WriteAndFlushTask;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.item.ItemSeeds;

public class LanServerDetector$ThreadLanServerFind extends Thread {
   public AbstractChannelHandlerContext$WriteAndFlushTask field_0003;
   public ItemSeeds field_0005;
   public RenderSquid field_0002;
   public MulticastSocket socket;
   public LanServerDetector$LanServerList localServerList;
   public InetAddress broadcastAddress;

   @Override
   public void run() {
      byte[] var1 = new byte[1024];

      while (!this.isInterrupted()) {
         DatagramPacket var2 = new DatagramPacket(var1, var1.length);

         try {
            this.socket.receive(var2);
         } catch (SocketTimeoutException var5) {
            continue;
         } catch (IOException var6) {
            LanServerDetector.access$100().error("Couldn't ping server", var6);
            break;
         }

         String var3 = new String(var2.getData(), var2.getOffset(), var2.getLength());
         LanServerDetector.access$100().debug(var2.getAddress() + ": " + var3);
         this.localServerList.func_77551_a(var3, var2.getAddress());
      }

      try {
         this.socket.leaveGroup(this.broadcastAddress);
      } catch (IOException var4) {
      }

      this.socket.close();
   }

   public LanServerDetector$ThreadLanServerFind(LanServerDetector$LanServerList var1) {
      super("LanServerDetector #" + LanServerDetector.access$000().incrementAndGet());
      this.localServerList = var1;
      this.setDaemon(true);
      this.socket = new MulticastSocket(4445);
      this.broadcastAddress = InetAddress.getByName("224.0.2.60");
      this.socket.setSoTimeout(5000);
      this.socket.joinGroup(this.broadcastAddress);
   }
}
