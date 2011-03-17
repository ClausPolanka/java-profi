package ch04_javagrundlagen;
import java.util.Date;

public class AnonymousInnerClass
{
    public static class Person
    {
        private final String name;

        private final Date   birthday;

        private final String city;

        public Person(final String name, final Date birthday, final String city)
        {
            if (name == null || birthday == null || city == null)
                throw new IllegalArgumentException(
                                                   "parameters 'name', 'birthday' and 'city' must not be null!");

            this.name = name;
            this.birthday = birthday;
            this.city = city;
        }

        public final String getName()
        {
            return name;
        }

        public final Date getBirthDay()
        {
            return birthday;
        }

        public final String getCity()
        {
            return city;
        }

        public boolean equals(Object other)
        {
            if (other == null) // null safe
                return false;

            if (this == other) // reflexive
                return true;

            if (!(this.getClass().equals(other.getClass()))) // same type ?
                return false;

            final Person otherPerson = (Person) other;
            return equalsImpl(otherPerson);
        }

        private boolean equalsImpl(final Person otherPerson)
        {
            return this.getName().equals(otherPerson.getName())
                   && this.getBirthDay().equals(otherPerson.getBirthDay())
                   && this.getCity().equals(otherPerson.getCity());
        }

        public String toString()
        {
            final StringBuffer buf = new StringBuffer();

            buf.append("Person: ");

            buf.append("Name='");
            buf.append(getName());
            buf.append("' ");
            buf.append("City='");
            buf.append(getCity());
            buf.append("' ");
            buf.append("Birthday='");
            buf.append(getBirthDay());
            buf.append("'");

            return buf.toString();
        }
    }

    public static void main(String[] args)
    {
        final Person anonymous = new Person("Anonym", new Date(), "Bern")
        {
            // Variablen sind unsichtbar nach au�en !!!
            int hashValue = -1;
            boolean calculated;

            // Initializer
            {
                calculated = false;
            }
        };
    }
}
