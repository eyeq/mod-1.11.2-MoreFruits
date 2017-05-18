package eyeq.morefruits;

import eyeq.morefruits.event.MoreFruitsEventHandler;
import eyeq.morefruits.item.*;
import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.oredict.CategoryTypes;
import eyeq.util.oredict.UOreDictionary;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.io.File;

import static eyeq.morefruits.MoreFruits.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class MoreFruits {
    public static final String MOD_ID = "eyeq_morefruits";

    @Mod.Instance(MOD_ID)
    public static MoreFruits instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static Item grape;
    public static Item peach;
    public static Item pineapple;
    public static Item persimmon;
    public static Item pear;

    public static Item grapeGold;
    public static Item peachGold;
    public static Item pineappleGold;
    public static Item persimmonGold;
    public static Item pearGold;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new MoreFruitsEventHandler());
        addRecipes();
        if(event.getSide().isServer()) {
            return;
        }
        renderItemModels();
        createFiles();
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        grape = new ItemFood(4, 0.3F, false).setUnlocalizedName("grape");
        peach = new ItemFood(4, 0.3F, false).setUnlocalizedName("peach");
        pineapple = new ItemFood(4, 0.3F, false).setUnlocalizedName("pineapple");
        persimmon = new ItemFood(4, 0.3F, false).setUnlocalizedName("persimmon");
        pear = new ItemFood(4, 0.3F, false).setUnlocalizedName("pear");
        grapeGold = new ItemGrapeGold(4, 1.2F, false).setUnlocalizedName("grapeGold");
        peachGold = new ItemPeachGold(4, 1.2F, false).setUnlocalizedName("peachGold");
        pineappleGold = new ItemPineappleGold(4, 1.2F, false).setUnlocalizedName("pineappleGold");
        persimmonGold = new ItemPersimmonGold(4, 1.2F, false).setUnlocalizedName("persimmonGold");
        pearGold = new ItemPearGold(4, 1.2F, false).setUnlocalizedName("pearGold");

        GameRegistry.register(grape, resource.createResourceLocation("grape"));
        GameRegistry.register(peach, resource.createResourceLocation("peach"));
        GameRegistry.register(pineapple, resource.createResourceLocation("pineapple"));
        GameRegistry.register(persimmon, resource.createResourceLocation("persimmon"));
        GameRegistry.register(pear, resource.createResourceLocation("pear"));
        GameRegistry.register(grapeGold, resource.createResourceLocation("grape_golden"));
        GameRegistry.register(peachGold, resource.createResourceLocation("peach_golden"));
        GameRegistry.register(pineappleGold, resource.createResourceLocation("pineapple_golden"));
        GameRegistry.register(persimmonGold, resource.createResourceLocation("persimmon_golden"));
        GameRegistry.register(pearGold, resource.createResourceLocation("pear_golden"));

        UOreDictionary.registerOre(CategoryTypes.PREFIX_FRUIT, "grape", grape);
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FRUIT, "peach", peach);
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FRUIT, "pineapple", pineapple);
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FRUIT, "persimmon", persimmon);
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FRUIT, "pear", pear);
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FOOD_GOLD, "grape", new ItemStack(grapeGold, 1, 1));
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FOOD_GOLD, "peach", new ItemStack(peachGold, 1, 1));
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FOOD_GOLD, "pineapple", new ItemStack(pineappleGold, 1, 1));
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FOOD_GOLD, "persimmon", new ItemStack(persimmonGold, 1, 1));
        UOreDictionary.registerOre(CategoryTypes.PREFIX_FOOD_GOLD, "pear", new ItemStack(pearGold, 1, 1));
    }


    public static void addRecipeGolden(Item output, Item input) {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(output, 1, 0),
                "XXX", "XYX", "XXX",
                'X', UOreDictionary.OREDICT_GOLD_INGOT,
                'Y', input));
    }

    public static void addRecipes() {
        addRecipeGolden(grapeGold, grape);
        addRecipeGolden(peachGold, peach);
        addRecipeGolden(pineappleGold, pineapple);
        addRecipeGolden(persimmonGold, persimmon);
        addRecipeGolden(pearGold, pear);
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        UModelLoader.setCustomModelResourceLocation(grape);
        UModelLoader.setCustomModelResourceLocation(peach);
        UModelLoader.setCustomModelResourceLocation(pineapple);
        UModelLoader.setCustomModelResourceLocation(persimmon);
        UModelLoader.setCustomModelResourceLocation(pear);
        UModelLoader.setCustomModelResourceLocation(grapeGold);
        UModelLoader.setCustomModelResourceLocation(grapeGold, 1);
        UModelLoader.setCustomModelResourceLocation(peachGold);
        UModelLoader.setCustomModelResourceLocation(peachGold, 1);
        UModelLoader.setCustomModelResourceLocation(pineappleGold);
        UModelLoader.setCustomModelResourceLocation(pineappleGold, 1);
        UModelLoader.setCustomModelResourceLocation(persimmonGold);
        UModelLoader.setCustomModelResourceLocation(persimmonGold, 1);
        UModelLoader.setCustomModelResourceLocation(pearGold);
        UModelLoader.setCustomModelResourceLocation(pearGold, 1);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-MoreFruits");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, grape, "Grape");
        language.register(LanguageResourceManager.JA_JP, grape, "ブドウ");
        language.register(LanguageResourceManager.EN_US, peach, "Peach");
        language.register(LanguageResourceManager.JA_JP, peach, "モモ");
        language.register(LanguageResourceManager.EN_US, pineapple, "Pineapple");
        language.register(LanguageResourceManager.JA_JP, pineapple, "パイナップル");
        language.register(LanguageResourceManager.EN_US, persimmon, "Japanese Persimmon");
        language.register(LanguageResourceManager.JA_JP, persimmon, "カキ");
        language.register(LanguageResourceManager.EN_US, pear, "Japanese Pear");
        language.register(LanguageResourceManager.JA_JP, pear, "ナシ");
        language.register(LanguageResourceManager.EN_US, grapeGold, "Golden Grape");
        language.register(LanguageResourceManager.JA_JP, grapeGold, "金のブドウ");
        language.register(LanguageResourceManager.EN_US, peachGold, "Golden Peach");
        language.register(LanguageResourceManager.JA_JP, peachGold, "金のモモ");
        language.register(LanguageResourceManager.EN_US, pineappleGold, "Golden Pineapple");
        language.register(LanguageResourceManager.JA_JP, pineappleGold, "金のパイナップル");
        language.register(LanguageResourceManager.EN_US, persimmonGold, "Golden Japanese Persimmon");
        language.register(LanguageResourceManager.JA_JP, persimmonGold, "金のカキ");
        language.register(LanguageResourceManager.EN_US, pearGold, "Golden Japanese Pear");
        language.register(LanguageResourceManager.JA_JP, pearGold, "金のナシ");

        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createItemJson(project, grape, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, peach, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, pineapple, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, persimmon, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, pear, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, grapeGold, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, peachGold, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, pineappleGold, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, persimmonGold, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, pearGold, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
    }
}
