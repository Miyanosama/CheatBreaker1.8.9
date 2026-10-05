package net.optifine.shaders;

import java.io.InputStream;
import net.minecraft.client.stream.Metadata;
import net.minecraft.world.World$1;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stones;
import net.optifine.NextTickHashSet;
import org.apache.log4j.helpers.Transform;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.server.WebSocketServer$WebSocketWorker$1;

public class ShaderPackDefault implements IShaderPack {
   public Transform field_0003;
   public HandshakeState field_0006;
   public World$1 field_0002;
   public StructureStrongholdPieces$Stones field_0005;
   public MapGenNetherBridge field_0000;
   public Metadata field_0001;
   public WebSocketServer$WebSocketWorker$1 field_0007;
   public NextTickHashSet field_0004;

   @Override
   public void close() {
   }

   @Override
   public InputStream getResourceAsStream(String var1) {
      return ShaderPackDefault.class.getResourceAsStream(var1);
   }

   @Override
   public boolean hasDirectory(String var1) {
      return false;
   }

   @Override
   public String getName() {
      return "(internal)";
   }
}
