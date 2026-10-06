package net.minecraft.client.multiplayer;

import com.cheatbreaker.client.CheatBreaker;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerList {
   public static Logger logger = LogManager.getLogger();
   public Minecraft mc;
   public List<ServerData> servers = Lists.newArrayList();

   public static void func_147414_b(ServerData var0) {
      ServerList var1 = new ServerList(Minecraft.getMinecraft());
      var1.loadServerList();

      for (int var2 = 0; var2 < var1.countServers(); var2++) {
         ServerData var3 = var1.getServerData(var2);
         if (var3.serverName.equals(var0.serverName) && var3.serverIP.equals(var0.serverIP)) {
            var1.func_147413_a(var2, var0);
            break;
         }
      }

      var1.saveServerList();
   }

   public int countServers() {
      return this.servers.size();
   }

   public void removeServerData(int var1) {
      this.servers.remove(var1);
   }

   public ServerData getServerData(int var1) {
      return this.servers.get(var1);
   }

   public void saveServerList() {
      try {
         NBTTagList var1 = new NBTTagList();

         for (ServerData var3 : this.servers) {
            if (!var3.recoveredField3389) {
               var1.appendTag(var3.getNBTCompound());
            }
         }

         NBTTagCompound var5 = new NBTTagCompound();
         var5.setTag("servers", var1);
         CompressedStreamTools.safeWrite(var5, new File(this.mc.mcDataDir, "servers.dat"));
      } catch (Exception var4) {
         logger.error("Couldn't save server list", var4);
      }
   }

   public ServerList(Minecraft var1) {
      this.mc = var1;
      this.loadServerList();
   }

   public void loadServerList() {
      try {
         this.servers.clear();
         for (String[] var4 : CheatBreaker.getInstance().getGlobalSettings().method_02689()) {
            this.servers.add(new ServerData(true, var4[0], var4[1], false));
         }

         NBTTagCompound var1 = CompressedStreamTools.read(new File(this.mc.mcDataDir, "servers.dat"));
         if (var1 == null) {
            return;
         }

         NBTTagList var2 = var1.getTagList("servers", 10);

         for (int var6 = 0; var6 < var2.tagCount(); var6++) {
            this.servers.add(ServerData.getServerDataFromNBTCompound(var2.getCompoundTagAt(var6)));
         }
      } catch (Exception var5) {
         logger.error("Couldn't load server list", var5);
      }
   }

   public void func_147413_a(int var1, ServerData var2) {
      this.servers.set(var1 - CheatBreaker.getInstance().getGlobalSettings().method_02689().size(), var2);
   }

   public void swapServers(int var1, int var2) {
      ServerData var3 = this.getServerData(var1);
      ServerData var4 = this.getServerData(var2);
      if (!var3.recoveredField3389 && !var4.recoveredField3389) {
         this.servers.set(var1, var4);
         this.servers.set(var2, var3);
         this.saveServerList();
      }
   }

   public boolean moveServer(int from, int to) {
      if (from < 0 || to < 0 || from >= this.servers.size() || to >= this.servers.size() || from == to) return false;
      for (int index = Math.min(from, to); index <= Math.max(from, to); index++) {
         if (this.servers.get(index).recoveredField3389) return false;
      }
      ServerData server = this.servers.remove(from);
      this.servers.add(to, server);
      return true;
   }

   public void addServerData(ServerData var1) {
      this.servers.add(var1);
   }
}
