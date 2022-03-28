import java.util.ArrayList;
import java.util.Iterator;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Edward Galindez
 * @version 03/21/2022
 */

public class Game 
{
    private Parser parser;
    private Room startingRoom;
    private Player player;
    
    public static void main(String[] args) 
    {
       Game game = new Game();
       game.play();
    }
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        player = new Player("Sir Playersworth", startingRoom);
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together. Create room items.
     */
    private void createRooms()
    {
        //City Rooms
        Room emptyField1, emptyField2, emptyField3, emptyField4, emptyField5, cityEntrance, twoPitchersPub, 
        touristCenter, policeStation, scaryAlley, scaryAlley2, signery, townSquare;
        
        //Castle Rooms
        Room castleEntrance, ballroom, grandHall, throneRoom, guardShack, hiddenRoom;
        
        //create the rooms
        emptyField5 = new Room("an empty field. In the distance you can see a large city of some kind.");
        emptyField4 = new Room("an empty field. You are closer to the city in the distance.");
        emptyField3 = new Room("an empty field. You are even closer to the city in the distance.");
        emptyField2 = new Room("an empty field. You are ever closer to the city in the distance.");
        emptyField1 = new Room("an empty field. The city is no longer in the distance.");
        cityEntrance = new Room("the city entrance");
        townSquare = new Room("the town square");
        twoPitchersPub = new Room("the local pub, The Two Pitchers");
        scaryAlley = new Room("a scary alley");
        scaryAlley2 = new Room("further in a scary alley");
        touristCenter = new Room("the city tourist center");
        policeStation = new Room("the police station");
        signery = new Room("the sign shop");
        
        //castle
        castleEntrance = new Room("the entrance to the castle");
        ballroom = new Room("the castle ballroom");
        grandHall = new Room("the Grand Hall");
        throneRoom = new Room("the throne room");
        guardShack = new Room("the castle guard shack");
        hiddenRoom = new Room("a hidden room");
        
        // initialise room exits
        
        // city exits
        emptyField5.setExit("north", emptyField4);
        
        emptyField4.setExit("north", emptyField3);
        emptyField4.setExit("south", emptyField5);
        
        emptyField3.setExit("north", emptyField2);
        emptyField3.setExit("south", emptyField4);
        
        emptyField2.setExit("north", emptyField1);
        emptyField2.setExit("south", emptyField3);
        
        emptyField1.setExit("north", cityEntrance);
        emptyField1.setExit("south", emptyField2);
        
        cityEntrance.setExit("north", townSquare);
        cityEntrance.setExit("south", emptyField1);
        
        townSquare.setExit("north", signery);
        townSquare.setExit("south", cityEntrance);
        townSquare.setExit("east", twoPitchersPub);
        townSquare.setExit("west", scaryAlley);
        
        twoPitchersPub.setExit("east", policeStation);
        twoPitchersPub.setExit("west", townSquare);

        policeStation.setExit("west", twoPitchersPub);
        
        signery.setExit("south", townSquare);
        
        scaryAlley.setExit("east", townSquare);
        scaryAlley.setExit("west", scaryAlley2);
        
        scaryAlley2.setExit("north", castleEntrance);
        scaryAlley2.setExit("east", scaryAlley);
        
        // castle exits
        castleEntrance.setExit("north", grandHall);
        castleEntrance.setExit("south", scaryAlley2);
        
        grandHall.setExit("north", throneRoom);
        grandHall.setExit("south", castleEntrance);
        grandHall.setExit("east", guardShack);
        grandHall.setExit("west", hiddenRoom);
        
        throneRoom.setExit("south", grandHall);
        
        guardShack.setExit("east", ballroom);
        guardShack.setExit("west", grandHall);
        
        ballroom.setExit("west", guardShack);
        
        hiddenRoom.setExit("east", grandHall);

        startingRoom = emptyField5;  // game starting point
        
        //Create Room Items
        Item apple, orange, lime, dentedGoblet, arrow, oldShield, dullSword, compiler, brokenLaserGun;
        
        //Initialize Items
        apple = new Item("apple", "A sweet, delicious apple.", 1);
        orange = new Item("orange", "A sweet, tangy orange.", 1);
        lime = new Item("lime", "A sweet, yet tart, lime.", 1);
        dentedGoblet = new Item("dented goblet", "A busted, old goblet.", 2);
        arrow = new Item("arrow", "An arrow.", 1);
        oldShield = new Item("shield", "A very old shield.", 3);
        dullSword = new Item("sword", "A sword that can barely cut butter.", 4);
        compiler = new Item("compiler", "A nerd translator.", 1);
        brokenLaserGun = new Item("laserGun", "A laser gun in need of repair.", 2);
        
        //add items to rooms
        emptyField4.addItem(apple);
        emptyField4.addItem(orange);
        
        townSquare.addItem(compiler);
        
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("Your phone alarm goes off, as it does every morning.");
        System.out.println("As you hit the snooze button for the fifth time, slowly you begin");
        System.out.println("to realize that your sheets feel surpringly like grass...");
        System.out.println("You open your eyes to discover you are still dreaming. I mean... you kind of have to be.");
        System.out.println("You absolutely MUST be dreaming, because you are in the middle of an empty field.");
        System.out.println("Your phone alarm goes off again and you hit the snooze button for the umpteenth time.");
        System.out.println("It's too early for this false awakening crap and you have better things to do,");
        System.out.println("such as hitting the snooze and being increasingly late for work. You go back to sleep...");
        System.out.println("Five minutes later, your phone continues to suggest that it is, in fact, time to wake up.");
        System.out.println("Your phone is usually willing to play this game all morning, but not today. The snooze button");
        System.out.println("ceases to work altogether. There is a certain level of urgency in this alarm. You open your eyes.");
        System.out.println("You are pretty sure you are awake this time, but you are also pretty sure that you are");
        System.out.println("definitely still laying in the middle of a field. This is concerning to you, for various reasons.");
        System.out.println("Your phone is blinking, making random noises, and vibrating. You look at it... it says:");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) 
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
            
