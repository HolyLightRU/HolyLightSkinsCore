
package de.HolyLight.skinshifter.client.gui;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.ResourceLocation;
import de.HolyLight.guilib.core.Viewport;
import de.HolyLight.guilib.core.WidgetFlexible;
import de.HolyLight.guilib.extra.ContextHelperWidgetAdapter;
import de.HolyLight.skinshifter.client.skin.services.SkinApplierService;
import de.HolyLight.skinshifter.common.skin.Skin;

public class PlayerDisplay extends ContextHelperWidgetAdapter implements WidgetFlexible {

    private Skin customSkin;

    public Skin getCustomSkin() {

        return customSkin;
    }

    public void setCustomSkin(Skin customSkin) {

        this.customSkin = customSkin;
    }

    @Override
    public void drawInLocalContext(Viewport viewport, int lmx, int lmy) {

        float scaleX = getWidth() / (33f / 30f);
        float scaleY = getHeight() / (62f / 30f);
        float finalScale = Math.min(scaleX, scaleY);

        float offsetX = finalScale * (17f / 30f);
        float offsetY = finalScale * (59f / 30f);

        // Center the player on the axis whose side length is larger than the actual player
        float playerWidth = finalScale * (33f / 30f);
        float playerHeight = finalScale * (62f / 30f);
        if ((float) getWidth() / getHeight() > 33f / 62f) {
            offsetX += (getWidth() - playerWidth) / 2;
        } else {
            offsetY += (getHeight() - playerHeight) / 2;
        }

        // The mouse position relative to the head of the player
        float relativeMouseX = - (lmx - (getX() + offsetX));
        float relativeMouseY = - (lmy - (getY() + offsetY - playerHeight * 0.8f));

        ResourceLocation oldSkinResource = MC.thePlayer.getLocationSkin();
        if (customSkin != null) {
            SkinApplierService.setSkinTo(MC.thePlayer, customSkin);
        }

        // This method is normally used to draw the small player in the middle of the inventory screen
        GuiInventory.func_147046_a(getX() + (int) offsetX, getY() + (int) offsetY, (int) finalScale, relativeMouseX, relativeMouseY, MC.thePlayer);

        if (customSkin != null) {
            SkinApplierService.setSkinTo(MC.thePlayer, oldSkinResource);
        }
    }

}
