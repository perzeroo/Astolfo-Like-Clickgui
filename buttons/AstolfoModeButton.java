package com.tepno.tepno.astolfoclickgui.buttons;

import com.tepno.tepno.settings.ModeSetting;
import com.tepno.tepno.utils.FontUtils;
import com.tepno.tepno.utils.RenderUtils;

import java.awt.*;

public class AstolfoModeButton extends AstolfoButton {
    public ModeSetting setting;
    public Color color;

    public AstolfoModeButton(float x, float y, float width, float height, ModeSetting set, Color col) {
        super(x, y, width, height);
        setting = set;
        color = col;
    }

    @Override
    public void drawPanel(int mouseX, int mouseY) {
        RenderUtils.drawRect(x, y, x + width, y + height, 0xff181A17);
        FontUtils.drawHeightCenteredString(setting.name + " > " + setting.get(), x + 4, y + height/2 - 0.5f, 0xffffffff);
    }

    @Override
    public void mouseAction(int mouseX, int mouseY, boolean click, int button) {
        if(isHovered(mouseX, mouseY) && click) {
            if(button == 0) setting.nextMode(); else setting.prevMode();
        }
    }
}
