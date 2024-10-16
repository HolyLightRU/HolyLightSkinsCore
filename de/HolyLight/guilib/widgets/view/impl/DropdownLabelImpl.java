
package de.HolyLight.guilib.widgets.view.impl;

import java.util.Collection;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.config.GuiUtils;
import de.HolyLight.guilib.core.Axis;
import de.HolyLight.guilib.core.MouseButton;
import de.HolyLight.guilib.core.Viewport;
import de.HolyLight.guilib.core.Widget;
import de.HolyLight.guilib.layouts.FlowLayout;
import de.HolyLight.guilib.util.FontUtils;
import de.HolyLight.guilib.widgets.model.Button;
import de.HolyLight.guilib.widgets.model.Button.ButtonHandler;
import de.HolyLight.guilib.widgets.model.ButtonLabel;
import de.HolyLight.guilib.widgets.model.ContainerFlexible;
import de.HolyLight.guilib.widgets.model.Dropdown;
import de.HolyLight.guilib.widgets.model.Dropdown.Option;
import de.HolyLight.guilib.widgets.model.Scrollbar;
import de.HolyLight.guilib.widgets.view.adapters.DropdownAdapter;

/**
 * Default style {@link Dropdown} that displays labels for the different options.
 *
 * @param <O> The type of {@link Option} that can be selected through this dropdown menu.
 */
public class DropdownLabelImpl<O extends Option<String>> extends DropdownAdapter<O> {

    private static final int              EXT_MARGIN       = 10;
    private static final int              OPTION_HEIGHT    = 12;
    private static final int              OPTION_H_PADDING = 6;

    private static final ResourceLocation TEXTURE          = new ResourceLocation("textures/gui/widgets.png");

    // The dropdown that appears when you click on the dropdown button
    private ContainerFlexible             ext;
    private Scrollbar                     extScrollbar;

    public DropdownLabelImpl(Collection<O> options) {

        this(options, null);
    }

    public DropdownLabelImpl(Collection<O> options, DropdownHandler<O> handler) {

        super(options, handler);

        setSize(200, 20);
    }

    public int getMaxOptionTextWidth() {

        int maxOptionWidth = 0;
        for (O option : getOptions()) {
            maxOptionWidth = Math.max(maxOptionWidth, MC.fontRenderer.getStringWidth(option.getDisplayObject()));
        }
        return maxOptionWidth;
    }

    public int getExtWidth() {

        return getMaxOptionTextWidth() + 2 * OPTION_H_PADDING + extScrollbar.getWidth(); // horizontal gap on each side, plus the scrollbar width
    }

    /*
     * Event handlers
     */

    @Override
    public DropdownLabelImpl<O> setOptions(Collection<O> options) {

        super.setOptions(options);

        generateExt();
        invalidate();

        return this;
    }

    private void generateExt() {

        extScrollbar = new ScrollbarImpl(0);
        ext = new ContainerScrollableImpl(extScrollbar, 10);
        ext
                .appendLayoutManager(c -> {
                    extScrollbar.setPosition(ext.getWidth() - extScrollbar.getWidth(), 0);

                    for (Widget widget : ext.getWidgets()) {
                        ((ButtonLabel) widget).setSize(getMaxOptionTextWidth() + 2 * OPTION_H_PADDING, OPTION_HEIGHT);
                    }
                })
                .appendLayoutManager(new FlowLayout(Axis.Y, 0, 0));

        for (final O option : getOptions()) {
            Button optionButton = new ButtonLabelImpl(option.getDisplayObject(), new ButtonHandler() {

                @Override
                public void buttonClicked(Button button, MouseButton mouseButton) {

                    setSelectedOption(option);
                    focusLost();

                    if (getHandler() != null) {
                        getHandler().optionSelected(DropdownLabelImpl.this, option);
                    }
                }

            });
            ext.addWidgets(optionButton);
        }
    }

    @Override
    protected void doRevalidate() {

        ext.setPosition(getX(), getY() + getHeight());
        ext.setWidth(getExtWidth());
    }

    private void fitExtIntoWindow(Viewport viewport) {

        // Make sure that ext does not peek out of the right side of the Minecraft window
        int maxX = viewport.localX(viewport.getScreenSize().getWidth() - EXT_MARGIN - ext.getWidth());
        ext.setX(MathHelper.clamp_int(0, getX(), maxX));

        // Make ext as high as necessary, but don't let it peek out of the lower side of the Minecraft window
        int maxHeight = viewport.getScreenSize().getHeight() - EXT_MARGIN - viewport.globalY(getY() + ext.getY());
        ext.setHeight(MathHelper.clamp_int(getOptions().size() * OPTION_HEIGHT, 0, maxHeight));

        ext.revalidate(true);
    }

    /*
     * Drawing code
     */

    @Override
    public void drawInLocalContext(Viewport viewport, int lmx, int lmy) {

        boolean hover = inLocalBounds(viewport, lmx, lmy);

        int u = 0, v = 46 + (hover ? 40 : 20);
        GuiUtils.drawContinuousTexturedBox(TEXTURE, getX(), getY(), u, v, getWidth(), getHeight(), 200, 20, 2, 3, 2, 2, zLevel);

        String actualLabel = FontUtils.abbreviateIfTooLong(MC.fontRenderer, getSelectedOption().getDisplayObject(), getWidth() - 6);
        drawCenteredString(MC.fontRenderer, actualLabel, getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, hover ? 16777120 : 14737632);
    }

    /*
     * Forwarding events to ext, removing the scissor area
     */

    @Override
    public boolean revalidate(boolean force) {

        boolean extRevalidated = ext.revalidate(!valid || force);
        return super.revalidate(force || extRevalidated);
    }

    @Override
    public void update() {

        super.update();
        if (isFocused()) {
            ext.update();
        }
    }

    @Override
    public void draw(Viewport viewport, int mx, int my) {

        super.draw(viewport, mx, my);
        if (isFocused()) {
            fitExtIntoWindow(viewport); // Since the revalidate() method doesn't take a viewport (for good reasons), we have to do the ext x and height adjustment here
            ext.draw(viewport.withoutScissor(), mx, my);
        }
    }

    @Override
    public boolean mousePressed(Viewport viewport, int mx, int my, MouseButton mouseButton) {

        return super.mousePressed(viewport, mx, my, mouseButton) | (isFocused() && ext.mousePressed(viewport.withoutScissor(), mx, my, mouseButton));
    }

    @Override
    public void mouseReleased(Viewport viewport, int mx, int my, MouseButton mouseButton) {

        super.mouseReleased(viewport, mx, my, mouseButton);
        if (isFocused()) {
            ext.mouseReleased(viewport.withoutScissor(), mx, my, mouseButton);
        }
    }

    @Override
    public boolean mouseWheel(Viewport viewport, int mx, int my, int delta) {

        return super.mouseWheel(viewport, mx, my, delta) | (isFocused() && ext.mouseWheel(viewport.withoutScissor(), mx, my, delta));
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {

        return super.keyTyped(typedChar, keyCode) | (isFocused() && ext.keyTyped(typedChar, keyCode));
    }

}
