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
    private String name;
    private ArrayList<Item> playerItems;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name, Room startingRoom)
    {
        this.name = name;
        currentRoom = startingRoom;
        previousRoom = null;
        playerItems = new ArrayList<Item>();
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
}
