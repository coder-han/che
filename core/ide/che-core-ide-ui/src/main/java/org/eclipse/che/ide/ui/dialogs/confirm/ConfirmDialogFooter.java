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

import org.eclipse.che.ide.ui.UILocalizationConstant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import org.eclipse.che.ide.ui.WidgetFocusTracker;
import org.eclipse.che.ide.ui.window.Window;

import javax.validation.constraints.NotNull;

/**
 * The footer show on confirmation dialogs.
 *
 * @author Mickaël Leduque
 * @author Artem Zatsarynnyi
 */
public class ConfirmDialogFooter extends Composite {

    private static final Window.Resources            resources = GWT.create(Window.Resources.class);
    /** The UI binder instance. */
    private static       ConfirmDialogFooterUiBinder uiBinder  = GWT.create(ConfirmDialogFooterUiBinder.class);
    /** The i18n messages. */
    @UiField(provided = true)
    UILocalizationConstant messages;
    @UiField
    Button                 okButton;
    @UiField
    Button                 cancelButton;
    /** The action delegate. */
    private ConfirmDialogView.ActionDelegate actionDelegate;

    private final WidgetFocusTracker widgetFocusTracker;
    @Inject
    public ConfirmDialogFooter(final @NotNull UILocalizationConstant messages, WidgetFocusTracker widgetFocusTracker) {
        this.messages = messages;
        this.widgetFocusTracker = widgetFocusTracker;
        initWidget(uiBinder.createAndBindUi(this));

        widgetFocusTracker.subscribe(okButton);
        widgetFocusTracker.subscribe(cancelButton);

        okButton.addStyleName(resources.windowCss().primaryButton());
        okButton.getElement().setId("ask-dialog-ok");

        cancelButton.addStyleName(resources.windowCss().button());
        cancelButton.getElement().setId("ask-dialog-cancel");
    }

    /**
     * Overwrites label of Ok button
     *
     * @param label new label
     */
    public void setOkButtonLabel(String label) {
        okButton.setText(label);
    }

    /**
     * Overwrites label of Cancel button
     *
     * @param label new label
     */
    public void setCancelButtonLabel(String label) {
        cancelButton.setText(label);
    }

    /**
     * Sets the action delegate.
     *
     * @param delegate
     *         the new value
     */
    public void setDelegate(final ConfirmDialogView.ActionDelegate delegate) {
        this.actionDelegate = delegate;
    }

    /**
     * Handler set on the OK button.
     *
     * @param event
     *         the event that triggers the handler call
     */
    @UiHandler("okButton")
    public void handleOkClick(final ClickEvent event) {
        this.actionDelegate.accepted();
    }

    /**
     * Handler set on the cancel button.
     *
     * @param event
     *         the event that triggers the handler call
     */
    @UiHandler("cancelButton")
    public void handleCancelClick(final ClickEvent event) {
        this.actionDelegate.cancelled();
    }

    /** Returns is OK button in the focus */
    boolean isOkButtonInFocus() {
        return widgetFocusTracker.isWidgetFocused(okButton);
    }

    /** Returns is Cancel button in the focus */
    boolean isCancelButtonInFocus() {
        return widgetFocusTracker.isWidgetFocused(cancelButton);
    }

    public void onClose() {
        widgetFocusTracker.unSubscribe(okButton);
        widgetFocusTracker.unSubscribe(cancelButton);
    }

    /** The UI binder interface for this component. */
    interface ConfirmDialogFooterUiBinder extends UiBinder<Widget, ConfirmDialogFooter> {
    }
}
