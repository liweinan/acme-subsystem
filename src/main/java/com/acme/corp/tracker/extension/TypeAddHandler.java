package com.acme.corp.tracker.extension;

import org.jboss.as.controller.AbstractAddStepHandler;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;

import java.util.Locale;

class TypeAddHandler extends AbstractAddStepHandler implements DescriptionProvider {
    public static final TypeAddHandler INSTANCE = new TypeAddHandler();

    private TypeAddHandler() {
    }


    @Override
    public ModelNode getModelDescription(Locale locale) {
        ModelNode node = new ModelNode();
        node.get(DESCRIPTION).set("Adds a tracked deployment type");
        node.get(REQUEST_PROPERTIES, "tick", DESCRIPTION).set("How often to output information about a tracked deployment");
        node.get(REQUEST_PROPERTIES, "tick", TYPE).set(ModelType.LONG);
        node.get(REQUEST_PROPERTIES, "tick", REQUIRED).set(false);
        node.get(REQUEST_PROPERTIES, "tick", DEFAULT).set(10000);
        return node;
    }

    @Override
    protected void populateModel(ModelNode operation, ModelNode model) throws OperationFailedException {
        //The default value is 10000 if it has not been specified
        long tick = 10000;
//Read the value from the operation
        if (operation.hasDefined("tick")) {
            tick = operation.get("tick").asLong();
        }
        model.get("tick").set(tick);
    }
}