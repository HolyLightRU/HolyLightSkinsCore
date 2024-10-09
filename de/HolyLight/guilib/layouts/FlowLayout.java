
package de.HolyLight.guilib.layouts;

import de.HolyLight.guilib.core.Axis;
import de.HolyLight.guilib.core.Widget;
import de.HolyLight.guilib.widgets.model.Container;
import de.HolyLight.guilib.widgets.model.Container.LayoutManager;

/**
 * Positions all the widgets in a container either on top of each other (Y axis) or from left to right (X axis) with a specified gap between them.
 */
public class FlowLayout implements LayoutManager {

    private final Axis axis;
    private final int  padding, gap;

    public FlowLayout(Axis axis) {

        this(axis, 10, 5);
    }

    public FlowLayout(Axis axis, int padding, int gap) {

        this.axis = axis;
        this.padding = padding;
        this.gap = gap;
    }

    @Override
    public void layout(Container container) {

        int coord = padding;
        for (Widget widget : container.getWidgets()) {
            widget.setCoord(axis, coord);
            coord += widget.getExtent(axis) + gap;
        }
    }

}
