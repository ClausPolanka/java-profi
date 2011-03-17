package ch12_entwurfsmuster;

import java.util.ArrayList;
import java.util.List;

/**
 * Beispielprogramm zur Demonstration des Kompositum-Musters
 * <br>
 * Die Klasse <code>KompositumExample</code> zeigt die hierarchische Definition
 * eines Projekts.
 * Jede Teil-Komponente erfüllt das durch die abstrakte Basisklasse <code>ProjectComponent</code>
 * vorgegebene Interface mit der abstrakten Methode <code>calcCosts()</code>.
 * Zur Demonstaration wird ein Projekt auf verschiedenen Teilen und Gruppen zusammengebaut
 * und anschließend, von der eigentlichen Projektkomponente abstrahiert, die eigentliche
 * Kostenberechnung durchgeführt, die wiederum die gemeinsame Basisklasse nutzt, um diese
 * Aufgabe zu erfüllen.
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
public final class KompositumExample
{
    public static void main(final String[] args)
    {
        final int SEVEN_DAYS = 7;
        final int FOUR_DAYS = 4;
        final ProjectComponent project1 = new Project("Seven", SEVEN_DAYS);
        final ProjectComponent project2 = new Project("Four", FOUR_DAYS);

        final ProjectGroup projectGroup = new ProjectGroup("Seven+Four");
        projectGroup.add(project1);
        projectGroup.add(project2);

        final ProjectGroup mainProject = new ProjectGroup("Main");
        mainProject.add(projectGroup);
        
        printCosts(project1);
        printCosts(project2);
        printCosts(projectGroup);
        printCosts(mainProject);
    }

    private static void printCosts(final ProjectComponent projectComponent)
    {
        System.out.println("Cost of project '" + projectComponent.getName() + "': " + projectComponent.calcCosts());
    }
    
    public static abstract class ProjectComponent
    {
        private final String name;

        public ProjectComponent(final String name)
        {
            this.name = name;
        }

        public abstract int calcCosts();

        public String getName()
        {
            return name;
        }
    }

    public static class Project extends ProjectComponent
    {
        private final int costs;

        public Project(final String name, final int costs)
        {
            super(name);
            this.costs = costs;
        }

        @Override
        public int calcCosts()
        {
            return costs;
        }
    }

    public static class ProjectGroup extends ProjectComponent
    {
        final List<ProjectComponent> subprojects = new ArrayList<ProjectComponent>();

        public ProjectGroup(final String name)
        {
            super(name);
        }

        @Override
        public int calcCosts()
        {
            int costs = 0;
            for (final ProjectComponent current : subprojects)
            {
                costs += current.calcCosts();
            }

            return costs;
        }

        public void add(final ProjectComponent projectComponent)
        {
            subprojects.add(projectComponent);
        }
    }    
}
