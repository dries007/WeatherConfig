package net.dries007.weatherconfig.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static net.dries007.weatherconfig.Constants.ASM_MOD_ID;

/**
 * @author Dries007
 */
@IFMLLoadingPlugin.Name(ASM_MOD_ID)
@IFMLLoadingPlugin.SortingIndex(5000)
@IFMLLoadingPlugin.TransformerExclusions("net.dries007.weatherconfig.asm")
public class WeatherConfigCoreMod implements IFMLLoadingPlugin
{
    static final Logger LOGGER = LogManager.getLogger(ASM_MOD_ID);
    public static boolean loaded;

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] {"net.dries007.weatherconfig.asm.WeatherConfigTransformer"};
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {

    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }
}
