package net.minecraft.block.state;

import java.util.Comparator;
import net.minecraft.block.BlockPrismarine$EnumType;
import net.minecraft.block.material.MaterialLogic;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.FaceBakery$1;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.ChatComponentTranslationFormatException;
import net.optifine.shaders.config.ShaderOptionVariable;
import org.apache.log4j.net.SocketHubAppender;
import org.java_websocket.exceptions.NotSendableException;

public class BlockState$2 implements Comparator<IProperty> {
   public NotSendableException field_0004;
   public ChatComponentTranslationFormatException field_0007;
   public MaterialLogic field_0003;
   public FaceBakery$1 field_0006;
   public PathFinder field_0000;
   public ShaderOptionVariable field_0008;
   public BlockPrismarine$EnumType field_0005;
   public SocketHubAppender field_0002;

   public int compare(IProperty var1, IProperty var2) {
      return var1.getName().compareTo(var2.getName());
   }

   public BlockState$2(BlockState var1) {
      this.field_0001 = var1;
      super();
   }
}
