package org.apache.log4j.lf5.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.client.renderer.block.model.BlockPart$1;
import net.minecraft.util.DamageSource;
import net.optifine.entity.model.ModelAdapterRabbit;
import recovered.unidentified.UnidentifiedClass1534;

public class Resource {
   public String _name;
   public BlockPart$1 field_0004;
   public DamageSource field_0001;
   public UnidentifiedClass1534 field_0003;
   public ModelAdapterRabbit field_0000;

   public InputStreamReader getInputStreamReader() {
      InputStream var1 = ResourceUtils.getResourceAsStream(this, this);
      return var1 == null ? null : new InputStreamReader(var1);
   }

   public String getName() {
      return this._name;
   }

   public Resource() {
   }

   public InputStream getInputStream() {
      return ResourceUtils.getResourceAsStream(this, this);
   }

   public URL getURL() {
      return ResourceUtils.getResourceAsURL(this, this);
   }

   public Resource(String var1) {
      this._name = var1;
   }

   public void setName(String var1) {
      this._name = var1;
   }
}
