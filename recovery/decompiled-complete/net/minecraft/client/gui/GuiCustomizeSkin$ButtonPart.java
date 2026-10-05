package net.minecraft.client.gui;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesToDoubleTask;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.entity.player.EnumPlayerModelParts;
import org.apache.log4j.helpers.PatternParser$DatePatternConverter;

public class GuiCustomizeSkin$ButtonPart extends GuiButton {
   public ConcurrentHashMapV8$MapReduceEntriesToDoubleTask field_0002;
   public PatternParser$DatePatternConverter field_0004;
   public ResourcePackListEntry field_0001;
   public EnumPlayerModelParts playerModelParts;

   public GuiCustomizeSkin$ButtonPart(GuiCustomizeSkin var1, int var2, int var3, int var4, int var5, int var6, EnumPlayerModelParts var7) {
      this.this$0 = var1;
      super(var2, var3, var4, var5, var6, GuiCustomizeSkin.access$200(var1, var7));
      this.playerModelParts = var7;
   }
}
