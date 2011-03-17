package ch06_applikationsbausteine;

/**
 * Aufzählung, die die zu verarbeitenden Kommandozeilenparameter definiert
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
enum AppParameter
{
    DEBUG_SHORT("d"),
    HELP_SHORT("?"),
    WIDTH_SHORT("w="), WIDTH("width="), 
    HEIGHT_SHORT("h="), HEIGHT("height=");

    final String paramName;
    final int    valueOffset;

    AppParameter(final String name)
    {
        this.paramName = "-" + name;
        this.valueOffset = paramName.length();
    }
}