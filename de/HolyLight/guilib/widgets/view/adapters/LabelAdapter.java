
package de.HolyLight.guilib.widgets.view.adapters;

import org.apache.commons.lang3.Validate;
import de.HolyLight.guilib.extra.ContextHelperWidgetAdapter;
import de.HolyLight.guilib.widgets.model.Label;

/**
 * A minimal implementation of {@link Label} that doesn't contain any drawing code.
 */
public abstract class LabelAdapter extends ContextHelperWidgetAdapter implements Label {

    private String text;

    public LabelAdapter(String text) {

        setText(text);
    }

    @Override
    public String getText() {

        return text;
    }

    @Override
    public LabelAdapter setText(String text) {

        Validate.notNull(text, "Label text cannot be null");
        this.text = text;
        invalidate();

        return this;
    }

}
