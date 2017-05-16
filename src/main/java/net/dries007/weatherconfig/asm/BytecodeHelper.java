/*
package net.dries007.weatherconfig.asm;

import com.google.common.base.Throwables;
import net.dries007.weatherconfig.ASMInterface;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

/**
 * @see net.minecraft.world.World#updateWeatherBody()
 *
 * @author Dries007
 */
/*
@SuppressWarnings("all")
public class BytecodeHelper
{
    static
    {
        Throwables.propagate(new RuntimeException("This class should never be loaded."));
    }
    /*
    if (this.worldInfo.isThundering())
    {
        this.worldInfo.setThunderTime(this.rand.nextInt(12000) + 3600);
    }
    else
    {
        this.worldInfo.setThunderTime(this.rand.nextInt(168000) + 12000);
    }

   L16
    LINENUMBER 2798 L16
    ALOAD 0
    GETFIELD net/minecraft/world/World.worldInfo : Lnet/minecraft/world/storage/WorldInfo;
    INVOKEVIRTUAL net/minecraft/world/storage/WorldInfo.isThundering ()Z
    IFEQ L17
   L18
    LINENUMBER 2800 L18
    ALOAD 0
    GETFIELD net/minecraft/world/World.worldInfo : Lnet/minecraft/world/storage/WorldInfo;
    ALOAD 0
    GETFIELD net/minecraft/world/World.rand : Ljava/util/Random;
    SIPUSH 12000
    INVOKEVIRTUAL java/util/Random.nextInt (I)I
    SIPUSH 3600
    IADD
    INVOKEVIRTUAL net/minecraft/world/storage/WorldInfo.setThunderTime (I)V
    GOTO L19
   L17
    LINENUMBER 2804 L17
   FRAME APPEND [I]
    ALOAD 0
    GETFIELD net/minecraft/world/World.worldInfo : Lnet/minecraft/world/storage/WorldInfo;
    ALOAD 0
    GETFIELD net/minecraft/world/World.rand : Ljava/util/Random;
    LDC 168000
    INVOKEVIRTUAL java/util/Random.nextInt (I)I
    SIPUSH 12000
    IADD
    INVOKEVIRTUAL net/minecraft/world/storage/WorldInfo.setThunderTime (I)V
    GOTO L19

    ---------------------------------------------------------------------------------------------

    if (this.worldInfo.isRaining())
    {
        this.worldInfo.setRainTime(this.rand.nextInt(12000) + 12000);
    }
    else
    {
        this.worldInfo.setRainTime(this.rand.nextInt(168000) + 12000);
    }

   L32
    LINENUMBER 2834 L32
    ALOAD 0
    GETFIELD net/minecraft/world/World.worldInfo : Lnet/minecraft/world/storage/WorldInfo;
    INVOKEVIRTUAL net/minecraft/world/storage/WorldInfo.isRaining ()Z
    IFEQ L33
   L34
    LINENUMBER 2836 L34
    ALOAD 0
    GETFIELD net/minecraft/world/World.worldInfo : Lnet/minecraft/world/storage/WorldInfo;
    ALOAD 0
    GETFIELD net/minecraft/world/World.rand : Ljava/util/Random;
    SIPUSH 12000
    INVOKEVIRTUAL java/util/Random.nextInt (I)I
    SIPUSH 12000
    IADD
    INVOKEVIRTUAL net/minecraft/world/storage/WorldInfo.setRainTime (I)V
    GOTO L35
   L33
    LINENUMBER 2840 L33
   FRAME APPEND [I]
    ALOAD 0
    GETFIELD net/minecraft/world/World.worldInfo : Lnet/minecraft/world/storage/WorldInfo;
    ALOAD 0
    GETFIELD net/minecraft/world/World.rand : Ljava/util/Random;
    LDC 168000
    INVOKEVIRTUAL java/util/Random.nextInt (I)I
    SIPUSH 12000
    IADD
    INVOKEVIRTUAL net/minecraft/world/storage/WorldInfo.setRainTime (I)V
    GOTO L35
   L31
    LINENUMBER 2845 L31
   FRAME SAME
    IINC 3 -1
   L36
    LINENUMBER 2846 L36
    ALOAD 0
    GETFIELD net/minecraft/world/World.worldInfo : Lnet/minecraft/world/storage/WorldInfo;
    ILOAD 3
    INVOKEVIRTUAL net/minecraft/world/storage/WorldInfo.setRainTime (I)V
    *

    private abstract class FakeWorld extends World
    {
        protected FakeWorld(ISaveHandler saveHandlerIn, WorldInfo info, WorldProvider providerIn, Profiler profilerIn, boolean client)
        {
            super(saveHandlerIn, info, providerIn, profilerIn, client);
        }

        @Override
        public void updateWeatherBody()
        {
            if (this.worldInfo.isThundering())
            {
                this.worldInfo.setThunderTime(this.rand.nextInt(ASMInterface.thunderEndRnd) + ASMInterface.thunderEndCnt);
            }
            else
            {
                this.worldInfo.setThunderTime(this.rand.nextInt(ASMInterface.thunderStartRnd) + ASMInterface.thunderStartCnt);
            }

            if (this.worldInfo.isRaining())
            {
                this.worldInfo.setRainTime(this.rand.nextInt(ASMInterface.rainEndRnd) + ASMInterface.rainEndCnt);
            }
            else
            {
                this.worldInfo.setRainTime(this.rand.nextInt(ASMInterface.rainStartRnd) + ASMInterface.rainStartCnt);
            }
        }
    }
}
*/