package recovered.unidentified;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import net.minecraft.block.BlockSeaLantern;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$Penthouse;
import net.optifine.entity.model.anim.ModelVariableUpdater;

public class UnidentifiedClass4110 extends Packet {
   public String field_0001;
   public int field_0002 = -13421569;
   public BlockSeaLantern field_0010;
   public boolean field_0007;
   public String field_0003;
   public double field_0011;
   public double field_0000;
   public double field_0005;
   public StructureOceanMonumentPieces$Penthouse field_0006;
   public ModelVariableUpdater field_0004;
   public boolean field_0008;
   public double field_0009;

   @Override
   public void read(ByteBufWrapper var1) {
      this.field_0001 = var1.readOptional(var1::readString);
      this.field_0003 = var1.readString();
      this.field_0008 = var1.buf().readBoolean();
      this.field_0007 = var1.buf().readBoolean();
      this.field_0002 = var1.buf().readInt();
      this.field_0009 = var1.buf().readDouble();
      this.field_0000 = var1.buf().readDouble();
      this.field_0011 = var1.buf().readDouble();
      this.field_0005 = var1.buf().readDouble();
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeOptional(this.field_0001, var1::writeString);
      var1.writeString(this.field_0003);
      var1.buf().writeBoolean(this.field_0008);
      var1.buf().writeBoolean(this.field_0007);
      var1.buf().writeInt(this.field_0002);
      var1.buf().writeDouble(this.field_0009);
      var1.buf().writeDouble(this.field_0000);
      var1.buf().writeDouble(this.field_0011);
      var1.buf().writeDouble(this.field_0005);
   }

   public double method_24759() {
      return this.field_0009;
   }

   public String method_24756() {
      return this.field_0003;
   }

   public boolean method_24758() {
      return this.field_0007;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11463(this);
   }

   public String method_24753() {
      return this.field_0001;
   }

   public int method_24754() {
      return this.field_0002;
   }

   public double method_24760() {
      return this.field_0005;
   }

   public UnidentifiedClass4110(String var1, String var2, boolean var3, boolean var4, int var5, double var6, double var8, double var10, double var12) {
      this.field_0001 = var1;
      this.field_0003 = var2;
      this.field_0008 = var3;
      this.field_0007 = var4;
      this.field_0002 = var5;
      this.field_0009 = var6;
      this.field_0000 = var8;
      this.field_0011 = var10;
      this.field_0005 = var12;
   }

   public double method_24757() {
      return this.field_0000;
   }

   public UnidentifiedClass4110() {
   }

   public double method_24755() {
      return this.field_0011;
   }

   public boolean method_24761() {
      return this.field_0008;
   }
}
