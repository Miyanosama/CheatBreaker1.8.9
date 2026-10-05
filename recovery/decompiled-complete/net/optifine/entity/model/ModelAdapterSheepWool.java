package net.optifine.entity.model;

import java.util.Iterator;
import java.util.List;
import javazoom.jl.converter.RiffFile$RiffChunkHeader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.crash.CrashReportCategory$1;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.src.Config;

public class ModelAdapterSheepWool extends ModelAdapterQuadruped {
   public RiffFile$RiffChunkHeader field_0001;
   public LayerMooshroomMushroom field_0002;
   public CrashReportCategory$1 field_0000;

   @Override
   public ModelBase makeModel() {
      return new ModelSheep1();
   }

   public ModelAdapterSheepWool() {
      super(EntitySheep.class, "sheep_wool", 0.7F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      Object var4 = var3.getEntityRenderMap().get(EntitySheep.class);
      if (!(var4 instanceof RenderSheep)) {
         Config.warn("Not a RenderSheep: " + var4);
         return null;
      } else {
         if (((Render)var4).getEntityClass() == null) {
            var4 = new RenderSheep(var3, new ModelSheep2(), 0.7F);
         }

         RenderSheep var5 = (RenderSheep)var4;
         List var6 = var5.getLayerRenderers();
         Iterator var7 = var6.iterator();

         while (var7.hasNext()) {
            LayerRenderer var8 = (LayerRenderer)var7.next();
            if (var8 instanceof LayerSheepWool) {
               var7.remove();
            }
         }

         LayerSheepWool var9 = new LayerSheepWool(var5);
         var9.sheepModel = (ModelSheep1)var1;
         var5.a(var9);
         return var5;
      }
   }
}
