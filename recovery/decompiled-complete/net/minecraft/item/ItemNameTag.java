package net.minecraft.item;

import javazoom.jl.converter.WaveFile$WaveFileSample;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stronghold$Door;
import net.optifine.gui.GuiAnimationSettingsOF;
import net.optifine.player.PlayerItemParser;

public class ItemNameTag extends Item {
   public S25PacketBlockBreakAnim field_0002;
   public StructureStrongholdPieces$Stronghold$Door field_0003;
   public ItemFishFood field_0004;
   public GuiAnimationSettingsOF field_0005;
   public WaveFile$WaveFileSample field_0001;
   public PlayerItemParser field_0000;

   public ItemNameTag() {
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   @Override
   public boolean itemInteractionForEntity(ItemStack var1, EntityPlayer var2, EntityLivingBase var3) {
      if (!var1.hasDisplayName()) {
         return false;
      } else if (var3 instanceof EntityLiving) {
         EntityLiving var4 = (EntityLiving)var3;
         var4.a(var1.getDisplayName());
         var4.enablePersistence();
         var1.stackSize--;
         return true;
      } else {
         return super.itemInteractionForEntity(var1, var2, var3);
      }
   }
}
