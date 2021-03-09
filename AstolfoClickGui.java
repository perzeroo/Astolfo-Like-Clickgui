package com.tepno.tepno.astolfoclickgui;

import com.tepno.tepno.Client;
import com.tepno.tepno.astolfoclickgui.buttons.AstolfoButton;
import com.tepno.tepno.astolfoclickgui.buttons.AstolfoCategoryPanel;
import com.tepno.tepno.astolfoclickgui.buttons.AstolfoModuleButton;
import com.tepno.tepno.module.Category;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class AstolfoClickGui extends GuiScreen {

    public ArrayList<AstolfoCategoryPanel> categoryPanels = new ArrayList<>();

    public AstolfoClickGui() {

        int count = 4;

        for(Category cat : Category.values()) {
            switch (cat) {
                case COMBAT:
                    categoryPanels.add(new AstolfoCategoryPanel(count, 4, 100, 18, cat, new Color(0xffE64D3A)));
                    break;
                case MOVEMENT:
                    categoryPanels.add(new AstolfoCategoryPanel(count, 4, 100, 18, cat, new Color(0xff2ECD6F)));
                    break;
                case PLAYER:
                    categoryPanels.add(new AstolfoCategoryPanel(count, 4, 100, 18, cat, new Color(0xff8E45AE)));
                    break;
                case WORLD:
                    categoryPanels.add(new AstolfoCategoryPanel(count, 4, 100, 18, cat, new Color(0xffE65F00)));
                    break;
                case EXPLOIT:
                    categoryPanels.add(new AstolfoCategoryPanel(count, 4, 100, 18, cat, new Color(0xff3398D9)));
                    break;
                case MISC:
                    categoryPanels.add(new AstolfoCategoryPanel(count, 4, 100, 18, cat, new Color(0xffF29D11)));
                    break;
                case RENDER:
                    categoryPanels.add(new AstolfoCategoryPanel(count, 4, 100, 18, cat, new Color(0xff3601CE)));
                    break;
                case TARGETS:
                    categoryPanels.add(new AstolfoCategoryPanel(count, 4, 100, 18, cat, Color.YELLOW.brighter()));
                    break;
            }

            count += 120;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for(AstolfoCategoryPanel catPanel : categoryPanels) {
            catPanel.drawPanel(mouseX, mouseY);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        Client.instance.executorService.execute(() -> {
            for(AstolfoCategoryPanel catPan : categoryPanels) {
                catPan.mouseAction(mouseX, mouseY, true, mouseButton);

                if(catPan.open) {
                    for(AstolfoModuleButton modPan : catPan.moduleButtons) {
                        modPan.mouseAction(mouseX, mouseY, true, mouseButton);
                        if(modPan.extended) {
                            for(AstolfoButton pan : modPan.astolfoButtons) {
                                pan.mouseAction(mouseX, mouseY, true, mouseButton);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        Client.instance.executorService.execute(() -> {
            for(AstolfoCategoryPanel catPan : categoryPanels) {
                catPan.mouseAction(mouseX, mouseY, false, state);

                if(catPan.open) {
                    for(AstolfoModuleButton modPan : catPan.moduleButtons) {
                        modPan.mouseAction(mouseX, mouseY, false, state);

                        if(modPan.extended) {
                            for(AstolfoButton pan : modPan.astolfoButtons) {
                                pan.mouseAction(mouseX, mouseY, false, state);
                            }
                        }
                    }
                }
            }
        });
    }
}
