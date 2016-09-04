package com.udacity.gradle.builditbigger.jokes;

import java.util.Random;

public class JokeClass {

    public static String[] JOKE = {

            " Can a kangaroo jump higher than a house? Of course, a house does not jump at all.",
            " Anton, do you think Im a bad mother?\n" +
                    "\n" +
                    "My name is Paul..",
            "My wife cooks so bad , we usually pray after our food.\n",
            "Police officer:Can you identify yourself sir? Driver pulls out his mirror and says: Yes, it is me.",
            "I like to buy a new boomerang please. Also, can you tell me how to throw the old one away?"
    };

    public String getJoke(){
        return JOKE[new Random().nextInt(JOKE.length)];
    }
}
