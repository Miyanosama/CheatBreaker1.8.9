package javazoom.jl.converter;

import com.cheatbreaker.client.module.type.notifications.CBNotificationsModule;
import io.netty.handler.codec.socks.SocksRequestType;
import javazoom.jl.decoder.Obuffer;
import net.optifine.expr.FunctionType;
import org.apache.log4j.helpers.NullEnumeration;

public class WaveFileObuffer extends Obuffer {
   public short[] buffer;
   public NullEnumeration __junk4254912432563185486;
   public short[] bufferp;
   public WaveFile outWave;
   public CBNotificationsModule __junk6519194333278663772;
   public FunctionType __junk1254476320988370662;
   public short[] myBuffer = new short[2];
   public int channels;
   public SocksRequestType __junk1099252624466799981;

   @Override
   public void clear_buffer() {
   }

   @Override
   public void write_buffer(int var1) {
      boolean var2 = false;
      int var3 = 0;
      var3 = this.outWave.WriteData(this.buffer, this.bufferp[0]);

      for (int var4 = 0; var4 < this.channels; var4++) {
         this.bufferp[var4] = (short)var4;
      }
   }

   @Override
   public void close() {
      this.outWave.Close();
   }

   public WaveFileObuffer(int var1, int var2, String var3) {
      if (var3 == null) {
         throw new NullPointerException("FileName");
      } else {
         this.buffer = new short[2304];
         this.bufferp = new short[2];
         this.channels = var1;

         for (int var4 = 0; var4 < var1; var4++) {
            this.bufferp[var4] = (short)var4;
         }

         this.outWave = new WaveFile();
         int var5 = this.outWave.OpenForWrite(var3, var2, (short)16, (short)this.channels);
      }
   }

   @Override
   public void append(int var1, short var2) {
      this.buffer[this.bufferp[var1]] = var2;
      this.bufferp[var1] = (short)(this.bufferp[var1] + this.channels);
   }

   @Override
   public void set_stop_flag() {
   }
}
