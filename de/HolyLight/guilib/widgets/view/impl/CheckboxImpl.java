
package de.HolyLight.guilib.widgets.view.impl;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;
import de.HolyLight.guilib.core.Viewport;
import de.HolyLight.guilib.widgets.model.Checkbox;
import de.HolyLight.guilib.widgets.view.adapters.CheckboxAdapter;

/**
 * Default style {@link Checkbox}.
 */
public class CheckboxImpl extends CheckboxAdapter {

    public static final int               SIZE    = 10;
    private static final ResourceLocation TEXTURE = new ResourceLocation("guilib", "textures/gui/checkbox.png");

    public CheckboxImpl(String label) {

        this(label, false);
    }

    public CheckboxImpl(String label, boolean checked) {

        super(label, checked);
    }

    @Override
    protected void doRevalidate() {

        int width = SIZE;
        if (!StringUtils.isBlank(getLabel())) {
            width += 2 + MC.fontRenderer.getStringWidth(getLabel());
        }
        setSize(width, SIZE);
    }

    @Override
    public void drawInLocalContext(Viewport viewport, int lmx, int lmy) {

        MC.renderEngine.bindTexture(TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(getX(), getY(), 0, isChecked() ? SIZE : 0, SIZE, SIZE);

        if (!StringUtils.isBlank(getLabel())) {
            MC.fontRenderer.drawStringWithShadow(getLabel(), getX() + SIZE + 2, getY() + 1, inLocalBounds(viewport, lmx, lmy) ? 16777120 : 0xffffff);
        }
    }

}
