package ch04_javagrundlagen;

/**
 * Beispielprogramm zur Demonstration von Fallstricken, wenn man die Funktionalität des
 * StringTokenizers durch String.split() ersetzen möchte
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class StringSplitExample
{
    public static void main(String[] args)
    {
        versuch1();
        versuch2();
        versuch3();
    }

    private static void versuch1()
    {
        final String versions = "2.14-17";
        final String[] values = versions.split("._-");
        StringUtils.printTokens(values); // Tokens = '['2.14-17']' 
    }

    private static void versuch2()
    {
        final String versions = "2.14-17";
        final String[] values2 = versions.split(".|_|-");
        StringUtils.printTokens(values2); // Tokens = '[]' 
    }

    private static void versuch3()
    {
        final String versions = "2.14-17";
        final String[] values3 = versions.split("\\.|_|-");
        StringUtils.printTokens(values3); // Tokens = '['2', '14', '17']' 
    }

    private StringSplitExample()
    {
    }
}
