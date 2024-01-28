
import java.util.Scanner;
import java.util.*;
public class App {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String endProgram = "Restarting...";
        String option1Or2 = "";
        //Using a loop to allow the user to reset the program if they are unhappy with their result
        while (endProgram.equals("Restarting...") || !option1Or2.equals("1") && !option1Or2.equals("2")){

            //Introduce program to user
            System.out.println("Hello, welcome to the decision maker. The goal of this program is to help you decide what you feel like doing! " +
                    "Have you ever been bored and couldn't decide what you wanted to do? Well we're here to help!");
            //Request user input
            System.out.println("To do this, we have 2 options, option 1: You, the user, must provide at least 3 things you may like to do, and we will help you choose 1.");
            System.out.println("Option 2: You can select something to do out of our catalog of options.");
            System.out.println("If you would like to use option 1, please enter '1'. If you would like to use option 2, enter '2'.");

            //Capture users input as String variable
            option1Or2 = input.nextLine();

            //Determine which course of action the user would like to pursue
            if(option1Or2.equals("1")){
                int numOfOptions = 0;
                while(numOfOptions < 3){
                    //Gather user's provided options
                    System.out.println("Please provide at least 3 options for you to choose from " +
                            "(ie. listen to music, watch a movie, do a puzzle, etc.). Separate each " +
                            "individual option with a comma and a space ', ' ");
                    //Separate provided options into an array
                    String[] optionsProvided = input.nextLine().split(", ");
                    numOfOptions = optionsProvided.length;
                    //if input does not meet requirement of 3 sepatate options request input again
                    if(optionsProvided.length < 3){
                        System.out.println("Sorry, it seems your input does not meet the minimum requirements, please try again.");
                    }
                    else{
                        //Run option1 method for user to interact with and end program if they choose to do so
                        if(option1(optionsProvided).equals("end")){
                            endProgram = "end";
                        }
                    }
                }



            } else if (option1Or2.equals("2")) {
                System.out.println("Our catelog is split up into categories. You will first select a category" +
                        " and then we will provide several options to choose from in the selected category.");
                //Run option2 method for user to interact with and end program if they choose to do so
                if(option2().equals("end")){
                    endProgram = "end";
                }
            }else{
                System.out.println("Sorry, your input must be a 1 or 2, please try again.");
            }
        }
    }


    //Method used to get the final result if option 1 is seleceted by the user.
    public static String option1(String[] inputOptions){
        Scanner input = new Scanner(System.in);
        //Initializes loop decision variable
        String endOrNo = "reset";

        //Make a list from the array provided
        List <String> inputToList = new ArrayList<String>(Arrays.asList(inputOptions));
        String randomOrNot = "";
        //loops until user input is an a or b
        while(!randomOrNot.equalsIgnoreCase("a") && !randomOrNot.equalsIgnoreCase("b")){
            //Does the user want us to "flip a coin" and randomly pick or get more in depth
            System.out.println("Would you like us to pick randomly (type 'a') or help you narrow down your decision (type 'b')? ");
            System.out.println("Be sure you type your choice as it is listed above.");
            randomOrNot = input.nextLine();
        }

        if(randomOrNot.equalsIgnoreCase("a")){
            //Select an option from the users choices randomly
            Random random = new Random();
            String randomPick = inputOptions[random.nextInt(inputOptions.length)];
            System.out.println("We have randomly selected " + randomPick + " as your 'what to do' option.");
            System.out.println("If you are unhappy with this result and would like to try again, type 'reset'." +
                    " Otherwise, press enter to end the program. Thanks for using the decision maker!");
            endOrNo = input.nextLine().toLowerCase();
        }
        //Find out more about provided options
        else{
            System.out.println("Do you have a favorite out of your options? " + inputToList + " If so, type that option, if not, type 'no'.");
            String favoriteOrNo = "";
            while (!favoriteOrNo.equals("no") && !inputToList.contains(favoriteOrNo)){
                System.out.println("Be sure you type your choice as it is listed above.");
                favoriteOrNo = input.nextLine().toLowerCase();

            }
            if(favoriteOrNo.equals("no")){
                String mostRecent = "";
                while (!inputToList.contains(mostRecent)){
                    System.out.println("Which option have you done the most recently? " + inputToList + " Select and type 1 of the options listed.");
                    System.out.println("Be sure you type your choice as it is listed above.");
                    mostRecent = input.nextLine().toLowerCase();
                }
                //Narrow down list
                inputToList.remove(mostRecent);
                System.out.println("Let's mix it up a bit and do something you haven't done in a while. We have removed your most recent option. ");
                System.out.println("Briefly, please provide a little more info about each of the remaining options.");
                System.out.println("For example, do any of them sound 'exciting' or 'new' or maybe it's something you have been procrastinating?");
                List<String> infoForInputs = new ArrayList<String>();
                for(int i = 0; i < inputToList.size(); i++){
                    //Create a list of key "trigger" words to search for in the users description that may indicate either a need or desire for their current input
                    List<String> determiningWords = new ArrayList<>(Arrays.asList("exciting", "excited", "awesome", "desire", "like", "new", "procrastinating", "fun", "love",
                            "want", "wish", "miss", "fancy", "need", "should", "great", "motivated", "passionate", "energetic", "dedicated", "dynamic", "interested"));
                    System.out.println("In a few words, describe how you feel about " + inputToList.get(i));
                    infoForInputs.add(input.nextLine().toLowerCase());
                    //loop to check each determining word against the given description
                    for(int j = 0; j < determiningWords.size(); j++){
                        if(infoForInputs.get(i).contains(determiningWords.get(j))){
                            //if the description uses a word in our determiningWord list, select the choice the description is about
                            System.out.println("Based on the info you gave us, we suggest you " + inputToList.get(i));
                            System.out.println("If you are unhappy with this result and would like to try again, type 'reset'." +
                                    " Otherwise, press enter to end the program. Thanks for using the decision maker!");
                            endOrNo = input.nextLine().toLowerCase();
                            //if to determine whether or not to reset the program
                            if(endOrNo.equalsIgnoreCase("reset")){
                                return "Restarting...";
                            }
                            return "end";
                        }
                    }
                }
                System.out.println("Unfortunately, your descriptions did not lean heavily toward any of your " +
                        "given options.");
                if(inputToList.size() == 2){
                    System.out.println("At this point, we are down to 2 options and would like to flip " +
                            "a coin to help you make your final decision");
                }else{
                   //Initialize variable to catch user input
                    String[] last2 = new String[2];
                    while ((true)){
                        System.out.println("At this point, we would like you to select 2 of your remaining options, and we will flip a coin " +
                                "to determine a final decision for you.");
                        System.out.println("Please enter the 2 selections you would like to flip for " +
                                "separated by a comma and space ', '");
                        System.out.println("As a reminder, here are your remaining options: " + inputToList);
                        //Catches input in Array
                        last2 = input.nextLine().split(", ");
                        //Makes sure input is split into 2 and both are included in the remaining options
                        if(inputToList.contains(last2[0]) && inputToList.contains(last2[1])){
                            break;
                        }
                    }
                    //Removes everything from inputToList and adds last2 options that we just confirmed were the choices the user wanted to flip between
                    inputToList.clear();
                    inputToList.add(last2[0]);
                    inputToList.add(last2[1]);
                    }

                //Tells user we will flip a coin to decide what they should choose to do
                System.out.println("We will pick heads for " + inputToList.get(0) +
                        " and tails for " + inputToList.get(1));
                //Makes an array for our random variable to choose between
                String[] headsOrTails = new String[] {"Heads", "Tails"};
                //Random variable
                Random coinFlip = new Random();
                String flipResult = headsOrTails[coinFlip.nextInt(headsOrTails.length)];
                System.out.println("It's " + flipResult + "! ");
                // if and else statements to give different results based on what the coin lands on
                if(flipResult.equals("Heads")){
                    System.out.println("The coin has spoken, so our final choice is " + inputToList.get(0));

                }
                else{
                    System.out.println("The coin has spoken, so our final choice is " + inputToList.get(1));

                }
                System.out.println("If you are unhappy with this result and would like to try again, type 'reset'." +
                        " Otherwise, press enter to end the program. Thanks for using the decision maker!");
                endOrNo = input.nextLine().toLowerCase();

            }
            //Result if user selected a favorite towards the beginning of the program
            else{
                System.out.println("Since you have a favorite, we recommend you stick to what you love for now, which is " + favoriteOrNo + ".");
                System.out.println("If you are unhappy with this result and would like to try again, type 'reset'." +
                        " Otherwise, press enter to end the program. Thanks for using the decision maker!");
                endOrNo = input.nextLine().toLowerCase();

            }

        }

        //if to determine whether or not to reset the program
        if(endOrNo.equalsIgnoreCase("reset")){
            return "Restarting...";
        }
        return "end";
    }


    public static String option2(){
        Scanner input = new Scanner(System.in);
        String endOrNo = "reset";

        //Make a list that creates categories for the user to pick from
        List<String> toDoCategories = new ArrayList<>();
        toDoCategories.add("watch a tv show");
        toDoCategories.add("watch an anime");
        toDoCategories.add("watch a movie");
        toDoCategories.add("play a board game");
        toDoCategories.add("play a card game");
        toDoCategories.add("play a video game");
        toDoCategories.add("get something to eat");
        toDoCategories.add("go out");
        toDoCategories.add("do chores");
        //Display all categories for user to pick from
        System.out.println("Please select one of the given categories, and type it as read. " + toDoCategories);
        String chosenCategory = input.nextLine().toLowerCase();

        List<String> optionsInCategory = new ArrayList<>();
        //Method takes input given by user and returns a list of options in the requested category
        //Stores the value returned from method in new list
        optionsInCategory = new ArrayList<>(listForGivenCategory(chosenCategory ,toDoCategories));
        //Mehod (listForGivenCategory) also prompts the user to input their choice from the given list, or to let us select randomly
        //This way the communication with the program can be more clear and direct for each individual category without making a big mess in this method
        String choiceFromOptions = input.nextLine().toLowerCase();
        //If input from user does not match any options, program will start over at point of input
        while (!optionsInCategory.contains(choiceFromOptions) && !choiceFromOptions.equalsIgnoreCase("random")){
            //Print a line reminding user to type answer as displayed
            System.out.println("Sorry, that input did not come through properly, please try again, and be sure to type" +
                    " your choice as it is displayed.");
            System.out.println("Here are those options again: ");
            System.out.println(optionsInCategory);
            choiceFromOptions = input.nextLine().toLowerCase();
        }


        if(choiceFromOptions.equals("random")){
            //randomly select an option from tv show list
            Random randomChoice = new Random();
            String randomSelection = optionsInCategory.get(randomChoice.nextInt(optionsInCategory.size()));
            choiceFromOptions = randomSelection;
            System.out.println("We have selected " + choiceFromOptions);
        }
        else{
            System.out.println("You have selected " + choiceFromOptions);
        }

        //Determines if user would like to end after using option 2 or try again
        System.out.println("Enjoy!");
        System.out.println("If you are unhappy with this result and would like to try again, type 'reset'." +
                " Otherwise, press enter to end the program. Thanks for using the decision maker!");
        endOrNo = input.nextLine().toLowerCase();
        if(endOrNo.equalsIgnoreCase("reset")){
            return "Restarting...";
        }
        return "end";
    }

    //Options in category data for option 2
    public static ArrayList<String> listForGivenCategory(String givenCategory, List<String> toDoCategories){
        Scanner input = new Scanner(System.in);
        //Confirm givenCategory can be found in toDoCategories, loop until it is
        while (!toDoCategories.contains(givenCategory)){
            System.out.println("Sorry, we were unable to match your input with any of the provided options. " +
                    "Please try again.");
            System.out.println("Here are your options:");
            System.out.println(toDoCategories);
            givenCategory = input.nextLine();
        }
        //initialize list for return
        List<String> optionsForGivenCategory = new ArrayList<>();

        //if statements to determine which category we are filling

        if(givenCategory.equals("watch a tv show")){
            optionsForGivenCategory.add("the office");
            optionsForGivenCategory.add("parks and rec");
            optionsForGivenCategory.add("how i met your mother");
            optionsForGivenCategory.add("scrubs");
            optionsForGivenCategory.add("greys anatomy");
            optionsForGivenCategory.add("stranger things");
            optionsForGivenCategory.add("breaking bad");
            optionsForGivenCategory.add("lucifer");
            optionsForGivenCategory.add("dexter");
            optionsForGivenCategory.add("game of thrones");
            optionsForGivenCategory.add("westworld");
            optionsForGivenCategory.add("black mirror");

            System.out.println("You have chosen to watch a tv show, please select a tv show from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);


        } else if (givenCategory.equals("watch an anime")) {
            optionsForGivenCategory.add("naruto");
            optionsForGivenCategory.add("dragonball z");
            optionsForGivenCategory.add("demon slayer");
            optionsForGivenCategory.add("attack on titan");
            optionsForGivenCategory.add("fullmetal alchemist");
            optionsForGivenCategory.add("one punch man");
            optionsForGivenCategory.add("death note");
            optionsForGivenCategory.add("cowboy bebop");
            optionsForGivenCategory.add("jujutsu kaisen");
            optionsForGivenCategory.add("one piece");
            optionsForGivenCategory.add("hunter x hunter");
            optionsForGivenCategory.add("the seven deadly sins");

            System.out.println("You have chosen to watch an anime, please select an anime from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);
        }

        else if (givenCategory.equals("watch a movie")) {
            optionsForGivenCategory.add("the hunger games");
            optionsForGivenCategory.add("the avengers");
            optionsForGivenCategory.add("the dark knight");
            optionsForGivenCategory.add("legally blonde");
            optionsForGivenCategory.add("step brothers");
            optionsForGivenCategory.add("deadpool");
            optionsForGivenCategory.add("caddy shack");
            optionsForGivenCategory.add("saving private ryan");
            optionsForGivenCategory.add("inception");
            optionsForGivenCategory.add("halloween");
            optionsForGivenCategory.add("pirates of the caribbean");
            optionsForGivenCategory.add("the matrix");

            System.out.println("You have chosen to watch a movie, please select a movie from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);
        }

        else if (givenCategory.equals("play a board game")) {
            optionsForGivenCategory.add("sorry");
            optionsForGivenCategory.add("monopoly");
            optionsForGivenCategory.add("battleship");
            optionsForGivenCategory.add("the game of life");
            optionsForGivenCategory.add("guess who");
            optionsForGivenCategory.add("clue");
            optionsForGivenCategory.add("candy land");
            optionsForGivenCategory.add("risk");
            optionsForGivenCategory.add("chess");
            optionsForGivenCategory.add("checkers");
            optionsForGivenCategory.add("backgammon");
            optionsForGivenCategory.add("chutes and ladders");

            System.out.println("You have chosen to play a board game, please select a game from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);
        }

        else if (givenCategory.equals("play a card game")) {
            optionsForGivenCategory.add("war");
            optionsForGivenCategory.add("go fish");
            optionsForGivenCategory.add("solitaire");
            optionsForGivenCategory.add("poker");
            optionsForGivenCategory.add("blackjack");
            optionsForGivenCategory.add("euchre");
            optionsForGivenCategory.add("rummy");
            optionsForGivenCategory.add("cards against humanity");
            optionsForGivenCategory.add("uno");
            optionsForGivenCategory.add("magic the gathering");
            optionsForGivenCategory.add("pokemon");
            optionsForGivenCategory.add("yu-gi-oh");

            System.out.println("You have chosen to play a card game, please select a game from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);
        }

        else if (givenCategory.equals("play a video game")) {
            optionsForGivenCategory.add("elden ring");
            optionsForGivenCategory.add("god of war");
            optionsForGivenCategory.add("call of duty");
            optionsForGivenCategory.add("halo");
            optionsForGivenCategory.add("lethal company");
            optionsForGivenCategory.add("fortnite");
            optionsForGivenCategory.add("minecraft");
            optionsForGivenCategory.add("assassins creed");
            optionsForGivenCategory.add("spiderman");
            optionsForGivenCategory.add("farcry");
            optionsForGivenCategory.add("rocket league");
            optionsForGivenCategory.add("fallout");

            System.out.println("You have chosen to play a video game, please select a game from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);
        }

        else if (givenCategory.equals("get something to eat")) {
            optionsForGivenCategory.add("pizza");
            optionsForGivenCategory.add("mexican food");
            optionsForGivenCategory.add("chinese food");
            optionsForGivenCategory.add("italian food");
            optionsForGivenCategory.add("fast food");
            optionsForGivenCategory.add("tv dinner");
            optionsForGivenCategory.add("popcorn");
            optionsForGivenCategory.add("chips and dip");
            optionsForGivenCategory.add("cookies and milk");
            optionsForGivenCategory.add("ice cream");
            optionsForGivenCategory.add("ramen noodles");
            optionsForGivenCategory.add("fresh salad");

            System.out.println("You have chosen to get something to eat, please select a food from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);
        }
        else if (givenCategory.equals("go out")) {
            optionsForGivenCategory.add("hang with friends");
            optionsForGivenCategory.add("go bowling");
            optionsForGivenCategory.add("go to the movies");
            optionsForGivenCategory.add("go out to dinner");
            optionsForGivenCategory.add("go to the arcade");
            optionsForGivenCategory.add("go to the mall");
            optionsForGivenCategory.add("go to the grocery store");
            optionsForGivenCategory.add("go rollerskating");
            optionsForGivenCategory.add("go ice skating");
            optionsForGivenCategory.add("go sledding");
            optionsForGivenCategory.add("go swimming");
            optionsForGivenCategory.add("go for a hike");

            System.out.println("You have chosen to go out, please select an option from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);
        }

        else if (givenCategory.equals("do chores")) {
            optionsForGivenCategory.add("mop");
            optionsForGivenCategory.add("vacuum");
            optionsForGivenCategory.add("dust");
            optionsForGivenCategory.add("pay bills");
            optionsForGivenCategory.add("do laundry");
            optionsForGivenCategory.add("wash the dishes");
            optionsForGivenCategory.add("take out the trash");
            optionsForGivenCategory.add("feed the pets");
            optionsForGivenCategory.add("work on homework");
            optionsForGivenCategory.add("mow the lawn");
            optionsForGivenCategory.add("shovel the driveway");
            optionsForGivenCategory.add("make your bed");

            System.out.println("You have chosen to do chores, please select a chore from the list provided" +
                    " and type the name of it as seen in the following list or, if " +
                    "you would like us to randomly select one for you, type 'random'.");
            System.out.println(optionsForGivenCategory);
        }




        return (ArrayList<String>) optionsForGivenCategory;
    }

}
