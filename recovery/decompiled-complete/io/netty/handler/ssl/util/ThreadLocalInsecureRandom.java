package io.netty.handler.ssl.util;

import io.netty.handler.codec.socks.SocksCmdResponseDecoder$1;
import io.netty.handler.traffic.GlobalTrafficShapingHandler$ToSend;
import io.netty.util.internal.ThreadLocalRandom;
import java.security.SecureRandom;
import java.util.Random;
import net.minecraft.tileentity.TileEntitySign$2;
import net.minecraft.world.gen.feature.WorldGenTaiga1;

public class ThreadLocalInsecureRandom extends SecureRandom {
   public GlobalTrafficShapingHandler$ToSend __junk1803580607458135552;
   public static SecureRandom INSTANCE = new ThreadLocalInsecureRandom();
   public SocksCmdResponseDecoder$1 __junk1916030627890050447;
   public TileEntitySign$2 __junk3151936857460745768;
   public static long serialVersionUID;
   public WorldGenTaiga1 __junk6778288417189030908;

   @Override
   public double nextDouble() {
      return random().nextDouble();
   }

   @Override
   public void nextBytes(byte[] var1) {
      random().nextBytes(var1);
   }

   public static SecureRandom current() {
      return INSTANCE;
   }

   @Override
   public boolean nextBoolean() {
      return random().nextBoolean();
   }

   @Override
   public void setSeed(long var1) {
   }

   @Override
   public float nextFloat() {
      return random().nextFloat();
   }

   @Override
   public int nextInt() {
      return random().nextInt();
   }

   @Override
   public long nextLong() {
      return random().nextLong();
   }

   @Override
   public byte[] generateSeed(int var1) {
      byte[] var2 = new byte[var1];
      random().nextBytes(var2);
      return var2;
   }

   @Override
   public int nextInt(int var1) {
      return random().nextInt(var1);
   }

   public static Random random() {
      return ThreadLocalRandom.current();
   }

   @Override
   public String getAlgorithm() {
      return "insecure";
   }

   @Override
   public double nextGaussian() {
      return random().nextGaussian();
   }

   @Override
   public void setSeed(byte[] var1) {
   }
}
