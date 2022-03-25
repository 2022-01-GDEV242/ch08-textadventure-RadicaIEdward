import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Edward Galindez
 * @version 03/21/2022
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    private Item item;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "Your location is " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) 
        {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Print out the available exits and their descriptions.
     */
    public void getExitDescriptions()
    {
        Set<String> keys = exits.keySet();
        for(String exit : keys) 
        {
            String exitDescription = exits.get(exit).getShortDescription();
            System.out.println("When you look " + exit + " you see " + exitDescription);
        }
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Display a room's items
     * 
     */
    public void displayItems() 
    {
        System.out.print("This room contains: ");
        for(Item item : items) 
        {
            System.out.print("An " + item.getItemName() + ". ");
        }
        
    }
    
    /**
     * Adds items to a room
     *
     */
    public void addItem(Item item) 
    {
        items.add(item);
    }
    
    /**
     * Display a room's items
     * 
     */
    public Item getItem() 
    {
        return item;
    }
    
    /**
     * Display a room's items
     * 
     */
    public boolean hasItem() 
    {
        if (items.size() != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

