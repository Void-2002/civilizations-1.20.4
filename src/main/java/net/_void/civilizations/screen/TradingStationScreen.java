package net._void.civilizations.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net._void.civilizations.Civilizations;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TradingStationScreen extends HandledScreen<TradingStationScreenHandler>{
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID, "textures/gui/trade_menu.png");
    public static int TRADE = 1;

    public TradingStationScreen(TradingStationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;

        ButtonWidget button1 = ButtonWidget.builder(Text.literal(""), button -> {
                    TRADE=1;
                })
                .dimensions(238, 111, 39, 23)
                .build();
        ButtonWidget button2 = ButtonWidget.builder(Text.literal(""), button -> {
                    TRADE=2;
                })
                .dimensions(277, 111, 39, 23)
                .build();
        ButtonWidget button3 = ButtonWidget.builder(Text.literal(""), button -> {
                    TRADE=3;
                })
                .dimensions(238, 134, 39, 23)
                .build();
        ButtonWidget button4 = ButtonWidget.builder(Text.literal(""), button -> {
                    TRADE=4;
                })
                .dimensions(277, 134, 39, 23)
                .build();

        addDrawableChild(button1);
        addDrawableChild(button2);
        addDrawableChild(button3);
        addDrawableChild(button4);

    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;


        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        context.drawText(this.textRenderer, "Trades", x + 29, y + 24 - this.textRenderer.fontHeight - 10, 0x373737, false);
        context.drawText(this.textRenderer, "Quests", x + 115, y + 24 - this.textRenderer.fontHeight - 10, 0x373737, false);

        renderReputationBar(context, x, y);
        //renderTrades(context, x, y);
        //renderQuests(context, x, y);
    }

    private void renderReputationBar(DrawContext context, int x, int y) {
        int reputation = handler.getReputation();
        context.drawTexture(TEXTURE, x + 91, y + 14, 176 + reputation * 8, 0, 80 - reputation * 8, 5);
        context.drawTexture(TEXTURE, x + 91 + reputation * 8, y + 14, 176 + reputation * 8, 5, 1 - reputation * 8, 5);
    }

    private void renderIcons(DrawContext context, int x, int y){
        Identifier texture = new Identifier("minecraft", "textures/item/diamond.png");
        // texture, x, y, u, v, width, height, textureWidth, textureHeight
        context.drawTexture(texture, 150, 150, 0, 0, 16, 16, 16, 16);
    }

    private void renderIcon(DrawContext context){
        Identifier texture = new Identifier("minecraft", "textures/block/hay_block_side.png");
        context.drawTexture(texture, 180, 113, 0, 0, 16, 16, 16, 16);
    }

    private void renderTrades(DrawContext context, int x, int y){
        renderIcon(context);
    }

    private void renderQuests(DrawContext context, int x, int y){
        int reputation = handler.getReputation();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
