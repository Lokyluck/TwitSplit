package com.dwarves.main.utils;

import java.util.ArrayList;

public class CommonUtils {

    /**
     * Split text into many chunks, a chunk has size smaller than 50
     * */
    public static ArrayList<String> splitMessage(String text) {

        // Find number of sub-messages
        int numOfSubMsg = getNumberOfSubMessages(text);
        //int numOfSubMsg = (int) Math.ceil(textLength/ 49);

        // Init variables
        ArrayList<String> subMessageList = new ArrayList<>();
        String[] words = text.split(" ");
        int i = 0;
        StringBuilder strBuilder = new StringBuilder();
        int currentSubMsgPos = 1;
        String indicator;

        // Split message into many sub-messages
        while (i < words.length) {
            if (strBuilder.length() == 0) {
                indicator = String.valueOf(currentSubMsgPos) + "/" + String.valueOf(numOfSubMsg) + " ";
                strBuilder.append(indicator);
            }

            int length = strBuilder.length();

            if (length <= 49) {
                strBuilder.append(words[i]);
                if (i == words.length -1) {
                    // Add the last sub message to list
                    String subMessage = strBuilder.toString();
                    if (subMessage.length() > 0)
                        subMessageList.add(subMessage);
                } else {
                    if (strBuilder.length() < 49)
                        strBuilder.append(" ");
                }
                i++;

            } else {
                // Delete the recent word which is just appended
                String recentWord = words[i -1];
                int begin = length - recentWord.length();
                strBuilder.delete(begin, length);

                // Add the sub message to list
                String subMessage = strBuilder.toString();
                if (subMessage.length() > 0) {
                    subMessageList.add(subMessage);
                    currentSubMsgPos ++;
                }
                // Clear strBuilder variable to add new sub-message and
                strBuilder = new StringBuilder();
                i--;
            }
        }

        return subMessageList;
    }


    /**
     * Calculate number of sub-messages
     * when the message is added indicator texts, example: "1/2 {sub-message 1}", "2/2 {sub-message 2}"
     * */
    private static int getNumberOfSubMessages(String text) {
        int numOfSubMsg;
        double pureTextLength = text.length();
        int chunksNumber = (int) Math.ceil(pureTextLength/ 49);
        double fixedTextLength
                = chunksNumber * ("x/y ".length()) + pureTextLength;
        numOfSubMsg = (int) Math.ceil(fixedTextLength/ 49);
        return numOfSubMsg;
    }

}
