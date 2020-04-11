/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
***/
package com.example.functionalprograminginjava.compare.fpij;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class ListSelectFiles {
  public static void main(String[] args) throws IOException {

{
      final String[] files = 
        new File("fpij").list(new java.io.FilenameFilter() {
          public boolean accept(final File dir, final String name) {
            return name.endsWith(".java");
          }
        });
  /*
  System.out.println(files);
  */
}

    Files.newDirectoryStream(
             Paths.get("fpij"), path -> path.toString().endsWith(".java"))
         .forEach(System.out::println);
  }
}