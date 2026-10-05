package org.json;

import java.io.StringWriter;
import org.json.JSONWriter;

public class JSONStringer extends JSONWriter {
   @Override
   public String toString() {
      return this.mode == 'd' ? this.recoveredField1299.toString() : null;
   }

   public JSONStringer() {
      super(new StringWriter());
   }
}
