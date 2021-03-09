package com.tepno.tepno.astolfoclickgui.buttons;

import com.tepno.tepno.module.Module;
import com.tepno.tepno.settings.BooleanSetting;
import com.tepno.tepno.settings.ModeSetting;
import com.tepno.tepno.settings.NumberSetting;
import com.tepno.tepno.settings.Setting;
import com.tepno.tepno.utils.FontUtils;
import com.tepno.tepno.utils.RenderUtils;

import java.awt.*;
import java.util.ArrayList;

public class AstolfoModuleButton extends AstolfoButton {
    public Module module;
    public Color color;

    public boolean extended;

    public float finalHeight;

    public ArrayList<AstolfoButton> astolfoButtons = new ArrayList<>();

    public AstolfoModuleButton(float x, float y, float width, float height, Module mod, Color col) {
        super(x, y, width, height);

        module = mod;

        color = col;

        final float startY = y + height;

        int count = 0;

        for(Setting set : module.settings) {
            if(set instanceof BooleanSetting) astolfoButtons.add(new AstolfoBooleanButton(x, startY + 18*count, width, 9, (BooleanSetting)set, color));
            if(set instanceof ModeSetting) astolfoButtons.add(new AstolfoModeButton(x, startY + 18*count, width, 9, (ModeSetting)set, color));
            if(set instanceof NumberSetting) astolfoButtons.add(new AstolfoNumberButton(x, startY + 18*count, width, 9, (NumberSetting)set, color));
            count++;
        }
    }

    @Override
    public void drawPanel(int mouseX, int mouseY) {
        RenderUtils.drawRect(x, y, x + width, y + height, 0xff181A17);

        if(!extended)
            RenderUtils.drawRect(x + 2, y, x + width - 2, y + height, module.isToggled() ? color.getRGB() : 0xff232623);
        else
            RenderUtils.drawRect(x + 2, y, x + width - 2, y + height, 0xff181A17);

        FontUtils.drawHeightCenteredString(module.getName().toLowerCase(), (x + width) - FontUtils.getStringWidth(module.getName().toLowerCase()) - 3, y + height/2, extended ? module.isToggled() ? color.getRGB() : 0xffffffff : 0xffffffff);

        int count = 0;

        float hehe = 0;

        if(extended) {
            final float startY = y + height;
            for(AstolfoButton pan : astolfoButtons) {
                pan.x = x;
                pan.y = startY + pan.height*count;
                pan.drawPanel(mouseX, mouseY);
                count++;

                hehe = pan.height;
            }
        }

        finalHeight = hehe * count + height;
    }

    @Override
    public void mouseAction(int mouseX, int mouseY, boolean click, int button) {
        if(isHovered(mouseX, mouseY) && click) {
            if(button == 0) {
                module.toggle();
            } else if(module.settings.size() > 0) extended = !extended;
        }
    }
}
