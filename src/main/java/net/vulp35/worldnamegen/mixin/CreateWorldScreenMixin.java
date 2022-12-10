package net.vulp35.worldnamegen.mixin;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.vulp35.worldnamegen.config.ConfigGui;
import net.vulp35.worldnamegen.utils.NameGenerator;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin extends Screen {

    @Shadow private TextFieldWidget levelNameField;
    private static final Identifier GENERATE_BUTTON_TEXTURE = new Identifier("worldnamegen", "textures/gui/generate.png");
    private static final Identifier SETTINGS_BUTTON_TEXTURE = new Identifier("worldnamegen", "textures/gui/settings.png");

    @Shadow protected abstract <T extends Element & Drawable & Selectable> T addDrawableChild(T drawableElement);

    @Shadow @Final @Nullable private Screen parent;

    protected CreateWorldScreenMixin(Text title, TextFieldWidget levelNameField) {
        super(title);
        this.levelNameField = levelNameField;
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void AddWorldNameGenButtons(CallbackInfo ci) {
        ButtonWidget generateNameButton = this.addDrawableChild(
                new TexturedButtonWidget(this.width / 2 + 105, 60, 20, 20, 0, 0, 20,
                        GENERATE_BUTTON_TEXTURE, 32, 64,
                        (button) -> this.levelNameField.setText(NameGenerator.getNewName())));
        ButtonWidget settingsButton = this.addDrawableChild(
                new TexturedButtonWidget(this.width / 2 + 130, 60, 20, 20, 0, 0, 20,
                        SETTINGS_BUTTON_TEXTURE, 32, 64,
                        (button) -> this.client.setScreen(new ConfigGui().createGui(client.currentScreen))));
    }

}
