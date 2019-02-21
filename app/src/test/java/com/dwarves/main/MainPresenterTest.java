package com.dwarves.main;

import com.dwarves.main.ui.MainMvpPresenter;
import com.dwarves.main.ui.MainMvpView;
import com.dwarves.main.ui.MainPresenter;
import com.dwarves.main.ui.SplitMessageResult;
import com.dwarves.main.utils.CommonUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

/**
 * Unit test for the splitting feature
 */
public class MainPresenterTest {

    // Stub an input message
    private static final String INPUT_MESSAGE
            = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.";

    // Stub an invalid input message
    private static final String INVALID_MESSAGE
            = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdon'thavetodoitmyself.";

    @Mock
    MainMvpView mMainMvpView;

    private MainMvpPresenter mMainMvpPresenter;

    @Before
    public void setupMainPresenter() {
        MockitoAnnotations.initMocks(this);
        mMainMvpPresenter = new MainPresenter(mMainMvpView);
    }

    /**
     * TEST CASE 1: valid input message
     * */
    @Test
    public void splitInputTextAndShow() {
        mMainMvpPresenter.getAndShowSubMessageList(INPUT_MESSAGE);
        SplitMessageResult subMessageList = CommonUtils.splitMessage(INPUT_MESSAGE);

        // Verify that view show the list of sub messages
        verify(mMainMvpView).showSubMessageView(subMessageList);

        // Verify that if input text has a word that greater than 50, error is shown
        if (!subMessageList.isSuccess()) {
            verify(mMainMvpView).showError(anyInt());
        }
    }


    /**
     * TEST CASE 2: invalid input message
     * */
    @Test
    public void splitInvalidInputTextAndShowError() {
        mMainMvpPresenter.getAndShowSubMessageList(INVALID_MESSAGE);
        SplitMessageResult subMessageList = CommonUtils.splitMessage(INVALID_MESSAGE);

        // Verify that if input text has a word that greater than 50, error is shown
        if (!subMessageList.isSuccess()) {
            verify(mMainMvpView).showError(anyInt());
        }
    }
}