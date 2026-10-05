package recovered.unidentified;

import java.io.StringWriter;
import org.json.JSONWriter;

public class UnidentifiedClass1099 extends JSONWriter {
   @Override
   public String toString() {
      return this.mode == 'd' ? this.field_0004.toString() : null;
   }

   public UnidentifiedClass1099() {
      super(new StringWriter());
   }
}
