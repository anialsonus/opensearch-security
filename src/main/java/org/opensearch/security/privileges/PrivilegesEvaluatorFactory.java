package org.opensearch.security.privileges;

import org.opensearch.cluster.metadata.IndexNameExpressionResolver;
import org.opensearch.cluster.service.ClusterService;
import org.opensearch.common.settings.Settings;
import org.opensearch.security.auditlog.AuditLog;
import org.opensearch.security.configuration.ClusterInfoHolder;
import org.opensearch.security.configuration.ConfigurationRepository;
import org.opensearch.security.resolver.IndexResolverReplacer;
import org.opensearch.threadpool.ThreadPool;

public interface PrivilegesEvaluatorFactory {
    PrivilegesEvaluator createPrivilegesEvaluator(ClusterService clusterService, ThreadPool threadPool,
                                                  ConfigurationRepository configurationRepository, IndexNameExpressionResolver resolver,
                                                  AuditLog auditLog, Settings settings, PrivilegesInterceptor privilegesInterceptor,
                                                  ClusterInfoHolder clusterInfoHolder, IndexResolverReplacer irr, boolean advancedModulesEnabled);
}
