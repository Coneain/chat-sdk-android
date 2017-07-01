package co.chatsdk.core.handlers;

import co.chatsdk.core.dao.BThread;

/**
 * Created by SimonSmiley-Andrews on 01/05/2017.
 */

public interface TypingIndicatorHandler {

    enum ChatStates{
        GONE,
        ACTIVE,
        INACTIVE,
        COMPOSING,
        PAUSED
    }

    void typingOn (BThread thread);
    void typingOff (BThread thread);

    void setChatState(BThread thread, ChatStates chatState);
//
//    -(RXPromise *) setChatState: (bChatState) state forThread: (id<PThread>) thread;
}
