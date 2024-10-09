
package de.HolyLight.guilib.widgets.model;

import de.HolyLight.guilib.core.WidgetFocusable;

/**
 * Abstract representation of a {@link Label} that can be focused. It's quite useful for lists where the user selects one option by clicking on it (so it gains focus).
 */
public interface LabelFocusable extends Label, WidgetFocusable {

    public Object getUserData();

    public LabelFocusable setUserData(Object userData);

}
