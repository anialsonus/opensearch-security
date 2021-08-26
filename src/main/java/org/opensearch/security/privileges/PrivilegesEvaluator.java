package org.opensearch.security.privileges;

import org.opensearch.action.ActionRequest;
import org.opensearch.common.transport.TransportAddress;
import org.opensearch.security.user.User;
import org.opensearch.tasks.Task;

import java.util.Map;
import java.util.Set;

public interface PrivilegesEvaluator {
    boolean isInitialized();
    PrivilegesEvaluatorResponse evaluate(final User user, String action0, final ActionRequest request,
                                                Task task, final Set<String> injectedRoles);
    Set<String> mapRoles(final User user, final TransportAddress caller);
    Map<String, Boolean> mapTenants(final User user, Set<String> roles);
    Set<String> getAllConfiguredTenantNames();
    boolean multitenancyEnabled();
    boolean notFailOnForbiddenEnabled();
    String dashboardsIndex();
    String dashboardsServerUsername();
    String dashboardsOpenSearchRole();
}
