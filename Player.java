import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (Edward Galindez)
 * @version 03/25/2022
 */
public class Player
{
    private Room currentRoom;
    private Room previousRoom;
    private boolean back; //determines if player has used the back command in the previous room
    private String name;
    private ArrayList<Item> playerItems;
    private int health;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name, Room startingRoom)
    {
        this.name = name;
        currentRoom = startingRoom;
        previousRoom = null;
        playerItems = new ArrayList<Item>();
        health = 10;
        back = false;
    }

    /**
     * Returns the current room the player is in.
     * @return  The player's current room
     */
    public Room getRoom()
    {
        return currentRoom;
    }
    
    /**
     * Sets the player's current room
     * @param room  The room to be set.
     */
    public void setRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Returns the previous room the player was in.
     * @return  The player's previoius room
     */
    public Room getPreviousRoom()
    {
        return previousRoom;
    }
    
    /**
     * Returns the previousRoom room the player was in.
     * @param room The room to be set
     */
    public void setPreviousRoom(Room room)
    {
        previousRoom = room;
    }
    
    /**
     * Returns the value of the back field.
     * @return back
     */
    public boolean getBack() //to where you once belonged
    {
        return back;
    }
    
    /**
     * Sets the back field to false.
     */
    public void setBackFalse()
    {
        back = false;
    }
    
    /**
     * Sets the back field to true.
     */
    public void setBackTrue()
    {
        back = true;
    }
    
    /**
     * Adds a an item to a player's inventory
     */
    public void addItem(Item item)
    {
        playerItems.add(item);
    }
    
    /**
     * Adds an item to a player's inventory
     * @return The playerItems arraylist
     */
    public ArrayList<Item> getPlayerItems()
    {
        return playerItems;
    }
    
    /**
     * Removes an item from a player's inventory
     */
    public void removeItem(Item item)
    {
        playerItems.remove(item);
    }
    
    /**
     * Returns the players current health
     * @return health The players health 
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Adds 10 health to the player after eating food
     */
    public void eatFood()
    {
        health += 10;
    }
    
    /**
     * Removes one health each time a player takes a step (moves from room to room)
     */
    public void takeStep()
    {
        health -= 1;
    }
}
