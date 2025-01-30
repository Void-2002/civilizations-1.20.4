package net._void.civilizations.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class CustomWidget extends ClickableWidget {

    public CustomWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Text.empty());
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(getX(), getY(), getX() + this.width, getY() + this.height, 0x00FF6F);

        if(isSelected()){
            context.drawHorizontalLine(getX(), getX() + width, getY(), 0xFFFFFFFF);
            context.drawHorizontalLine(getX(), getX() + width, getY() + height, 0xFFFFFFFF);
            context.drawVerticalLine(getX(), getY(), getY() + height, 0xFFFFFFFF);
            context.drawVerticalLine(getX() + width, getY(), getY() + height, 0xFFFFFFFF);
        }
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }
}
