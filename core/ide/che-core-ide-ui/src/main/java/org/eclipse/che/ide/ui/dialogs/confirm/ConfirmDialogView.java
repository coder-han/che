/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.ui.dialogs.confirm;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * The view interface for the confirmation dialog component.
 * 
 * @author Mickaël Leduque
 * @author Artem Zatsarynnyi
 */
public interface ConfirmDialogView {

    /** Sets the action delegate. */
    void setDelegate(ActionDelegate delegate);

    /** Displays the dialog window. */
    void showDialog();

    /** Closes the dialog window. */
    void closeDialog();

    /** Fill the window with its content. */
    void setContent(IsWidget content);

    /** Sets the window title. */
    void setTitle(String title);

    /** Overwrites label of Ok button */
    void setOkButtonLabel(String label);

    /** Overwrites label of Cancel button */
    void setCancelButtonLabel(String label);

    /** Returns is OK button in the focus */
    boolean isOkButtonInFocus();

    /** Returns is Cancel button in the focus */
    boolean isCancelButtonInFocus();

    /** The interface for the action delegate. */
    interface ActionDelegate {

        /** Defines what's done when the user clicks cancel. */
        void cancelled();

        /** Defines what's done when the user clicks OK. */
        void accepted();

        /** Performs any actions appropriate in response to the user having clicked the Enter key. */
        void onEnterClicked();
    }
}
