
package de.HolyLight.guilib.widgets.model;

import de.HolyLight.guilib.core.WidgetFlexible;

/**
 * Abstract representation of a {@link Button} that displays a label text on its surface.
 */
public interface ButtonLabel extends Button, WidgetFlexible {

    public String getLabel();

    public ButtonLabel setLabel(String label);

}
