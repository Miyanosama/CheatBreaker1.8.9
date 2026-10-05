package net.minecraft.world;

import com.cheatbreaker.client.ui.element.type.custom.KeybindElement;
import java.util.ArrayList;
import net.minecraft.block.BlockEventData;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.network.NetworkSystem$5;
import net.minecraft.util.MessageSerializer2;

public class WorldServer$ServerBlockEventList extends ArrayList<BlockEventData> {
   public NetworkSystem$5 field_0001;
   public KeybindElement field_0003;
   public BlockStateMapper field_0000;
   public MessageSerializer2 field_0002;

   public WorldServer$ServerBlockEventList() {
   }
}
