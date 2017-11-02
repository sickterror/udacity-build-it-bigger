package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeLib {

    private final ArrayList<String> jokesList;
    private String[] jokes;
    private Random random;

    public JokeLib() {
        jokesList = new ArrayList<>();
        jokesList.add("Q: How many prolog programmers does it take to change a lightbulb? A: Yes.");
        jokesList.add("The IT guy says, \"First we should all try getting out of the car, shutting all the doors, then getting back in again.\"");
        jokesList.add("Why do Java developers wear glasses? Because they can't C#");
        jokesList.add("The IT guy says, \"First we should all try getting out of the car, shutting all the doors, then getting back in again.\"");
        jokesList.add("I've got a really good UDP joke to tell you, but I don't know if you'll get it");
        jokesList.add("A programmer puts two glasses on his bedside table before going to sleep. A full one, in case he gets thirsty, and an empty one, in case he doesn’t.");
        jokesList.add("Java and C were telling jokes. It was C's turn, so he writes something on the wall, points to it and says \"Do you get the reference?\" But Java didn't.\n");
        jokesList.add("A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"");
        random = new Random();
    }

    public String[] getJokes() {
        return jokes;
    }

    public String getRandomJoke() {
        return jokesList.get(random.nextInt(jokesList.size()));
    }
}
