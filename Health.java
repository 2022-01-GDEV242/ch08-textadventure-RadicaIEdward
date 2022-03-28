
/**
 * Health class. Eating food gives health.
 *
 * @author Edward Galindez
 * @version (03/28/2022)
 */
public class Health
{
    // instance variables - replace the example below with your own
    private int health;

    /**
     * Constructor for objects of class Health
     */
    public Health()
    {
        health = 10;
    }

    /**
     * Returns players current health
     * @return The players health points
     */
    public int getHealth()
    {
        return health;
    }
    
    public void setHealth(int healthPoints)
    {
        health = healthPoints;
    }
}
