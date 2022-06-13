package com.khubla.antlr4example;

import java.io.*;
import java.util.List;
import java.util.Map;

import com.khubla.antlr4example.csv.CSVLexer;
import com.khubla.antlr4example.csv.CSVParser;
import com.khubla.antlr4example.csv.CSVToMapListener;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


/**
 * @author Tom Everett
 */
class Main {
   public static void main(String[] args) throws IOException {
      InputStream in = new FileInputStream("D:\\Desktop\\test.csv"); // 1
      Reader r = new InputStreamReader(in);
      ANTLRInputStream inputStream = new ANTLRInputStream(r);
      CSVLexer lexer = new CSVLexer(inputStream);
      CommonTokenStream tokenStream = new CommonTokenStream(lexer);
      CSVParser parser = new CSVParser(tokenStream);
      ParseTree parseTree = parser.file();
      System.out.println(parseTree.toStringTree(parser));

      CSVToMapListener listener = new CSVToMapListener();
      ParseTreeWalker walker = new ParseTreeWalker();
      walker.walk(listener,parseTree);

      List<Map<String, String>> rows = listener.getRows();
      System.out.println(rows);

   }
}