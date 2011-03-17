package ch06_applikationsbausteine;

/**
 * Aufzählung, die die zu verarbeitenden Kommandozeilenparameter definiert
 * <br>
 * Variante für das CLI-Framework 
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public enum AppParameterCLI
{
    WIDTH("w", "width", true, "set the width"), 
    HEIGHT("h", "height", true, "set the height"), 
    HELP("?", "help", false, "show help"),
    DEBUG("d", "debug", false, "activate debug mode"); 

    final String  shortname;
    final String  name;
    final boolean hasArgs;
    final String  helptext;

    AppParameterCLI(final String shortname, final String name, 
                    final boolean hasArgs, final String helptext)
    {
        this.shortname = shortname;
        this.name = name;
        this.hasArgs = hasArgs;
        this.helptext = helptext;
    }
}