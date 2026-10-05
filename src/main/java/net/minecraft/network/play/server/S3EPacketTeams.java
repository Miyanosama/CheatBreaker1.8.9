package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.util.Collection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;

public class S3EPacketTeams implements Packet<INetHandlerPlayClient> {
   public int color;
   public String prefix;
   public String suffix;
   public Collection<String> players;
   public int action;
   public String displayName;
   public int friendlyFlags;
   public String name = "";
   public String nameTagVisibility;

   public S3EPacketTeams(ScorePlayerTeam var1, Collection<String> var2, int var3) {
      this.displayName = "";
      this.prefix = "";
      this.suffix = "";
      this.nameTagVisibility = Team.EnumVisible.ALWAYS.internalName;
      this.color = -1;
      this.players = Lists.newArrayList();
      if (var3 != 3 && var3 != 4) {
         throw new IllegalArgumentException("Method must be join or leave for player constructor");
      } else if (var2 != null && !var2.isEmpty()) {
         this.action = var3;
         this.name = var1.getRegisteredName();
         this.players.addAll(var2);
      } else {
         throw new IllegalArgumentException("Players cannot be null/empty");
      }
   }

   public String getNameTagVisibility() {
      return this.nameTagVisibility;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeString(this.name);
      var1.writeByte(this.action);
      if (this.action == 0 || this.action == 2) {
         var1.writeString(this.displayName);
         var1.writeString(this.prefix);
         var1.writeString(this.suffix);
         var1.writeByte(this.friendlyFlags);
         var1.writeString(this.nameTagVisibility);
         var1.writeByte(this.color);
      }

      if (this.action == 0 || this.action == 3 || this.action == 4) {
         var1.writeVarIntToBuffer(this.players.size());

         for (String var3 : this.players) {
            var1.writeString(var3);
         }
      }
   }

   public S3EPacketTeams(ScorePlayerTeam var1, int var2) {
      this.displayName = "";
      this.prefix = "";
      this.suffix = "";
      this.nameTagVisibility = Team.EnumVisible.ALWAYS.internalName;
      this.color = -1;
      this.players = Lists.newArrayList();
      this.name = var1.getRegisteredName();
      this.action = var2;
      if (var2 == 0 || var2 == 2) {
         this.displayName = var1.getTeamName();
         this.prefix = var1.getColorPrefix();
         this.suffix = var1.getColorSuffix();
         this.friendlyFlags = var1.func_98299_i();
         this.nameTagVisibility = var1.getNameTagVisibility().internalName;
         this.color = var1.getChatFormat().getColorIndex();
      }

      if (var2 == 0) {
         this.players.addAll(var1.getMembershipCollection());
      }
   }

   public int getColor() {
      return this.color;
   }

   public int getAction() {
      return this.action;
   }

   public String getName() {
      return this.name;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public Collection<String> getPlayers() {
      return this.players;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.name = var1.readStringFromBuffer(16);
      this.action = var1.readByte();
      if (this.action == 0 || this.action == 2) {
         this.displayName = var1.readStringFromBuffer(32);
         this.prefix = var1.readStringFromBuffer(16);
         this.suffix = var1.readStringFromBuffer(16);
         this.friendlyFlags = var1.readByte();
         this.nameTagVisibility = var1.readStringFromBuffer(32);
         this.color = var1.readByte();
      }

      if (this.action == 0 || this.action == 3 || this.action == 4) {
         int var2 = var1.readVarIntFromBuffer();

         for (int var3 = 0; var3 < var2; var3++) {
            this.players.add(var1.readStringFromBuffer(40));
         }
      }
   }

   public int getFriendlyFlags() {
      return this.friendlyFlags;
   }

   public S3EPacketTeams() {
      this.displayName = "";
      this.prefix = "";
      this.suffix = "";
      this.nameTagVisibility = Team.EnumVisible.ALWAYS.internalName;
      this.color = -1;
      this.players = Lists.newArrayList();
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleTeams(this);
   }

   public String getSuffix() {
      return this.suffix;
   }

   public String getPrefix() {
      return this.prefix;
   }
}
