package net.dries007.weatherconfig;

import net.dries007.weatherconfig.asm.WeatherConfigCoreMod;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import static net.dries007.weatherconfig.ASMInterface.*;
import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, guiFactory = "net.dries007.weatherconfig.client.ConfigGui")
public class WeatherConfig
{
    @Mod.Instance(Constants.MOD_ID)
    public static WeatherConfig I;

    private Logger logger;
    private Configuration config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());
        updateConfig();

        // Force World class to load, so the transformer does its thing
        //noinspection unused
        double d = World.MAX_ENTITY_RADIUS;

        if (!WeatherConfigCoreMod.loaded)
        {
            logger.fatal("The ASM transformer did not load.");
            throw new RuntimeException("The ASM transformer did not load.");
        }

        logger.info("We should be good to go!");
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Constants.MOD_ID)) updateConfig();
    }

    private void updateConfig()
    {
        config.addCustomCategoryComment(CATEGORY_GENERAL, "How the numbers work:\n" +
                "The RND part is used as upper bound of a new random number, CNT is added straight on.\n" +
                "The START numbers are used to get the next rain/thunder when it's currently not raining/thundering\n" +
                "The END numbers are used to get the next rain/thunder when it currently is raining/thundering");

        rainStartRnd = config.getInt("rainStartRnd", CATEGORY_GENERAL, 168000, 1, Integer.MAX_VALUE, "Max for random added to rainStartCnt to pick when the next rain will start");
        rainStartCnt = config.getInt("rainStartCnt", CATEGORY_GENERAL, 12000, 1, Integer.MAX_VALUE, "Constant added to rainStartRnd to pick when the next rain will start");

        rainEndRnd = config.getInt("rainEndRnd", CATEGORY_GENERAL, 12000, 1, Integer.MAX_VALUE, "Max for random added to rainEndCnt to pick when the rain will end");
        rainEndCnt = config.getInt("rainEndCnt", CATEGORY_GENERAL, 12000, 1, Integer.MAX_VALUE, "Constant added to rainEndRnd to pick when the rain will end");

        thunderStartRnd = config.getInt("thunderStartRnd", CATEGORY_GENERAL, 168000, 1, Integer.MAX_VALUE, "Max for random added to thunderStartCnt to pick when the next thunder will start");
        thunderStartCnt = config.getInt("thunderStartCnt", CATEGORY_GENERAL, 12000, 1, Integer.MAX_VALUE, "Constant added to thunderStartRnd to pick when the next thunder will start");

        thunderEndRnd = config.getInt("thunderEndRnd", CATEGORY_GENERAL, 12000, 1, Integer.MAX_VALUE, "Max for random added to thunderEndCnt to pick when the thunder will end");
        thunderEndCnt = config.getInt("thunderEndCnt", CATEGORY_GENERAL, 3600, 1, Integer.MAX_VALUE, "Constant added to thunderEndRnd to pick when the thunder will end");

        logger.info("Config updated!");

        if (config.hasChanged()) config.save();
    }

    public Configuration getConfig()
    {
        return config;
    }
}
