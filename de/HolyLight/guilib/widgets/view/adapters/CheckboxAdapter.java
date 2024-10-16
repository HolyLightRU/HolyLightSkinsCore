
package de.HolyLight.guilib.widgets.view.adapters;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import de.HolyLight.guilib.core.MouseButton;
import de.HolyLight.guilib.core.Viewport;
import de.HolyLight.guilib.extra.ContextHelperWidgetAdapter;
import de.HolyLight.guilib.widgets.model.Checkbox;

/**
 * A minimal implementation of {@link Checkbox} that doesn't contain any drawing code.
 */
public abstract class CheckboxAdapter extends ContextHelperWidgetAdapter implements Checkbox {

    private CheckboxHandler handler;

    private String          label;
    private boolean         checked;

    public CheckboxAdapter(String label) {

        this(label, false);
    }

    public CheckboxAdapter(String label, boolean checked) {

        setLabel(label);
        this.checked = checked;
    }

    @Override
    public CheckboxHandler getHandler() {

        return handler;
    }

    @Override
    public CheckboxAdapter setHandler(CheckboxHandler handler) {

        this.handler = handler;
        return this;
    }

    @Override
    public String getLabel() {

        return label;
    }

    @Override
    public CheckboxAdapter setLabel(String label) {

        this.label = label;
        invalidate();

        return this;
    }

    @Override
    public boolean isChecked() {

        return checked;
    }

    @Override
    public CheckboxAdapter setChecked(boolean checked) {

        this.checked = checked;
        return this;
    }

    @Override
    public boolean mousePressedInLocalContext(Viewport viewport, int lmx, int lmy, MouseButton mouseButton) {

        if (mouseButton == MouseButton.LEFT && inLocalBounds(viewport, lmx, lmy)) {
            MC.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));

            checked = !checked;

            if (getHandler() != null) {
                getHandler().checkboxChanged(this, checked);
            }

            return true;
        }

        return false;
    }

}
