package com.dwarves.main.utils;

import android.content.Context;
import com.dwarves.main.ui.SplitMessageResult;

public class CommonUtils {

    /**
     * Split text into many chunks, a chunk has size smaller than 50
     * */
    public static SplitMessageResult splitMessage (String text) {
        SplitMessageResult subMessageList = new SplitMessageResult();
        subMessageList.setIsSucess(true);

        // If a user's input is less than or equal to 50 characters, post it as is
        if (text.length() <= 50) {
            subMessageList.add(text);
            return subMessageList;
        }

        // Else split it into sub-messages

        // Find number of sub-messages
        int numOfSubMsg = getNumberOfSubMessages(text);
        //int numOfSubMsg = (int) Math.ceil(textLength/ 49);

        // Init variables
        String[] words = text.split(" ");
        int i = 0;
        int numOfWordsInSubMsg = 0;
        StringBuilder strBuilder = new StringBuilder();
        int currentSubMsgPos = 1;
        String indicator;

        // Split message into many sub-messages
        while (i < words.length) {

            // Check if a word has length greater than 50
            if (words[i].length() > 50) {
                subMessageList.clear();
                subMessageList.setIsSucess(false);
                subMessageList.setErrorCode(1);
                return subMessageList;
            }

            // Add indicator text
            if (strBuilder.length() == 0) {
                indicator = String.valueOf(currentSubMsgPos) + "/" + String.valueOf(numOfSubMsg) + " ";
                strBuilder.append(indicator);
            }

            strBuilder.append(words[i]);
            numOfWordsInSubMsg ++;
            int length = strBuilder.length();

            if (length <= 49)  {
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
                if (numOfWordsInSubMsg > 1) {
                    // Delete the recent word which is appended before
                    String recentWord = words[i];
                    int begin = length - recentWord.length();
                    strBuilder.delete(begin, length);

                    // Add the sub message to list
                    String subMessage = strBuilder.toString();
                    if (subMessage.length() > 0) {
                        subMessageList.add(subMessage);
                        currentSubMsgPos++;
                        numOfWordsInSubMsg = 0;
                    }
                    // Clear strBuilder variable to add new sub-message and
                    strBuilder = new StringBuilder();
                } else {
                    subMessageList.clear();
                    subMessageList.setIsSucess(false);
                    subMessageList.setErrorCode(2);
                    break;
                }
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
        double fixedTextLength;

        if (chunksNumber < 10) {
            fixedTextLength
                    = chunksNumber * ("x/y ".length()) + pureTextLength;
        } else {
            int tmp = chunksNumber % 10;
            fixedTextLength =
                    tmp * ("x/yy ".length())
                    + (chunksNumber - tmp) * ("xx/yy ".length()) + pureTextLength;
            // Users can't input text with length greater than 100 * 49 characters one time
            // so it isn't necessary to handle case "x/yyy", "xx/yyy",...
        }
        numOfSubMsg = (int) Math.ceil(fixedTextLength / 49);
        return numOfSubMsg;
    }

}
