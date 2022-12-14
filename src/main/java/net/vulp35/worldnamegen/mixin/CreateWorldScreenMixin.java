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
import net.vulp35.worldnamegen.WorldNameGen;
import net.vulp35.worldnamegen.config.ConfigGui;
import net.vulp35.worldnamegen.utils.NameGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin extends Screen {

    @Shadow private TextFieldWidget levelNameField;
    @Shadow private boolean moreOptionsOpen;

    private ButtonWidget generateNameButton;
    private ButtonWidget settingsButton;
    private ButtonWidget generateSeedButton;

    private static final Identifier GENERATE_BUTTON_TEXTURE = new Identifier("worldnamegen", "textures/gui/generate.png");
    private static final Identifier SETTINGS_BUTTON_TEXTURE = new Identifier("worldnamegen", "textures/gui/settings.png");

    @Shadow protected abstract <T extends Element & Drawable & Selectable> T addDrawableChild(T drawableElement);

    protected CreateWorldScreenMixin(Text title, TextFieldWidget levelNameField, boolean moreOptionsOpen) {
        super(title);
        this.levelNameField = levelNameField;
        this.moreOptionsOpen = moreOptionsOpen;
    }

    @Inject(at = @At("RETURN"), method = "init()V")
    private void AddWorldNameGenButtons(CallbackInfo ci) {
        this.generateNameButton = this.addDrawableChild(
                new TexturedButtonWidget(this.width / 2 + 105, 60, 20, 20, 0, 0, 20,
                        GENERATE_BUTTON_TEXTURE, 32, 64,
                        (button) -> this.levelNameField.setText(NameGenerator.getNewName())));
        this.settingsButton = this.addDrawableChild(
                new TexturedButtonWidget(this.width / 2 + 130, 60, 20, 20, 0, 0, 20,
                        SETTINGS_BUTTON_TEXTURE, 32, 64,
                        (button) -> this.client.setScreen(ConfigGui.createGui(client.currentScreen))));

        this.generateSeedButton = this.addDrawableChild(
                new TexturedButtonWidget(this.width / 2 + 105, 60, 20, 20, 0, 0, 20,
                        GENERATE_BUTTON_TEXTURE, 32, 64,
                        (button) -> {

                        } ));
    }

    @Inject(at = @At("RETURN"), method = "setMoreOptionsOpen*")
    private void moreOptionsScreenOpen(CallbackInfo ci) {
        if (this.generateNameButton != null) {
            this.generateNameButton.visible = !moreOptionsOpen;
        }
        if (this.settingsButton != null) {
            this.settingsButton.visible = !moreOptionsOpen;
        }
        if (this.generateSeedButton != null ) {
            if (WorldNameGen.getConfig().getConfig().buttonOnSeedScreen) {
                this.generateSeedButton.visible = moreOptionsOpen;
            } else {
                this.generateSeedButton.visible = false;
            }
        }
    }

}
