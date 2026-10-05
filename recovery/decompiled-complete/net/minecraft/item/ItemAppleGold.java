package net.minecraft.item;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker13;
import java.util.List;
import net.minecraft.client.stream.ChatController;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderSettings$Factory;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$FitSimpleRoomTopHelper;
import net.optifine.util.CounterInt;
import recovered.unidentified.UnidentifiedClass0943;

public class ItemAppleGold extends ItemFood {
   public ChunkProviderSettings$Factory field_0002;
   public ChatController field_0004;
   public UnidentifiedClass0943 field_0005;
   public EntityGolem field_0006;
   public WebSocketServerHandshaker13 field_0001;
   public CounterInt field_0000;
   public StructureOceanMonumentPieces$FitSimpleRoomTopHelper field_0003;

   @Override
   public boolean hasEffect(ItemStack var1) {
      return var1.getMetadata() > 0;
   }

   @Override
   public void getSubItems(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
   }

   @Override
   public void onFoodEaten(ItemStack var1, World var2, EntityPlayer var3) {
      if (!var2.D) {
         var3.c(new PotionEffect(Potion.absorption.id, 2400, 0));
      }

      if (var1.getMetadata() > 0) {
         if (!var2.D) {
            var3.c(new PotionEffect(Potion.regeneration.id, 600, 4));
            var3.c(new PotionEffect(Potion.resistance.id, 6000, 0));
            var3.c(new PotionEffect(Potion.fireResistance.id, 6000, 0));
         }
      } else {
         super.onFoodEaten(var1, var2, var3);
      }
   }

   @Override
   public EnumRarity getRarity(ItemStack var1) {
      return var1.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
   }

   public ItemAppleGold(int var1, float var2, boolean var3) {
      super(var1, var2, var3);
      this.setHasSubtypes(true);
   }
}
