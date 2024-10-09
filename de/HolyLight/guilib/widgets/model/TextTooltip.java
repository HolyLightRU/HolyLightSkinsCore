
package de.HolyLight.guilib.widgets.model;

import java.util.List;
import de.HolyLight.guilib.core.WidgetRigid;

/**
 * Abstract representation of a tooltip which displays multiple lines of text.
 */
public interface TextTooltip extends WidgetRigid {

    public List<String> getLines();

    public TextTooltip setLines(List<String> lines);

    public TextTooltip addLines(List<String> lines);

    public TextTooltip removeLines(List<String> lines);

}
