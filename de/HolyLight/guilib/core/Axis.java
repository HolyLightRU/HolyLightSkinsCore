
package de.HolyLight.guilib.core;

public enum Axis {

    X, Y;

    public Axis other() {

        return this == X ? Y : X;
    }

}
