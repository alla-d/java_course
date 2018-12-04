package pl.stqa.java_course.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main (String[] args){
    String[] langs = {"Java", "C#", "Python", "PHP"};

    for (String l : langs) {
      System.out.println("I want learn " + l);
    }

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
    for (String l : languages){
      System.out.println("I want learn " + l);

/*  List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
 */
    }
  }
}
