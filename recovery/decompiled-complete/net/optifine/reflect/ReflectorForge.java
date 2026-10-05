package net.optifine.reflect;

import io.netty.handler.codec.http.QueryStringEncoder;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import org.apache.log4j.NDC;
import org.apache.log4j.lf5.viewer.LogTableRowRenderer;
import org.apache.log4j.varia.HUPNode;

public class ReflectorForge {
   public HUPNode field_0003;
   public BlockStoneBrick field_0006;
   public QueryStringEncoder field_0002;
   public static Object EVENT_RESULT_DEFAULT = Reflector.getFieldValue(Reflector.Event_Result_DEFAULT);
   public NDC field_0000;
   public static Object EVENT_RESULT_DENY = Reflector.getFieldValue(Reflector.Event_Result_DENY);
   public LogTableRowRenderer field_0007;
   public static Object EVENT_RESULT_ALLOW = Reflector.getFieldValue(Reflector.Event_Result_ALLOW);

   public static MapData getMapData(ItemMap var0, ItemStack var1, World var2) {
      return Reflector.ForgeHooksClient.exists() ? ((ItemMap)var1.getItem()).getMapData(var1, var2) : var0.getMapData(var1, var2);
   }

   public static boolean renderFirstPersonHand(RenderGlobal var0, float var1, int var2) {
      return !Reflector.ForgeHooksClient_renderFirstPersonHand.exists()
         ? false
         : Reflector.callBoolean(Reflector.ForgeHooksClient_renderFirstPersonHand, var0, var1, var2);
   }

   public static boolean blockHasTileEntity(IBlockState var0) {
      Block var1 = var0.getBlock();
      return !Reflector.ForgeBlock_hasTileEntity.exists() ? var1.hasTileEntity() : Reflector.callBoolean(var1, Reflector.ForgeBlock_hasTileEntity, var0);
   }

   public static void putLaunchBlackboard(String var0, Object var1) {
      Map var2 = (Map)Reflector.getFieldValue(Reflector.Launch_blackboard);
      if (var2 != null) {
         var2.put(var0, var1);
      }
   }

   public static boolean doSpecialSpawn(EntityLiving var0, World var1, float var2, int var3, float var4) {
      return Reflector.ForgeEventFactory_doSpecialSpawn.exists()
         ? Reflector.callBoolean(Reflector.ForgeEventFactory_doSpecialSpawn, var0, var1, var2, var3, var4)
         : false;
   }

   public static InputStream getOptiFineResourceStream(String var0) {
      if (!Reflector.OptiFineClassTransformer_instance.exists()) {
         return null;
      } else {
         Object var1 = Reflector.getFieldValue(Reflector.OptiFineClassTransformer_instance);
         if (var1 == null) {
            return null;
         } else {
            if (var0.startsWith("/")) {
               var0 = var0.substring(1);
            }

            byte[] var2 = (byte[])Reflector.call(var1, Reflector.OptiFineClassTransformer_getOptiFineResource, var0);
            return var2 == null ? null : new ByteArrayInputStream(var2);
         }
      }
   }

   public static void FMLClientHandler_trackBrokenTexture(ResourceLocation var0, String var1) {
      if (!Reflector.FMLClientHandler_trackBrokenTexture.exists()) {
         Object var2 = Reflector.call(Reflector.FMLClientHandler_instance);
         Reflector.call(var2, Reflector.FMLClientHandler_trackBrokenTexture, var0, var1);
      }
   }

   public static boolean canEntitySpawn(EntityLiving var0, World var1, float var2, float var3, float var4) {
      Object var5 = Reflector.call(Reflector.ForgeEventFactory_canEntitySpawn, var0, var1, var2, var3, var4);
      return var5 == EVENT_RESULT_ALLOW || var5 == EVENT_RESULT_DEFAULT && var0.getCanSpawnHere() && var0.isNotColliding();
   }

   public static String[] getForgeModIds() {
      if (!Reflector.Loader.exists()) {
         return new String[0];
      } else {
         Object var0 = Reflector.call(Reflector.Loader_instance);
         List var1 = (List)Reflector.call(var0, Reflector.Loader_getActiveModList);
         if (var1 == null) {
            return new String[0];
         } else {
            ArrayList var2 = new ArrayList();

            for (Object var4 : var1) {
               if (Reflector.ModContainer.isInstance(var4)) {
                  String var5 = Reflector.callString(var4, Reflector.ModContainer_getModId);
                  if (var5 != null) {
                     var2.add(var5);
                  }
               }
            }

            return var2.toArray(new String[var2.size()]);
         }
      }
   }

   public static boolean isItemDamaged(ItemStack var0) {
      return !Reflector.ForgeItem_showDurabilityBar.exists()
         ? var0.isItemDamaged()
         : Reflector.callBoolean(var0.getItem(), Reflector.ForgeItem_showDurabilityBar, var0);
   }

   public static boolean armorHasOverlay(ItemArmor var0, ItemStack var1) {
      int var2 = var0.getColor(var1);
      return var2 != -1;
   }

   public static void FMLClientHandler_trackMissingTexture(ResourceLocation var0) {
      if (!Reflector.FMLClientHandler_trackMissingTexture.exists()) {
         Object var1 = Reflector.call(Reflector.FMLClientHandler_instance);
         Reflector.call(var1, Reflector.FMLClientHandler_trackMissingTexture, var0);
      }
   }
}
