
package de.HolyLight.guilib.widgets.view.impl;

import de.HolyLight.guilib.core.Widget;
import de.HolyLight.guilib.widgets.model.Container;
import de.HolyLight.guilib.widgets.model.ContainerFlexible;
import de.HolyLight.guilib.widgets.view.adapters.ContainerAdapter;

/**
 * Default implementation of {@link Container}, which clips all content that peeks out of the container's set bounds.
 */
public class ContainerClippingImpl extends ContainerAdapter implements ContainerFlexible {

    public ContainerClippingImpl(Widget... widgets) {

        super(widgets);
    }

}
