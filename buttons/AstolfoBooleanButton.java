package com.tepno.tepno.astolfoclickgui.buttons;

import com.tepno.tepno.settings.BooleanSetting;
import com.tepno.tepno.utils.FontUtils;
import com.tepno.tepno.utils.RenderUtils;

import java.awt.*;

public class AstolfoBooleanButton extends AstolfoButton {
    public BooleanSetting setting;
    public Color color;

    public AstolfoBooleanButton(float x, float y, float width, float height, BooleanSetting set, Color col) {
        super(x, y, width, height);
        setting = set;
        color = col;
    }

    @Override
    public void drawPanel(int mouseX, int mouseY) {
        RenderUtils.drawRect(x, y, x + width, y + height, 0xff181A17);
        if(setting.get()) RenderUtils.drawRect(x + 3, y, x + width - 3, y + height, color.getRGB());
        FontUtils.drawHeightCenteredString(setting.name, x + 4, y + height/2 - 0.5f, 0xffffffff);
    }

    @Override
    public void mouseAction(int mouseX, int mouseY, boolean click, int button) {
        if(isHovered(mouseX, mouseY) && click) {
            setting.set(!setting.get());
        }
    }
}