            case BACK:
                goBack();
                break;
                
            case EAT:
                eat(command);
                break;
                
            case LOOK:
                look(command);
                break;
                
            case TAKE:
                take(command);
                break;
                
            case DROP:
                drop(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the world.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room currentRoom = player.getRoom();
        Room nextRoom = player.getRoom().getExit(direction);
        // Try to leave the current room
        if (nextRoom == null) 
        {
            System.out.println("You can't go that way!");
        }
        else 
        {
            player.setBackFalse();
            player.setPreviousRoom(currentRoom);
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
            if(player.getRoom().hasItem())
            {
                player.getRoom().displayItems();
            }
        }
    }
    
    /**
     * Sends the player bsack to the previos room they were in.
     */
    private void goBack()
    {
        if(player.getBack())
        {
            System.out.println("You JUST went back! Are you trying to go forward?! That's not a command!");
            System.out.println("Pick a direction, you meandering fool!!!");
        }    
        else
        {
            player.setBackTrue();
            System.out.println("You return to your previous location... did you drop something?");
            Room previousRoom = player.getRoom();
            player.setPreviousRoom(previousRoom);
            if(player.getRoom().hasItem())
            {
                player.getRoom().displayItems();
            }
            System.out.println(player.getRoom().getLongDescription());
        }
    }
    
    /** 
     * Eats player specified food, from inventory or current room.
     */
    private void eat(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what we are eating
            System.out.println("Eat what?");
            return;
        }
        String food = command.getSecondWord();
        System.out.println("You eat the " + food + ". You wish you had more...");
    }
    
    /** 
     * Looks around the player's current location and describes their surroundings. 
     */
    private void look(Command command) 
    {
        System.out.println("You look with your eyes in an attempt to see things...");
        if(player.getRoom().hasItem())
        {
            player.getRoom().getExitDescriptions();
            if(player.getRoom().hasItem())
            {
                System.out.print("You also see: ");
                player.getRoom().displayItems();
            }
        }
        else
        {
            player.getRoom().getExitDescriptions();
        }
    }

    /** 
     * Takes an item from a room and places it in a player's inventory 
     */
    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what now?");
            return;
        }
        String itemName = command.getSecondWord();
        ArrayList<Item> roomItems = player.getRoom().getItems();
        Iterator<Item> it = roomItems.iterator();
        Item currentItem = null;
        String currentItemName = "";
        if (player.getRoom().getItems().size() == 0) 
        {
            System.out.println("You really have gone mad. There's no " + itemName + " here!");
        }
        else  
        {
            while(it.hasNext()) 
            {
                currentItem = it.next();
                currentItemName = currentItem.getItemName(); 
                if(currentItemName.equals(itemName))
                {
                    player.addItem(currentItem); // add item to player inventory
                    it.remove();// remove item from room
                    break;
                }    
            }
            System.out.println("You take the " + itemName + " and place it in your bag.");
        }
    }
    
    /** 
     * Drops an item from a players inventory
     */
    private void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Drop what where?");
            return;
        }
        String itemName = command.getSecondWord();
        ArrayList<Item> playerItems = player.getPlayerItems();
        Iterator<Item> it = playerItems.iterator();
        Item currentItem = null;
        String currentItemName = "";
        if (player.getPlayerItems().size() == 0) 
        {
            System.out.println("You search your pockets. You have NOTHING!");
        }
        else  
        {
            while(it.hasNext()) 
            {
                currentItem = it.next();
                currentItemName = currentItem.getItemName(); 
                if(currentItemName.equals(itemName))
                {
                    it.remove(); // remove item from player inventory
                    player.getRoom().addItem(currentItem); // place item in room
                    break;
                }    
            }
            System.out.println("You take the " + itemName + " and place it in your bag.");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) 
        {
            System.out.println("Quit what?");
            return false;
        }
        else 
        {
            return true;  // signal that we want to quit
        }
    }
}