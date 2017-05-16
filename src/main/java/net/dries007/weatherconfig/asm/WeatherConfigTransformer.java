package net.dries007.weatherconfig.asm;

import com.google.common.base.Objects;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.util.ArrayList;

import static net.dries007.weatherconfig.asm.WeatherConfigCoreMod.LOGGER;
import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.tree.AbstractInsnNode.*;

/**
 * @author Dries007
 */
public class WeatherConfigTransformer implements IClassTransformer
{
    private static final String OWNER = "net/dries007/weatherconfig/ASMInterface";

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        if (transformedName.equals("net.minecraft.world.World")) return transformWorld(basicClass);
        return basicClass;
    }

    private byte[] transformWorld(byte[] bytes)
    {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        LOGGER.info("Found world class...");

        for (MethodNode method : classNode.methods)
        {
            if (!method.name.equals("updateWeatherBody")) continue;

            LOGGER.info("Found updateWeatherBody method...");

            WeatherConfigCoreMod.loaded =
                    replace(method.instructions, new String[] {"func_76061_m", "isThundering"}, "thunder", 12000, 3600, 168000, 12000) &&
                    replace(method.instructions, new String[] {"func_76059_o", "isRaining"}, "rain", 12000, 12000, 168000, 12000);
        }

        if (!WeatherConfigCoreMod.loaded) throw new RuntimeException("World did not have updateWeatherBody");

        LOGGER.info("Patching world done!");

        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }

    @SuppressWarnings("SameParameterValue")
    private static boolean replace(InsnList list, String[] markers, String prefix, int endRnd, int endCnt, int startRnd, int startCnt)
    {
        LOGGER.info("Replacing by {} prefix {}...", markers, prefix);

        ArrayList<MethodInsnNode> markerNodes = new ArrayList<MethodInsnNode>();
        for (int i = 0; i < markers.length; i++)
        {
            markerNodes.add(new MethodInsnNode(INVOKEVIRTUAL, "net/minecraft/world/storage/WorldInfo", markers[i], "()Z", false));
        }

        int i = find(list, 0, markerNodes.toArray(new AbstractInsnNode[markerNodes.size()]));
        if (i == -1) return false;

        LOGGER.info("Found one of {} as {}", markers, ((MethodInsnNode) list.get(i)).name);

        i = find(list, i, new IntInsnNode(SIPUSH, endRnd), new LdcInsnNode(endRnd));
        if (i == -1) return false;
        list.set(list.get(i), new FieldInsnNode(GETSTATIC, OWNER, prefix + "EndRnd", "I"));

        i = find(list, i, new IntInsnNode(SIPUSH, endCnt), new LdcInsnNode(endCnt));
        if (i == -1) return false;
        list.set(list.get(i), new FieldInsnNode(GETSTATIC, OWNER, prefix + "EndCnt", "I"));

        i = find(list, i, new IntInsnNode(SIPUSH, startRnd), new LdcInsnNode(startRnd));
        if (i == -1) return false;
        list.set(list.get(i), new FieldInsnNode(GETSTATIC, OWNER, prefix + "StartRnd", "I"));

        i = find(list, i, new IntInsnNode(SIPUSH, startCnt), new LdcInsnNode(startCnt));
        if (i == -1) return false;
        list.set(list.get(i), new FieldInsnNode(GETSTATIC, OWNER, prefix + "StartCnt", "I"));

        return true;
    }

    @SuppressWarnings("ConstantConditions")
    private static int find(InsnList list, int i, AbstractInsnNode... targets)
    {
        final int size = list.size();
        while (i < size)
        {
            AbstractInsnNode abstractInsnNode = list.get(i++);
            for (AbstractInsnNode target : targets)
            {
                if (abstractInsnNode.getOpcode() != target.getOpcode()) continue;
                switch (abstractInsnNode.getType())
                {
                    case METHOD_INSN:
                    {
                        MethodInsnNode a = ((MethodInsnNode) abstractInsnNode);
                        MethodInsnNode b = ((MethodInsnNode) target);
                        if (a.itf != b.itf) continue;
                        if (!a.owner.equals(b.owner)) continue;
                        if (!a.name.equals(b.name)) continue;
                        if (!a.desc.equals(b.desc)) continue;
                    }
                    break;
                    case INT_INSN:
                    {
                        IntInsnNode a = ((IntInsnNode) abstractInsnNode);
                        IntInsnNode b = ((IntInsnNode) target);
                        if (a.operand != b.operand) continue;
                    }
                    break;
                    case LDC_INSN:
                    {
                        LdcInsnNode a = (LdcInsnNode) abstractInsnNode;
                        LdcInsnNode b = (LdcInsnNode) target;
                        if (!Objects.equal(a.cst, b.cst)) continue;
                    }
                    break;
                }
                return i - 1;
            }
        }
        return -1;
    }
}
