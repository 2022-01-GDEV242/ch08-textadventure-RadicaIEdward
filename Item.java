
/**
 * Item - an item in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * An "Item" represents one item within room. An item has a description and a weight
 * 
 * @author  Edward Galindez
 * @version 03/23/2022
 */

public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private int weight;
    /**
     * Create an Item object
     */
    public Item(String name, String description, int weight)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Gets the name of the item
     * @return The item's name  
     */
    public String getItemName()
    {
        return name;
    }
    
    /**
     * Gets the description of the item
     * @return The item's description  
     */
    public String getItemDescription()
    {
        return description;
    }
    
    /**
     * Gets the name of the item
     * @return  The item's name  
     */
    public int getItemWeight()
    {
        // put your code here
        return weight;
    }
}
