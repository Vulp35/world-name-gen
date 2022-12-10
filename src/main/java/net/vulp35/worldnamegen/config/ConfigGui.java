package net.vulp35.worldnamegen.config;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.*;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.vulp35.worldnamegen.WorldNameGen;

public class ConfigGui {

    public static void save() {
        MinecraftClient.getInstance().options.write();
        WorldNameGen.getConfig().save();
    }
    public static Screen createGui(Screen parent) {
        return YetAnotherConfigLib.create(WorldNameGen.getConfig(), (defaults, config, builder) -> builder
                .title(Text.of("World Name Generator"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.of("World Name Generator"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.of("Enable Seed name generation"))
                                .tooltip(Text.of("This option puts the generate button on the seed screen"))
                                .binding(
                                        defaults.buttonOnSeedScreen,
                                        () -> config.buttonOnSeedScreen,
                                        (value) -> config.buttonOnSeedScreen = value
                                )
                                .available(false)
                                .controller(BooleanController::new)
                                .build()
                        )
                        .option(Option.createBuilder(int.class)
                                .name(Text.of("Max Length of Generated Name"))
                                .tooltip(Text.of("Sets the longest the generated name can be.\n" +
                                                        "This may limit how many unique names are generated."))
                                .binding(
                                        defaults.maxNameLength,
                                        () -> config.maxNameLength,
                                        (value) -> config.maxNameLength = value
                                )
                                .controller(opt -> new IntegerSliderController(opt, 7, 32, 1))
                                .build()
                        )
                        .build())
                                .save(ConfigGui::save)
                )
                .generateScreen(parent);
    }
}
