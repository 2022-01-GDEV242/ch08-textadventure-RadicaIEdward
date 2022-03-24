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
    private Room currentRoom;
    
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
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //City
        Room emptyField1, emptyField2, emptyField3, emptyField4, emptyField5, cityEntrance, twoPitchersPub, 
        touristCenter, policeStation, scaryAlley, scaryAlley2, signery, townSquare;
        
        Item apple, orange, lime, dentedGoblet, arrow, oldShield, dullSword, compiler, brokenLaserGun;
        
        apple = new Item("apple", "A sweet, delicious apple.", 1);
        orange = new Item("orange", "A sweet, tangy orange.", 1);
        lime = new Item("lime", "A sweet yet tart lime.", 1);
        dentedGoblet = new Item("dented goblet", "A busted, old goblet.", 2);
        arrow = new Item("arrow", "An arrow.", 1);
        oldShield = new Item("shield", "A very old shield.", 3);
        dullSword = new Item("sword", "A sword that could barely cut butter.", 4);
        compiler = new Item("compiler", "A nerd translator.", 1);
        brokenLaserGun = new Item("laserGun", "A laser gun in need of repair.", 2);
        
        //Castle
        Room castleEntrance, ballroom, grandHall, throneRoom, guardShack, hiddenRoom;
        
        // create the rooms
        emptyField5 = new Room("in an empty field. In the distance you can see a large city of some kind.");
        emptyField4 = new Room("in an empty field. You are closer to the city in the distance.");
        emptyField3 = new Room("in an empty field. You are even closer to the city in the distance.");
        emptyField2 = new Room("in an empty field. You are ever closer to the city in the distance.");
        emptyField1 = new Room("in an empty field. The city is no longer in the distance.");
        cityEntrance = new Room(" at the city entrance");
        townSquare = new Room("in the town square");
        twoPitchersPub = new Room("in the local pub, The Two Pitchers");
        scaryAlley = new Room("in a scary alley");
        scaryAlley2 = new Room("further in a scary alley");
        touristCenter = new Room("in the city tourist center");
        policeStation = new Room("in the police station");
        signery = new Room("at the sign shop");
        
        //castle
        castleEntrance = new Room("at the entrance to the castle");
        ballroom = new Room("in the castle ballroom");
        grandHall = new Room("in the Grand Hall");
        throneRoom = new Room("in the throne room");
        guardShack = new Room("in the castle guard shack");
        hiddenRoom = new Room("in a hidden room");
        
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

        currentRoom = emptyField5;  // start game outside
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
        System.out.println(currentRoom.getLongDescription());
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
                
            case EAT:
                eat(command);
                break;
                
            case LOOK:
                look(command);
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

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
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
     * Look around the room the player is currently in.
     * @return 
     */
    private void look(Command command) 
    {
        System.out.println("You look with your eyes in an attempt to see things...");
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.getLongDescription());
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



