package org.opensearch.security.privileges.impl;

import org.opensearch.cluster.metadata.IndexNameExpressionResolver;
import org.opensearch.cluster.service.ClusterService;
import org.opensearch.common.settings.Settings;
import org.opensearch.security.auditlog.AuditLog;
import org.opensearch.security.configuration.ClusterInfoHolder;
import org.opensearch.security.configuration.ConfigurationRepository;
import org.opensearch.security.privileges.OpenSearchPrivilegesEvaluator;
import org.opensearch.security.privileges.PrivilegesEvaluator;
import org.opensearch.security.privileges.PrivilegesEvaluatorFactory;
import org.opensearch.security.privileges.PrivilegesInterceptor;
import org.opensearch.security.resolver.IndexResolverReplacer;
import org.opensearch.security.support.ConfigConstants;
import org.opensearch.threadpool.ThreadPool;

public class PrivilegesEvaluatorFactoryImpl implements PrivilegesEvaluatorFactory {

    @Override
    public PrivilegesEvaluator createPrivilegesEvaluator(ClusterService clusterService, ThreadPool threadPool, ConfigurationRepository configurationRepository, IndexNameExpressionResolver resolver, AuditLog auditLog, Settings settings, PrivilegesInterceptor privilegesInterceptor, ClusterInfoHolder clusterInfoHolder, IndexResolverReplacer irr, boolean advancedModulesEnabled) {
        String evaluatorName = settings.get(ConfigConstants.SECURITY_PRIVILEGES_EVALUATOR,"opensearch");
        PrivilegesEvaluator evaluator;
        switch (evaluatorName) {
            case "opensearch":
            default:
                evaluator =  new OpenSearchPrivilegesEvaluator(clusterService,threadPool,configurationRepository,resolver,auditLog,settings,privilegesInterceptor,clusterInfoHolder,irr,advancedModulesEnabled);
        }
        return evaluator;
    }
}
