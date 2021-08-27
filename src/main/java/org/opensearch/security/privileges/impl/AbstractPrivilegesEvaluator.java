package org.opensearch.security.privileges.impl;

import org.greenrobot.eventbus.Subscribe;
import org.opensearch.action.ActionRequest;
import org.opensearch.common.transport.TransportAddress;
import org.opensearch.security.privileges.PrivilegesEvaluator;
import org.opensearch.security.privileges.PrivilegesEvaluatorResponse;
import org.opensearch.security.privileges.PrivilegesInterceptor;
import org.opensearch.security.securityconf.ConfigModel;
import org.opensearch.security.securityconf.DynamicConfigModel;
import org.opensearch.security.user.User;
import org.opensearch.tasks.Task;

import java.util.Map;
import java.util.Set;

public abstract class AbstractPrivilegesEvaluator implements PrivilegesEvaluator {

    protected ConfigModel configModel;
    protected DynamicConfigModel dcm;
    protected PrivilegesInterceptor privilegesInterceptor;

    protected AbstractPrivilegesEvaluator(PrivilegesInterceptor privilegesInterceptor) {
        this.privilegesInterceptor = privilegesInterceptor;
    }

    @Subscribe
    public void onConfigModelChanged(ConfigModel configModel) {
        this.configModel = configModel;
    }

    @Subscribe
    public void onDynamicConfigModelChanged(DynamicConfigModel dcm) {
        this.dcm = dcm;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public PrivilegesEvaluatorResponse evaluate(User user, String action0, ActionRequest request, Task task, Set<String> injectedRoles) {
        return new PrivilegesEvaluatorResponse();
    }

    @Override
    public Set<String> mapRoles(User user, TransportAddress caller) {
        return configModel.mapSecurityRoles(user, caller);
    }

    @Override
    public Map<String, Boolean> mapTenants(User user, Set<String> roles) {
        return configModel.mapTenants(user, roles);
    }

    @Override
    public Set<String> getAllConfiguredTenantNames() {
        return configModel.getAllConfiguredTenantNames();
    }

    @Override
    public boolean multitenancyEnabled() {
        return privilegesInterceptor.getClass() != PrivilegesInterceptor.class
                && dcm.isDashboardsMultitenancyEnabled();
    }

    @Override
    public boolean notFailOnForbiddenEnabled() {
        return privilegesInterceptor.getClass() != PrivilegesInterceptor.class
                && dcm.isDnfofEnabled();
    }

    @Override
    public String dashboardsIndex() {
        return dcm.getDashboardsIndexname();
    }

    @Override
    public String dashboardsServerUsername() {
         return dcm.getDashboardsServerUsername();
    }

    @Override
    public String dashboardsOpenSearchRole() {
        return dcm.getDashboardsOpenSearchRole();
    }
}
