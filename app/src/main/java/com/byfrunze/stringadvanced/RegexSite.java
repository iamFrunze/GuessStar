package com.byfrunze.stringadvanced;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSite {

    private ArrayList<String> imageArrayList;
    private ArrayList<String> nameArrayList;
    private String htmlCode ;


    public void getInfo(String url){
        ParserSite parserSite = new ParserSite();
        htmlCode = parserSite.callMeth(url);
        FindImg(url, parserSite, htmlCode);
        FindName(url, parserSite, htmlCode);

    }

    public ArrayList<String> getImageArrayList() {
        return imageArrayList;
    }

    public ArrayList<String> getNameArrayList() {
        return nameArrayList;
    }

    private void FindImg(String url, ParserSite parserSite, String htmlCode){
        imageArrayList = new ArrayList<>();
        Pattern patternImage = Pattern.compile("<img src=\"(.*?)\"");
        Matcher matcherImage = patternImage.matcher(htmlCode);
        while(matcherImage.find() ){
            imageArrayList.add(matcherImage.group(1));
        }
    }

    private void FindName(String url, ParserSite parserSite, String htmlCode){
        nameArrayList = new ArrayList<>();
        Pattern patternName = Pattern.compile("alt=\"(.*?)\"/>");
        Matcher matcherName = patternName.matcher(htmlCode);
        while(matcherName.find() ){
            nameArrayList.add(matcherName.group(1));
        }
    }
}
