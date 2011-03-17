package ch03_oodesign;

/**
 * Beispielklasse zur Demonstration von Invarainten und Vor- und Nachbedingungen
 * auf den Objektzustand
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class GridPosition
{
    private static final int GRID_SIZE = 10;
    
    // Invariante: x,y liegen immer auf einem Raster der Gr��e 10 
    private int x = 0;
    private int y = 0;

    GridPosition()
    {
    }
    
    public void addOffset(final int dx, final int dy)
    {
        // Vorbedingung: x, y auf einem beliebigen Rasterpunkt 
        checkOnGrid(x,y);
        
        x += snapToGrid(dx);
        y += snapToGrid(dy);

        // Nachbedingung: x + (dy \% 10), y + (dy % 10) auf einem Rasterpunkt 
        checkOnGrid(x,y);
    }

    public void setSamePos(final int pos)
    {
        // Vorbedingung: x, y auf einem beliebigen Rasterpunkt
        checkOnGrid(x,y);
        
        x = snapToGrid(pos);
        y = snapToGrid(pos);
        
        // Nachbedingung: x = y = (pos % 10) auf einem Rasterpunkt 
        checkOnGrid(x,y);
    }

    private static void checkOnGrid(final int x, final int y)
    {
        if (x % GRID_SIZE != 0 || y % GRID_SIZE != 0)
            throw new IllegalStateException("invalid position, not on grid");
    }

    private int snapToGrid(final int value)
    {
        return value - value % GRID_SIZE;
    }
   
    public int getX()    				{ return x; }
    public int getY()   				{ return y; }

	// Zur Demonstration von Problemen 
	public void setX(final int x)		{ this.x = x; }
	public void setY(final int y)		{ this.y = y; }
}