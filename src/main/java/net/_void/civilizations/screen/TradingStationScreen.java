package net._void.civilizations.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net._void.civilizations.Civilizations;
import net._void.civilizations.networking.ModMessages;
import net._void.civilizations.util.CustomWidget;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class TradingStationScreen extends HandledScreen<TradingStationScreenHandler>{
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID, "textures/gui/trade_menu.png");
    private static final Identifier haybale = new Identifier("minecraft", "textures/block/hay_block_side.png");
    private static final Identifier clay = new Identifier("minecraft", "textures/block/clay.png");
    private static final Identifier coin = new Identifier(Civilizations.MOD_ID, "textures/item/egypt_coin.png");
    private static final Identifier papyrus = new Identifier(Civilizations.MOD_ID, "textures/item/papyrus.png");
    private static final Identifier gold_nugget = new Identifier("minecraft", "textures/item/gold_nugget.png");

    public TradingStationScreen(TradingStationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        CustomWidget customWidget1 = new CustomWidget(x + 6, y + 14, 39, 23){
            @Override
            protected boolean clicked(double mouseX, double mouseY) {
                if (mouseX >= x + 6 && mouseX <= x + 45 && mouseY >= y + 14 && mouseY <= y + 37) {
                    PacketByteBuf buffer = PacketByteBufs.create();
                    buffer.writeInt(1);
                    ClientPlayNetworking.send(ModMessages.TRADE, buffer);
                }
                if (mouseX >= x + 45 && mouseX <= x + 84 && mouseY >= y + 14 && mouseY <= y + 37) {
                    PacketByteBuf buffer = PacketByteBufs.create();
                    buffer.writeInt(2);
                    ClientPlayNetworking.send(ModMessages.TRADE, buffer);
                }
                if (mouseX >= x + 6 && mouseX <= x + 45 && mouseY >= y + 37 && mouseY <= y + 60) {
                    PacketByteBuf buffer = PacketByteBufs.create();
                    buffer.writeInt(3);
                    ClientPlayNetworking.send(ModMessages.TRADE, buffer);
                }
                if (mouseX >= x + 45 && mouseX <= x + 84 && mouseY >= y + 37 && mouseY <= y + 60) {
                    PacketByteBuf buffer = PacketByteBufs.create();
                    buffer.writeInt(4);
                    ClientPlayNetworking.send(ModMessages.TRADE, buffer);
                }
                return super.clicked(mouseX, mouseY);
            }
        };
        CustomWidget customWidget2 = new CustomWidget(x + 45, y + 14, 39, 23){};
        CustomWidget customWidget3 = new CustomWidget(x + 6, y + 37, 39, 23){};
        CustomWidget customWidget4 = new CustomWidget(x + 45, y + 37, 39, 23){};
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
        renderTrades(context, x, y);
        renderQuests(context, x, y);
    }

    private void renderReputationBar(DrawContext context, int x, int y) {
        int reputation = handler.getReputation();
        if(reputation >= 90) context.drawTexture(TEXTURE, x + 91, y + 14, 176, 0, 80, 5);
        else{
            context.drawTexture(TEXTURE, x + 91, y + 14, 176, 0, 1 + (int) Math.floor(reputation * 0.85), 5);
            context.drawTexture(TEXTURE, x + 91 + (int) Math.floor(reputation * 0.85), y + 14, 176 + (int) Math.floor(reputation * 0.85), 5, 80 - (int) Math.floor(reputation * 0.85), 5);
        }
    }

    private void renderTrades(DrawContext context, int x, int y){
        context.drawTexture(haybale, x + 9, y + 17, 0, 0, 16, 16, 16, 16);
        context.drawTexture(coin, x + 26, y + 17, 0, 0, 16, 16, 16, 16);
        context.drawTexture(clay, x + 48, y + 17, 0, 0, 16, 16, 16, 16);
        context.drawTexture(coin, x + 65, y + 17, 0, 0, 16, 16, 16, 16);
        context.drawTexture(coin, x + 9, y + 40, 0, 0, 16, 16, 16, 16);
        context.drawTexture(papyrus, x + 26, y + 40, 0, 0, 16, 16, 16, 16);
        context.drawTexture(coin, x + 48, y + 40, 0, 0, 16, 16, 16, 16);
        context.drawTexture(gold_nugget, x + 65, y + 40, 0, 0, 16, 16, 16, 16);
    }

    private void renderQuests(DrawContext context, int x, int y){
        int reputation = handler.getReputation();
        String line1 = "I found a very";
        String line2 = "precious item.";
        String line3 = "Bring some coins";
        String line4 = "and it's yours";
        switch (reputation){
            case 0 -> {
                line1 = "Hello traveller";
                line2 = "could you bring";
                line3 = "me some food";
                line4 = "for the camels?";
            }
            case 10 -> {
                line1 = "We need some";
                line2 = "sandstone to";
                line3 = "repair the";
                line4 = "pyramids";
            }
            case 20 -> {
                line1 = "We need clay";
                line2 = "for pottery,";
                line3 = "could you bring";
                line4 = "me some?";
            }
            case 30 -> {
                line1 = "My brush is";
                line2 = "worn out could";
                line3 = "you bring me";
                line4 = "a new one?";
            }
            case 40 -> {
                line1 = "I'm running out";
                line2 = "of food, could";
                line3 = "you bring me";
                line4 = "some bread?";
            }
            case 50 -> {
                line1 = "I need copper";
                line2 = "for my tools,";
                line3 = "could you help";
                line4 = "me with that?";
            }
            case 60 -> {
                line1 = "I need leather";
                line2 = "to make clothes";
                line3 = "Think you can";
                line4 = "help me?";
            }
            case 70 -> {
                line1 = "Bring me some";
                line2 = "honey and I'll";
                line3 = "give you some";
                line4 = "precious stuff";
            }
            case 80 -> {
                line1 = "I need diamonds";
                line2 = "for a gift to the";
                line3 = "king, can you";
                line4 = "help with that?";
            }
        }
        context.drawText(this.textRenderer, line1, x + 131 - size(line1) / 2, y + 40 - this.textRenderer.fontHeight - 10, 0x373737, false);
        context.drawText(this.textRenderer, line2, x + 131 - size(line2) / 2, y + 50 - this.textRenderer.fontHeight - 10, 0x373737, false);
        context.drawText(this.textRenderer, line3, x + 131 - size(line3) / 2, y + 60 - this.textRenderer.fontHeight - 10, 0x373737, false);
        context.drawText(this.textRenderer, line4, x + 131 - size(line4) / 2, y + 70 - this.textRenderer.fontHeight - 10, 0x373737, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    public int size(String text){
        if(text.isEmpty()) return 0;
        int size = text.length() - 1;
        List<Character> ch = text.chars().mapToObj(c -> (char) c).toList();
        for(char c : ch){
            if(c == ' ' || c == 't' || c == 'I') size += 3;
            else if(c == 'i' || c == '!' || c == '.' || c == ',' || c == '\'') size += 1;
            else if(c == 'l') size += 2;
            else if(c == 'f' || c == 'k') size += 4;
            else size += 5;
        }
        return size;
    }
}
