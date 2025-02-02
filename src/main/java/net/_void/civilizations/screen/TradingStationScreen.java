package net._void.civilizations.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net._void.civilizations.Civilizations;
import net._void.civilizations.util.CustomWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TradingStationScreen extends HandledScreen<TradingStationScreenHandler>{
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID, "textures/gui/trade_menu.png");
    private static final Identifier haybale = new Identifier("minecraft", "textures/block/hay_block_side.png");
    private static final Identifier clay = new Identifier("minecraft", "textures/block/clay.png");
    private static final Identifier coin = new Identifier(Civilizations.MOD_ID, "textures/item/egypt_coin.png");
    private static final Identifier papyrus = new Identifier(Civilizations.MOD_ID, "textures/item/blank_papyrus.png");
    private static final Identifier gold_nugget = new Identifier("minecraft", "textures/item/gold_nugget.png");
    public static int TRADE = 1;

    public TradingStationScreen(TradingStationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;

        CustomWidget customWidget1 = new CustomWidget(238, 111, 39, 23){
            @Override
            protected boolean clicked(double mouseX, double mouseY) {
                if (mouseX >= 238 && mouseX <= 238+39 && mouseY >= 111 && mouseY <= 111 + 23) TRADE = 1;
                if (mouseX >= 277 && mouseX <= 277+39 && mouseY >= 111 && mouseY <= 111 + 23) TRADE = 2;
                if (mouseX >= 238 && mouseX <= 238+39 && mouseY >= 134 && mouseY <= 134 + 23) TRADE = 3;
                if (mouseX >= 277 && mouseX <= 277+39 && mouseY >= 134 && mouseY <= 134 + 23) TRADE = 4;
                return super.clicked(mouseX, mouseY);
            }
        };
        CustomWidget customWidget2 = new CustomWidget(277, 111, 39, 23){};
        CustomWidget customWidget3 = new CustomWidget(238, 134, 39, 23){};
        CustomWidget customWidget4 = new CustomWidget(277, 134, 39, 23){};
        addDrawableChild(customWidget1);
        addDrawableChild(customWidget2);
        addDrawableChild(customWidget3);
        addDrawableChild(customWidget4);
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
        renderTrades(context);
        //renderQuests(context, x, y);
    }

    private void renderReputationBar(DrawContext context, int x, int y) {
        int reputation = handler.getReputation();
        context.drawTexture(TEXTURE, x + 91, y + 14, 176 + reputation * 8, 0, 80 - reputation * 8, 5);
        context.drawTexture(TEXTURE, x + 91 + reputation * 8, y + 14, 176 + reputation * 8, 5, 1 - reputation * 8, 5);
    }

    private void renderTrades(DrawContext context){
        context.drawTexture(haybale, 241, 114, 0, 0, 16, 16, 16, 16);
        context.drawTexture(coin, 258, 114, 0, 0, 16, 16, 16, 16);
        context.drawTexture(clay, 280, 114, 0, 0, 16, 16, 16, 16);
        context.drawTexture(coin, 297, 114, 0, 0, 16, 16, 16, 16);
        context.drawTexture(coin, 241, 137, 0, 0, 16, 16, 16, 16);
        context.drawTexture(papyrus, 258, 137, 0, 0, 16, 16, 16, 16);
        context.drawTexture(coin, 280, 137, 0, 0, 16, 16, 16, 16);
        context.drawTexture(gold_nugget, 297, 137, 0, 0, 16, 16, 16, 16);
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
