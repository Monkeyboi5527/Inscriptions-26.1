package net.monkeyskl.inscriptions.menu.custom;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.monkeyskl.inscriptions.Inscriptions;

public class TestCraftingScreen extends AbstractContainerScreen<TestCraftingMenu> {

    public static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "textures/gui/test_crafting/test_crafting_gui.png");

    public TestCraftingScreen(TestCraftingMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 166);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                GUI_TEXTURE,
                this.leftPos, this.topPos,
                0, 0,
                this.imageWidth, this.imageHeight,
                256, 256
        );

        super.extractContents(graphics, mouseX, mouseY, a);
    }
}
