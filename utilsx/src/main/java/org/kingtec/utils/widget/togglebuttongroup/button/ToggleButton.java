package org.kingtec.utils.widget.togglebuttongroup.button;

import android.widget.Checkable;

public interface ToggleButton extends Checkable {

    void setOnCheckedChangeListener(OnCheckedChangeListener listener);

}
