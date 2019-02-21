package com.dwarves.main;

import com.dwarves.main.ui.SplitMessageResult;
import com.dwarves.main.utils.CommonUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SplitTest {

    // Stub some input messages to cover all test cases
    private static final String INPUT_MESSAGE_1 = "Dwarves Foundation";

    private static final String INPUT_MESSAGE_2
            = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.";

    // Stub an invalid input message 1, has a word with length greater than 50
    private static final String INVALID_MESSAGE_1
            = "Invalid text: Ican'tbelieveTweeternowsupportschunkingmymessages,soIdon'thavetodoitmyself.";

    // Stub an invalid input message 2, has a word with length greater than 50 after add indicator text
    private static final String INVALID_MESSAGE_2
            = "Invalid text: Ican'tbelieveTweeternowsupportschunkingmymessages This message has all word shorter than 50. But after add " +
            "indicator text, it has a word with length longer 50";




    /**
     * Split message with text length smaller than 50
     * */
    @Test
    public void splitMessage1() {
        SplitMessageResult subMessageList = CommonUtils.splitMessage(INPUT_MESSAGE_1);
        assertTrue(subMessageList.isSuccess());
        assertEquals(1, subMessageList.size());
    }

    /**
     * Split message with text length greater or equal 50, and split into 2 sub-messages
     * */
    @Test
    public void splitMessage2() {
        SplitMessageResult subMessageList = CommonUtils.splitMessage(INPUT_MESSAGE_2);
        assertTrue(subMessageList.isSuccess());
        assertEquals(2, subMessageList.size());
    }

    /**
     * Split an invalid message that has a word with length greater than 50
     * */
    @Test
    public void splitInvalidMessage1() {
        SplitMessageResult subMessageList = CommonUtils.splitMessage(INVALID_MESSAGE_1);
        assertTrue(!subMessageList.isSuccess());
        assertEquals(0, subMessageList.size());
        assertEquals(1, subMessageList.getErrorCode());
    }

    /**
     * Split an invalid message that has a word with length greater than 50 after add indicator text
     * */
    @Test
    public void splitInvalidMessage2() {
        SplitMessageResult subMessageList = CommonUtils.splitMessage(INVALID_MESSAGE_2);
        assertTrue(!subMessageList.isSuccess());
        assertEquals(0, subMessageList.size());
        assertEquals(2, subMessageList.getErrorCode());
    }
}
