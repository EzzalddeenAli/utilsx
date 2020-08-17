package org.kingtec.utils.slidr.model;

/**
 * This listener interface is for receiving events from the sliding panel such as state changes
 * and slide progress
 */
public interface SlidrListener {

    /**
     * This is called when the {@link androidx.core.widget.ViewDragHelper} calls it's
     * state change callback.
     *
     * @param state the {@link androidx.core.widget.ViewDragHelper} state
     * @see androidx.core.widget.ViewDragHelper#STATE_IDLE
     * @see androidx.core.widget.ViewDragHelper#STATE_DRAGGING
     * @see androidx.core.widget.ViewDragHelper#STATE_SETTLING
     */
    void onSlideStateChanged(int state);

    void onSlideChange(float percent);

    void onSlideOpened();

    void onSlideClosed();
}
