
package de.HolyLight.guilib.core;

/**
 * {@link Widget}s can implement this interface if they want to have a tooltip show up whenever the mouse cursor hovers over them.
 */
public interface WidgetTooltipable extends Widget {

    /**
     * Returns the {@link Widget} that should be shown at the mouse position whenever the user hovers over this widget.
     * If this method returns {@code null}, no such tooltip is shown.
     * Whether or not the mouse hovers over this widget is determined using the {@link #inGlobalBounds(Viewport, int, int)} or the {@link #inLocalBounds(Viewport, int, int)} method.
     * Note that this method is called at each drawing tick as long as the mouse cursor hovers over the widget.
     * Be careful not to create a new widget at each method call, because all those useless widgets would bloat the memory and block the GC in no time!
     *
     * @param hoveredMillis The amount of milliseconds since the mouse cursor started hovering over this widget.
     * @return The tooltip that should be rendered when the user hovers over this widget.
     */
    public Widget getTooltip(int hoveredMillis);

}
