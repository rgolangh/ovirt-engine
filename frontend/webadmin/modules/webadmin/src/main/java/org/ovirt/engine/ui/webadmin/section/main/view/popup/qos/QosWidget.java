package org.ovirt.engine.ui.webadmin.section.main.view.popup.qos;

import org.gwtbootstrap3.client.ui.Container;
import org.ovirt.engine.core.common.businessentities.qos.QosBase;
import org.ovirt.engine.ui.common.editor.UiCommonEditorDriver;
import org.ovirt.engine.ui.common.widget.uicommon.popup.AbstractModelBoundPopupWidget;
import org.ovirt.engine.ui.uicommonweb.models.datacenters.qos.QosParametersModel;
import org.ovirt.engine.ui.uicompat.Event;
import org.ovirt.engine.ui.uicompat.IEventListener;
import org.ovirt.engine.ui.uicompat.PropertyChangedEventArgs;
import com.google.gwt.uibinder.client.UiField;

public abstract class QosWidget<T extends QosBase, P extends QosParametersModel<T>> extends AbstractModelBoundPopupWidget<P> {

    @UiField
    Container mainContainer;

    protected UiCommonEditorDriver<P, QosWidget<T, P>> driver;

    private QosParametersModel<? extends QosBase> model;
    private final IEventListener<PropertyChangedEventArgs> propertyChangeListener;

    public QosWidget() {
        propertyChangeListener = new IEventListener<PropertyChangedEventArgs>() {

            @Override
            public void eventRaised(Event<? extends PropertyChangedEventArgs> ev,
                    Object sender,
                    PropertyChangedEventArgs args) {
                if ("IsAvailable".equals(args.propertyName)) { //$NON-NLS-1$
                    toggleVisibility();
                } else if ("IsChangable".equals(args.propertyName)) { //$NON-NLS-1$
                    updateChangeability(model.getIsChangable());
                }
            }
        };
    }

    private void toggleVisibility() {
        mainContainer.setVisible(model.getIsAvailable());
    }

    protected void updateChangeability(boolean enabled) {
        // Do nothing
    }

    @Override
    public void edit(P model) {
        driver.edit(model);

        if (this.model != null) {
            this.model.getPropertyChangedEvent().removeListener(propertyChangeListener);
        }
        this.model = model;
        model.getPropertyChangedEvent().addListener(propertyChangeListener);
        toggleVisibility();
        updateChangeability(model.getIsChangable());
    }

    @Override
    public P flush() {
        return driver.flush();
    }

    @Override
    public void cleanup() {
        driver.cleanup();
    }
}
