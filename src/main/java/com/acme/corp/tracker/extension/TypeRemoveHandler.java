package com.acme.corp.tracker.extension;

import org.jboss.as.controller.AbstractRemoveStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.ServiceName;

import java.util.Locale;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.*;

public class TypeRemoveHandler extends AbstractRemoveStepHandler implements DescriptionProvider {

    public static final TypeRemoveHandler INSTANCE = new TypeRemoveHandler();

    private TypeRemoveHandler() {
    }

    @Override
    public ModelNode getModelDescription(Locale locale) {
        ModelNode node = new ModelNode();
        node.get(DESCRIPTION).set("Removes a tracked deployment type");
        return node;
    }

    @Override
    protected void performRuntime(OperationContext context, ModelNode operation, ModelNode model) throws OperationFailedException {
        String suffix = PathAddress.pathAddress(operation.get(ModelDescriptionConstants.ADDRESS)).getLastElement().getValue();
        ServiceName name = TrackerService.createServiceName(suffix);
        context.removeService(name);
    }
}
