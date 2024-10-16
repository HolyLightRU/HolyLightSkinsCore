
package de.HolyLight.guilib.widgets.view.adapters;

import java.util.Collection;
import org.apache.commons.lang3.Validate;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import de.HolyLight.guilib.core.MouseButton;
import de.HolyLight.guilib.core.Viewport;
import de.HolyLight.guilib.extra.ContextHelperWidgetAdapter;
import de.HolyLight.guilib.widgets.model.Dropdown;
import de.HolyLight.guilib.widgets.model.Dropdown.Option;

/**
 * A minimal implementation of {@link Dropdown} that doesn't contain any drawing code.
 *
 * @param <O> The type of {@link Option} that can be selected through this dropdown menu.
 */
public abstract class DropdownAdapter<O extends Option<?>> extends ContextHelperWidgetAdapter implements Dropdown<O> {

    private DropdownHandler<O> handler;

    private ImmutableList<O>   options;
    private O                  selectedOption;

    private boolean            focused;

    public DropdownAdapter(Collection<O> options) {

        this(options, null);
    }

    public DropdownAdapter(Collection<O> options, DropdownHandler<O> handler) {

        setOptions(options);
        this.handler = handler;
    }

    @Override
    public DropdownHandler<O> getHandler() {

        return handler;
    }

    @Override
    public DropdownAdapter<O> setHandler(DropdownHandler<O> handler) {

        this.handler = handler;
        return this;
    }

    @Override
    public ImmutableList<O> getOptions() {

        return options;
    }

    @Override
    public DropdownAdapter<O> setOptions(Collection<O> options) {

        this.options = ImmutableList.copyOf(options);

        if (!options.contains(selectedOption)) {
            setSelectedOption(options.iterator().next());
        }

        invalidate();

        return this;
    }

    @Override
    public O getSelectedOption() {

        return selectedOption;
    }

    @Override
    public DropdownAdapter<O> setSelectedOption(O selectedOption) {

        Validate.isTrue(options.contains(selectedOption), "You can't select an option which the dropdown doesn't even offer");
        this.selectedOption = selectedOption;

        return this;
    }

    @Override
    public boolean isFocused() {

        return focused;
    }

    @Override
    public void focusGained() {

        focused = true;
    }

    @Override
    public void focusLost() {

        focused = false;
    }

    @Override
    public boolean mousePressedInLocalContext(Viewport viewport, int lmx, int lmy, MouseButton mouseButton) {

        if (mouseButton == MouseButton.LEFT && inLocalBounds(viewport, lmx, lmy)) {
            MC.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            return true;
        }

        return false;
    }

}
