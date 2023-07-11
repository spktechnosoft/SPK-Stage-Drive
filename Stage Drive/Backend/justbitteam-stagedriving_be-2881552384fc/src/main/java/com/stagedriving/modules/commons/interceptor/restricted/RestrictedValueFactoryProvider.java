package com.stagedriving.modules.commons.interceptor.restricted;

import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.dto.ScxResponseDTO;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.user.controller.AccountController;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;
import org.glassfish.jersey.server.model.Parameter;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Class to take {@link Restricted} object from the Http Request attributes and make sure it can be
 * automatically injected in resources.
 */
@Singleton
public class RestrictedValueFactoryProvider extends AbstractValueFactoryProvider {

    /**
     * Injection resolver for {@link Restricted} annotation. Will create a Factory Provider for
     * the actual resolving of the object.
     */
    @Singleton
    static final class InjectionResolver extends ParamInjectionResolver<Restricted> {

        /**
         * Create new {@link Restricted} annotation injection resolver.
         */
        public InjectionResolver() {
            super(RestrictedValueFactoryProvider.class);
        }
    }

    /**
     * Factory implementation for resolving request-based attributes and other information.
     */
    private static final class RestrictedValueFactory extends AbstractContainerRequestValueFactory<Account> {

        @Inject
        private AccountController accountController;

        @Context
        private ResourceContext context;

        /*@Inject
        private ScxAccountController accountController;
        @Inject
        private ScxApplicationDAO applicationDAO;
        @Inject
        private ScxAccountDAO accountDAO;*/
        @Inject
        AccountDAO accountDAO;

        private String role;
        private boolean onlyActivated;
        private boolean required;

        public RestrictedValueFactory(String role, boolean onlyActivated, boolean required) {
            this.role = role;
            this.required = required;
            this.onlyActivated = onlyActivated;
        }

        /**
         * Fetch the Identity object from the request. Since HttpServletRequest is not directly available, we need to get it via
         * the injected {@link ResourceContext}.
         *
         * @return {@link Account} stored on the request, or NULL if no object was found.
         */
        public Account provide() {
            final HttpServletRequest request = context.getResource(HttpServletRequest.class);
            try {
                final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
                if (header != null) {
                    final int space = header.indexOf(' ');
                    if (space > 0) {
                        final String method = header.substring(0, space);
                        if ("bearer".equalsIgnoreCase(method)) {
                            final String token = header.substring(space + 1);

                            Account account = null;
                            try {
                                account = accountDAO.findByToken(token);
                                if (token != null && account == null) {
                                    throw new StgdrvInvalidRequestException(Response.Status.UNAUTHORIZED, ScxResponseDTO.Codes.INVALID_TOKEN, "Invalid token");
                                }

                                if (account != null) {
                                    if (role != null) {
                                        if (accountController.accountHasRole(account, role)) {
                                            return account;
                                        } else {
                                            throw new StgdrvInvalidRequestException(Response.Status.UNAUTHORIZED, ScxResponseDTO.Codes.PERMISSION_DENIED, StgdrvMessage.MessageError.INVALID_PERMISSION);
                                        }
                                    } else {
                                        return account;
                                    }
                                }
                            } catch (Exception e) {
                                if (e instanceof StgdrvInvalidRequestException) {
                                    throw e;
                                }
                                throw new StgdrvInvalidRequestException(Response.Status.UNAUTHORIZED, ScxResponseDTO.Codes.PERMISSION_DENIED, StgdrvMessage.MessageError.INVALID_PERMISSION);
                            }
//                            if (result.isPresent()) {
//                                if (onlyActivated) {
//                                    if (ScxData.AccountStatus.ACTIVATED.equals(result.get().getStatus())) {
//                                        return result.get();
//                                    }
//
//                                    throw new StgdrvInvalidRequestException(Response.Status.UNAUTHORIZED, ScxResponseDTO.Codes.PERMISSION_DENIED, "User not activated");
//                                }
//                                return result.get();
//                            }

                        }
                    }
                }
            } finally {

            }
            /* TODO catch (AuthenticationException e) {
//                LOGGER.warn("Error authenticating credentials", e);
                throw new StgdrvInvalidRequestException(Response.Status.UNAUTHORIZED, ScxResponseDTO.Codes.INVALID_TOKEN, "Invalid token");
//                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
            }*/

            if (required) {
                throw new StgdrvInvalidRequestException(Response.Status.UNAUTHORIZED, ScxResponseDTO.Codes.INVALID_TOKEN, "Invalid token");
//                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
            }
            return null;
        }
    }

    /**
     * {@link Restricted} annotation value factory provider injection constructor.
     *
     * @param mpep     multivalued parameter extractor provider.
     * @param injector injector instance.
     */
    @Inject
    public RestrictedValueFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator injector) {
        super(mpep, injector, Parameter.Source.UNKNOWN);
    }

    /**
     * Return a factory for the provided parameter. We only expect {@link Account} objects being annotated with
     * {@link Restricted} annotation
     *
     * @param parameter Parameter that was annotated for being injected
     * @return {@link RestrictedValueFactory} if parameter matched {@link Account} type
     */
    @Override
    public AbstractContainerRequestValueFactory<?> createValueFactory(Parameter parameter) {
        Class<?> classType = parameter.getRawType();

        if (classType == null || (!classType.equals(Account.class))) {
//            LOG.warn("IdentityParam annotation was not placed on correct object type; Injection might not work correctly!");
            return null;
        }

        Restricted restricted = parameter.getAnnotation(Restricted.class);

        return new RestrictedValueFactory(restricted.role(), restricted.onlyActivated(), restricted.required());
    }
}