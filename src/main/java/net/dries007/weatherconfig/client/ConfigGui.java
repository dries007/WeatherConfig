package net.dries007.weatherconfig.client;

import net.dries007.weatherconfig.Constants;
import net.dries007.weatherconfig.WeatherConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Dries007
 */
@SuppressWarnings("unused")
public class ConfigGui implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft minecraftInstance)
    {

    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass()
    {
        return ForgeConfigGui.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
    {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element)
    {
        return null;
    }

    public static class ForgeConfigGui extends GuiConfig
    {
        public ForgeConfigGui(GuiScreen parent)
        {
            super(parent, getConfigElements(), Constants.MOD_ID, false, false, Constants.MOD_NAME);
        }

        private static List<IConfigElement> getConfigElements()
        {
            List<IConfigElement> elements = new ArrayList<IConfigElement>();

            Configuration config = WeatherConfig.I.getConfig();

            for (String cat : config.getCategoryNames())
            {
                elements.add(new ConfigElement(config.getCategory(cat)));
            }

            return elements;
        }
    }
}
