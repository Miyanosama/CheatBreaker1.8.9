package net.minecraft.client.network;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanServerDetector {
   public static AtomicInteger field_148551_a = new AtomicInteger(0);
   public static Logger logger = LogManager.getLogger();

   public static class LanServer {
      public String lanServerMotd;
      public String lanServerIpPort;
      public long timeLastSeen;

      public String getServerMotd() {
         return this.lanServerMotd;
      }

      public void updateLastSeen() {
         this.timeLastSeen = Minecraft.getSystemTime();
      }

      public String getServerIpPort() {
         return this.lanServerIpPort;
      }

      public LanServer(String var1, String var2) {
         this.lanServerMotd = var1;
         this.lanServerIpPort = var2;
         this.timeLastSeen = Minecraft.getSystemTime();
      }
   }

   public static class LanServerList {
      public List<LanServerDetector.LanServer> listOfLanServers = Lists.newArrayList();
      public boolean wasUpdated;

      public synchronized void setWasNotUpdated() {
         this.wasUpdated = false;
      }

      public synchronized List<LanServerDetector.LanServer> getLanServers() {
         return Collections.unmodifiableList(this.listOfLanServers);
      }

      public synchronized boolean getWasUpdated() {
         return this.wasUpdated;
      }

      public synchronized void func_77551_a(String var1, InetAddress var2) {
         String var3 = ThreadLanServerPing.method_24020(var1);
         String var4 = ThreadLanServerPing.method_24018(var1);
         if (var4 != null) {
            var4 = var2.getHostAddress() + ":" + var4;
            boolean var5 = false;

            for (LanServerDetector.LanServer var7 : this.listOfLanServers) {
               if (var7.getServerIpPort().equals(var4)) {
                  var7.updateLastSeen();
                  var5 = true;
                  break;
               }
            }

            if (!var5) {
               this.listOfLanServers.add(new LanServerDetector.LanServer(var3, var4));
               this.wasUpdated = true;
            }
         }
      }
   }

   public static class ThreadLanServerFind extends Thread {
      public MulticastSocket socket;
      public LanServerDetector.LanServerList localServerList;
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
               LanServerDetector.logger.error("Couldn't ping server", var6);
               break;
            }

            String var3 = new String(var2.getData(), var2.getOffset(), var2.getLength());
            LanServerDetector.logger.debug(var2.getAddress() + ": " + var3);
            this.localServerList.func_77551_a(var3, var2.getAddress());
         }

         try {
            this.socket.leaveGroup(this.broadcastAddress);
         } catch (IOException var4) {
         }

         this.socket.close();
      }

      public ThreadLanServerFind(LanServerDetector.LanServerList var1) throws java.io.IOException {
         super("LanServerDetector #" + LanServerDetector.field_148551_a.incrementAndGet());
         this.localServerList = var1;
         this.setDaemon(true);
         this.socket = new MulticastSocket(4445);
         this.broadcastAddress = InetAddress.getByName("224.0.2.60");
         this.socket.setSoTimeout(5000);
         this.socket.joinGroup(this.broadcastAddress);
      }
   }
}
