package io.netty.handler.ssl.util;

import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.netty.util.internal.ThreadLocalRandom;
import java.security.SecureRandom;
import java.util.Random;
import net.minecraft.world.gen.feature.WorldGenTaiga1;

public class ThreadLocalInsecureRandom extends SecureRandom {
   public static final long serialVersionUID = -8209473337192526191L;
   public static SecureRandom INSTANCE = new ThreadLocalInsecureRandom();

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
