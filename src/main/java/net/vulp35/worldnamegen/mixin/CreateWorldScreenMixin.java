package net.vulp35.worldnamegen.mixin;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.vulp35.worldnamegen.utils.NameGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin extends Screen {

    private TextFieldWidget levelNameField;

    @Shadow protected abstract <T extends Element & Drawable & Selectable> T addDrawableChild(T drawableElement);

    protected CreateWorldScreenMixin(Text title, TextFieldWidget levelNameField) {
        super(title);
        this.levelNameField = levelNameField;
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void AddWorldNameGenButtons(CallbackInfo ci) {
        ButtonWidget generateNameButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 105, 60, 20, 20, Text.of("?"),
                (button) -> this.levelNameField.setText(NameGenerator.getNewName())));
        ButtonWidget settingsButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 130, 60, 20, 20, Text.of("..."), (button) -> {
            // Open settings
        }));
    }

}
