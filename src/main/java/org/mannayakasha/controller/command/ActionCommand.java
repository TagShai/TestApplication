package org.mannayakasha.controller.command;

import org.mannayakasha.service.IServiceFactory;

import javax.servlet.http.HttpServletRequest;

public abstract class ActionCommand {

    protected IServiceFactory serviceFactory;

    public abstract String execute(HttpServletRequest request);
}
