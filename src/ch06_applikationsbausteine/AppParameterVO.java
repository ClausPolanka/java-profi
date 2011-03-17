package ch06_applikationsbausteine;

/**
 * Value Object, das die zu verarbeitenden Kommandozeilenparameter speichern kann
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
class AppParameterVO
{
    // Defaultwerte, wenn Wert nicht in der Kommandozeile übergeben
    int     width    = 700;
    int     height   = 200;
    boolean debug    = false;
    boolean showHelp = false;

    @Override
    public String toString()
    {
        return "AppParameterVO [debug=" + debug + ", height=" + height + ", " +
                            "showHelp=" + showHelp + ", width=" + width + "]";
    }
}
