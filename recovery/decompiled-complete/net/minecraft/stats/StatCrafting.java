package net.minecraft.stats;

import junit.swingui.TestRunner$12;
import net.minecraft.command.PlayerSelector$4;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.IChatComponent;

public class StatCrafting extends StatBase {
   public RecipesArmorDyes field_0002;
   public TestRunner$12 field_0004;
   public Item field_150960_a;
   public PlayerSelector$4 field_0003;
   public S40PacketDisconnect field_0000;

   public StatCrafting(String var1, String var2, IChatComponent var3, Item var4) {
      super(var1 + var2, var3);
      this.field_150960_a = var4;
      int var5 = Item.getIdFromItem(var4);
      if (var5 != 0) {
         IScoreObjectiveCriteria.INSTANCES.put(var1 + var5, this.getCriteria());
      }
   }

   public Item func_150959_a() {
      return this.field_150960_a;
   }
}
