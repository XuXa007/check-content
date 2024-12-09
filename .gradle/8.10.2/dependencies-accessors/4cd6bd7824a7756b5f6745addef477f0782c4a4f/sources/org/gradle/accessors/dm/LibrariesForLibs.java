package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final JavaxLibraryAccessors laccForJavaxLibraryAccessors = new JavaxLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Group of libraries at <b>com</b>
     */
    public ComLibraryAccessors getCom() {
        return laccForComLibraryAccessors;
    }

    /**
     * Group of libraries at <b>io</b>
     */
    public IoLibraryAccessors getIo() {
        return laccForIoLibraryAccessors;
    }

    /**
     * Group of libraries at <b>javax</b>
     */
    public JavaxLibraryAccessors getJavax() {
        return laccForJavaxLibraryAccessors;
    }

    /**
     * Group of libraries at <b>org</b>
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComH2databaseLibraryAccessors laccForComH2databaseLibraryAccessors = new ComH2databaseLibraryAccessors(owner);
        private final ComNetflixLibraryAccessors laccForComNetflixLibraryAccessors = new ComNetflixLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.h2database</b>
         */
        public ComH2databaseLibraryAccessors getH2database() {
            return laccForComH2databaseLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.netflix</b>
         */
        public ComNetflixLibraryAccessors getNetflix() {
            return laccForComNetflixLibraryAccessors;
        }

    }

    public static class ComH2databaseLibraryAccessors extends SubDependencyFactory {

        public ComH2databaseLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>h2</b> with <b>com.h2database:h2</b> coordinates and
         * with version reference <b>com.h2database.h2</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getH2() {
            return create("com.h2database.h2");
        }

    }

    public static class ComNetflixLibraryAccessors extends SubDependencyFactory {
        private final ComNetflixGraphqlLibraryAccessors laccForComNetflixGraphqlLibraryAccessors = new ComNetflixGraphqlLibraryAccessors(owner);

        public ComNetflixLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.netflix.graphql</b>
         */
        public ComNetflixGraphqlLibraryAccessors getGraphql() {
            return laccForComNetflixGraphqlLibraryAccessors;
        }

    }

    public static class ComNetflixGraphqlLibraryAccessors extends SubDependencyFactory {
        private final ComNetflixGraphqlDgsLibraryAccessors laccForComNetflixGraphqlDgsLibraryAccessors = new ComNetflixGraphqlDgsLibraryAccessors(owner);

        public ComNetflixGraphqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.netflix.graphql.dgs</b>
         */
        public ComNetflixGraphqlDgsLibraryAccessors getDgs() {
            return laccForComNetflixGraphqlDgsLibraryAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsLibraryAccessors extends SubDependencyFactory {
        private final ComNetflixGraphqlDgsGraphqlLibraryAccessors laccForComNetflixGraphqlDgsGraphqlLibraryAccessors = new ComNetflixGraphqlDgsGraphqlLibraryAccessors(owner);

        public ComNetflixGraphqlDgsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.netflix.graphql.dgs.graphql</b>
         */
        public ComNetflixGraphqlDgsGraphqlLibraryAccessors getGraphql() {
            return laccForComNetflixGraphqlDgsGraphqlLibraryAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlLibraryAccessors extends SubDependencyFactory {
        private final ComNetflixGraphqlDgsGraphqlDgsLibraryAccessors laccForComNetflixGraphqlDgsGraphqlDgsLibraryAccessors = new ComNetflixGraphqlDgsGraphqlDgsLibraryAccessors(owner);

        public ComNetflixGraphqlDgsGraphqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.netflix.graphql.dgs.graphql.dgs</b>
         */
        public ComNetflixGraphqlDgsGraphqlDgsLibraryAccessors getDgs() {
            return laccForComNetflixGraphqlDgsGraphqlDgsLibraryAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlDgsLibraryAccessors extends SubDependencyFactory {
        private final ComNetflixGraphqlDgsGraphqlDgsSpringLibraryAccessors laccForComNetflixGraphqlDgsGraphqlDgsSpringLibraryAccessors = new ComNetflixGraphqlDgsGraphqlDgsSpringLibraryAccessors(owner);

        public ComNetflixGraphqlDgsGraphqlDgsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.netflix.graphql.dgs.graphql.dgs.spring</b>
         */
        public ComNetflixGraphqlDgsGraphqlDgsSpringLibraryAccessors getSpring() {
            return laccForComNetflixGraphqlDgsGraphqlDgsSpringLibraryAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlDgsSpringLibraryAccessors extends SubDependencyFactory {
        private final ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlLibraryAccessors laccForComNetflixGraphqlDgsGraphqlDgsSpringGraphqlLibraryAccessors = new ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlLibraryAccessors(owner);

        public ComNetflixGraphqlDgsGraphqlDgsSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.netflix.graphql.dgs.graphql.dgs.spring.graphql</b>
         */
        public ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlLibraryAccessors getGraphql() {
            return laccForComNetflixGraphqlDgsGraphqlDgsSpringGraphqlLibraryAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlLibraryAccessors extends SubDependencyFactory {
        private final ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterLibraryAccessors laccForComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterLibraryAccessors = new ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterLibraryAccessors(owner);

        public ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter</b>
         */
        public ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterLibraryAccessors getStarter() {
            return laccForComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterLibraryAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>starter</b> with <b>com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter</b> coordinates and
         * with version reference <b>com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter");
        }

        /**
         * Dependency provider for <b>test</b> with <b>com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter-test</b> coordinates and
         * with version reference <b>com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter.test</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTest() {
            return create("com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter.test");
        }

    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoGrpcLibraryAccessors laccForIoGrpcLibraryAccessors = new IoGrpcLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.grpc</b>
         */
        public IoGrpcLibraryAccessors getGrpc() {
            return laccForIoGrpcLibraryAccessors;
        }

    }

    public static class IoGrpcLibraryAccessors extends SubDependencyFactory {
        private final IoGrpcGrpcLibraryAccessors laccForIoGrpcGrpcLibraryAccessors = new IoGrpcGrpcLibraryAccessors(owner);

        public IoGrpcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.grpc.grpc</b>
         */
        public IoGrpcGrpcLibraryAccessors getGrpc() {
            return laccForIoGrpcGrpcLibraryAccessors;
        }

    }

    public static class IoGrpcGrpcLibraryAccessors extends SubDependencyFactory {
        private final IoGrpcGrpcNettyLibraryAccessors laccForIoGrpcGrpcNettyLibraryAccessors = new IoGrpcGrpcNettyLibraryAccessors(owner);

        public IoGrpcGrpcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>protobuf</b> with <b>io.grpc:grpc-protobuf</b> coordinates and
         * with version reference <b>io.grpc.grpc.protobuf</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getProtobuf() {
            return create("io.grpc.grpc.protobuf");
        }

        /**
         * Dependency provider for <b>stub</b> with <b>io.grpc:grpc-stub</b> coordinates and
         * with version reference <b>io.grpc.grpc.stub</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getStub() {
            return create("io.grpc.grpc.stub");
        }

        /**
         * Group of libraries at <b>io.grpc.grpc.netty</b>
         */
        public IoGrpcGrpcNettyLibraryAccessors getNetty() {
            return laccForIoGrpcGrpcNettyLibraryAccessors;
        }

    }

    public static class IoGrpcGrpcNettyLibraryAccessors extends SubDependencyFactory {

        public IoGrpcGrpcNettyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>shaded</b> with <b>io.grpc:grpc-netty-shaded</b> coordinates and
         * with version reference <b>io.grpc.grpc.netty.shaded</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getShaded() {
            return create("io.grpc.grpc.netty.shaded");
        }

    }

    public static class JavaxLibraryAccessors extends SubDependencyFactory {
        private final JavaxAnnotationLibraryAccessors laccForJavaxAnnotationLibraryAccessors = new JavaxAnnotationLibraryAccessors(owner);

        public JavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>javax.annotation</b>
         */
        public JavaxAnnotationLibraryAccessors getAnnotation() {
            return laccForJavaxAnnotationLibraryAccessors;
        }

    }

    public static class JavaxAnnotationLibraryAccessors extends SubDependencyFactory {
        private final JavaxAnnotationJavaxLibraryAccessors laccForJavaxAnnotationJavaxLibraryAccessors = new JavaxAnnotationJavaxLibraryAccessors(owner);

        public JavaxAnnotationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>javax.annotation.javax</b>
         */
        public JavaxAnnotationJavaxLibraryAccessors getJavax() {
            return laccForJavaxAnnotationJavaxLibraryAccessors;
        }

    }

    public static class JavaxAnnotationJavaxLibraryAccessors extends SubDependencyFactory {
        private final JavaxAnnotationJavaxAnnotationLibraryAccessors laccForJavaxAnnotationJavaxAnnotationLibraryAccessors = new JavaxAnnotationJavaxAnnotationLibraryAccessors(owner);

        public JavaxAnnotationJavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>javax.annotation.javax.annotation</b>
         */
        public JavaxAnnotationJavaxAnnotationLibraryAccessors getAnnotation() {
            return laccForJavaxAnnotationJavaxAnnotationLibraryAccessors;
        }

    }

    public static class JavaxAnnotationJavaxAnnotationLibraryAccessors extends SubDependencyFactory {

        public JavaxAnnotationJavaxAnnotationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>javax.annotation:javax.annotation-api</b> coordinates and
         * with version reference <b>javax.annotation.javax.annotation.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("javax.annotation.javax.annotation.api");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgModelmapperLibraryAccessors laccForOrgModelmapperLibraryAccessors = new OrgModelmapperLibraryAccessors(owner);
        private final OrgProjectlombokLibraryAccessors laccForOrgProjectlombokLibraryAccessors = new OrgProjectlombokLibraryAccessors(owner);
        private final OrgSpringframeworkLibraryAccessors laccForOrgSpringframeworkLibraryAccessors = new OrgSpringframeworkLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.modelmapper</b>
         */
        public OrgModelmapperLibraryAccessors getModelmapper() {
            return laccForOrgModelmapperLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.projectlombok</b>
         */
        public OrgProjectlombokLibraryAccessors getProjectlombok() {
            return laccForOrgProjectlombokLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.springframework</b>
         */
        public OrgSpringframeworkLibraryAccessors getSpringframework() {
            return laccForOrgSpringframeworkLibraryAccessors;
        }

    }

    public static class OrgModelmapperLibraryAccessors extends SubDependencyFactory {

        public OrgModelmapperLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>modelmapper</b> with <b>org.modelmapper:modelmapper</b> coordinates and
         * with version reference <b>org.modelmapper.modelmapper</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getModelmapper() {
            return create("org.modelmapper.modelmapper");
        }

    }

    public static class OrgProjectlombokLibraryAccessors extends SubDependencyFactory {

        public OrgProjectlombokLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>lombok</b> with <b>org.projectlombok:lombok</b> coordinates and
         * with version reference <b>org.projectlombok.lombok</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLombok() {
            return create("org.projectlombok.lombok");
        }

    }

    public static class OrgSpringframeworkLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkAmqpLibraryAccessors laccForOrgSpringframeworkAmqpLibraryAccessors = new OrgSpringframeworkAmqpLibraryAccessors(owner);
        private final OrgSpringframeworkBootLibraryAccessors laccForOrgSpringframeworkBootLibraryAccessors = new OrgSpringframeworkBootLibraryAccessors(owner);

        public OrgSpringframeworkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.amqp</b>
         */
        public OrgSpringframeworkAmqpLibraryAccessors getAmqp() {
            return laccForOrgSpringframeworkAmqpLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.springframework.boot</b>
         */
        public OrgSpringframeworkBootLibraryAccessors getBoot() {
            return laccForOrgSpringframeworkBootLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkAmqpLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkAmqpSpringLibraryAccessors laccForOrgSpringframeworkAmqpSpringLibraryAccessors = new OrgSpringframeworkAmqpSpringLibraryAccessors(owner);

        public OrgSpringframeworkAmqpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.amqp.spring</b>
         */
        public OrgSpringframeworkAmqpSpringLibraryAccessors getSpring() {
            return laccForOrgSpringframeworkAmqpSpringLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkAmqpSpringLibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkAmqpSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>amqp</b> with <b>org.springframework.amqp:spring-amqp</b> coordinates and
         * with version reference <b>org.springframework.amqp.spring.amqp</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAmqp() {
            return create("org.springframework.amqp.spring.amqp");
        }

    }

    public static class OrgSpringframeworkBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringLibraryAccessors laccForOrgSpringframeworkBootSpringLibraryAccessors = new OrgSpringframeworkBootSpringLibraryAccessors(owner);

        public OrgSpringframeworkBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.boot.spring</b>
         */
        public OrgSpringframeworkBootSpringLibraryAccessors getSpring() {
            return laccForOrgSpringframeworkBootSpringLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootLibraryAccessors laccForOrgSpringframeworkBootSpringBootLibraryAccessors = new OrgSpringframeworkBootSpringBootLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.boot.spring.boot</b>
         */
        public OrgSpringframeworkBootSpringBootLibraryAccessors getBoot() {
            return laccForOrgSpringframeworkBootSpringBootLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.springframework.boot.spring.boot.starter</b>
         */
        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors getStarter() {
            return laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>amqp</b> with <b>org.springframework.boot:spring-boot-starter-amqp</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.amqp</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAmqp() {
            return create("org.springframework.boot.spring.boot.starter.amqp");
        }

        /**
         * Dependency provider for <b>hateoas</b> with <b>org.springframework.boot:spring-boot-starter-hateoas</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.hateoas</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHateoas() {
            return create("org.springframework.boot.spring.boot.starter.hateoas");
        }

        /**
         * Dependency provider for <b>test</b> with <b>org.springframework.boot:spring-boot-starter-test</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.test</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTest() {
            return create("org.springframework.boot.spring.boot.starter.test");
        }

        /**
         * Dependency provider for <b>web</b> with <b>org.springframework.boot:spring-boot-starter-web</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.web</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getWeb() {
            return create("org.springframework.boot.spring.boot.starter.web");
        }

        /**
         * Group of libraries at <b>org.springframework.boot.spring.boot.starter.data</b>
         */
        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors getData() {
            return laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>jpa</b> with <b>org.springframework.boot:spring-boot-starter-data-jpa</b> coordinates and
         * with version reference <b>org.springframework.boot.spring.boot.starter.data.jpa</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJpa() {
            return create("org.springframework.boot.spring.boot.starter.data.jpa");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final JavaxVersionAccessors vaccForJavaxVersionAccessors = new JavaxVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com</b>
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.io</b>
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.javax</b>
         */
        public JavaxVersionAccessors getJavax() {
            return vaccForJavaxVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org</b>
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComH2databaseVersionAccessors vaccForComH2databaseVersionAccessors = new ComH2databaseVersionAccessors(providers, config);
        private final ComNetflixVersionAccessors vaccForComNetflixVersionAccessors = new ComNetflixVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.h2database</b>
         */
        public ComH2databaseVersionAccessors getH2database() {
            return vaccForComH2databaseVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.netflix</b>
         */
        public ComNetflixVersionAccessors getNetflix() {
            return vaccForComNetflixVersionAccessors;
        }

    }

    public static class ComH2databaseVersionAccessors extends VersionFactory  {

        public ComH2databaseVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.h2database.h2</b> with value <b>2.2.224</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getH2() { return getVersion("com.h2database.h2"); }

    }

    public static class ComNetflixVersionAccessors extends VersionFactory  {

        private final ComNetflixGraphqlVersionAccessors vaccForComNetflixGraphqlVersionAccessors = new ComNetflixGraphqlVersionAccessors(providers, config);
        public ComNetflixVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.netflix.graphql</b>
         */
        public ComNetflixGraphqlVersionAccessors getGraphql() {
            return vaccForComNetflixGraphqlVersionAccessors;
        }

    }

    public static class ComNetflixGraphqlVersionAccessors extends VersionFactory  {

        private final ComNetflixGraphqlDgsVersionAccessors vaccForComNetflixGraphqlDgsVersionAccessors = new ComNetflixGraphqlDgsVersionAccessors(providers, config);
        public ComNetflixGraphqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.netflix.graphql.dgs</b>
         */
        public ComNetflixGraphqlDgsVersionAccessors getDgs() {
            return vaccForComNetflixGraphqlDgsVersionAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsVersionAccessors extends VersionFactory  {

        private final ComNetflixGraphqlDgsGraphqlVersionAccessors vaccForComNetflixGraphqlDgsGraphqlVersionAccessors = new ComNetflixGraphqlDgsGraphqlVersionAccessors(providers, config);
        public ComNetflixGraphqlDgsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.netflix.graphql.dgs.graphql</b>
         */
        public ComNetflixGraphqlDgsGraphqlVersionAccessors getGraphql() {
            return vaccForComNetflixGraphqlDgsGraphqlVersionAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlVersionAccessors extends VersionFactory  {

        private final ComNetflixGraphqlDgsGraphqlDgsVersionAccessors vaccForComNetflixGraphqlDgsGraphqlDgsVersionAccessors = new ComNetflixGraphqlDgsGraphqlDgsVersionAccessors(providers, config);
        public ComNetflixGraphqlDgsGraphqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.netflix.graphql.dgs.graphql.dgs</b>
         */
        public ComNetflixGraphqlDgsGraphqlDgsVersionAccessors getDgs() {
            return vaccForComNetflixGraphqlDgsGraphqlDgsVersionAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlDgsVersionAccessors extends VersionFactory  {

        private final ComNetflixGraphqlDgsGraphqlDgsSpringVersionAccessors vaccForComNetflixGraphqlDgsGraphqlDgsSpringVersionAccessors = new ComNetflixGraphqlDgsGraphqlDgsSpringVersionAccessors(providers, config);
        public ComNetflixGraphqlDgsGraphqlDgsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.netflix.graphql.dgs.graphql.dgs.spring</b>
         */
        public ComNetflixGraphqlDgsGraphqlDgsSpringVersionAccessors getSpring() {
            return vaccForComNetflixGraphqlDgsGraphqlDgsSpringVersionAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlDgsSpringVersionAccessors extends VersionFactory  {

        private final ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlVersionAccessors vaccForComNetflixGraphqlDgsGraphqlDgsSpringGraphqlVersionAccessors = new ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlVersionAccessors(providers, config);
        public ComNetflixGraphqlDgsGraphqlDgsSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.netflix.graphql.dgs.graphql.dgs.spring.graphql</b>
         */
        public ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlVersionAccessors getGraphql() {
            return vaccForComNetflixGraphqlDgsGraphqlDgsSpringGraphqlVersionAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlVersionAccessors extends VersionFactory  {

        private final ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterVersionAccessors vaccForComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterVersionAccessors = new ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterVersionAccessors(providers, config);
        public ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter</b>
         */
        public ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterVersionAccessors getStarter() {
            return vaccForComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterVersionAccessors;
        }

    }

    public static class ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterVersionAccessors extends VersionFactory  implements VersionNotationSupplier {

        public ComNetflixGraphqlDgsGraphqlDgsSpringGraphqlStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter</b> with value <b>9.1.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> asProvider() { return getVersion("com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter"); }

        /**
         * Version alias <b>com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter.test</b> with value <b>9.1.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getTest() { return getVersion("com.netflix.graphql.dgs.graphql.dgs.spring.graphql.starter.test"); }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoGrpcVersionAccessors vaccForIoGrpcVersionAccessors = new IoGrpcVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.grpc</b>
         */
        public IoGrpcVersionAccessors getGrpc() {
            return vaccForIoGrpcVersionAccessors;
        }

    }

    public static class IoGrpcVersionAccessors extends VersionFactory  {

        private final IoGrpcGrpcVersionAccessors vaccForIoGrpcGrpcVersionAccessors = new IoGrpcGrpcVersionAccessors(providers, config);
        public IoGrpcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.grpc.grpc</b>
         */
        public IoGrpcGrpcVersionAccessors getGrpc() {
            return vaccForIoGrpcGrpcVersionAccessors;
        }

    }

    public static class IoGrpcGrpcVersionAccessors extends VersionFactory  {

        private final IoGrpcGrpcNettyVersionAccessors vaccForIoGrpcGrpcNettyVersionAccessors = new IoGrpcGrpcNettyVersionAccessors(providers, config);
        public IoGrpcGrpcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>io.grpc.grpc.protobuf</b> with value <b>1.58.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getProtobuf() { return getVersion("io.grpc.grpc.protobuf"); }

        /**
         * Version alias <b>io.grpc.grpc.stub</b> with value <b>1.58.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getStub() { return getVersion("io.grpc.grpc.stub"); }

        /**
         * Group of versions at <b>versions.io.grpc.grpc.netty</b>
         */
        public IoGrpcGrpcNettyVersionAccessors getNetty() {
            return vaccForIoGrpcGrpcNettyVersionAccessors;
        }

    }

    public static class IoGrpcGrpcNettyVersionAccessors extends VersionFactory  {

        public IoGrpcGrpcNettyVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>io.grpc.grpc.netty.shaded</b> with value <b>1.58.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getShaded() { return getVersion("io.grpc.grpc.netty.shaded"); }

    }

    public static class JavaxVersionAccessors extends VersionFactory  {

        private final JavaxAnnotationVersionAccessors vaccForJavaxAnnotationVersionAccessors = new JavaxAnnotationVersionAccessors(providers, config);
        public JavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.javax.annotation</b>
         */
        public JavaxAnnotationVersionAccessors getAnnotation() {
            return vaccForJavaxAnnotationVersionAccessors;
        }

    }

    public static class JavaxAnnotationVersionAccessors extends VersionFactory  {

        private final JavaxAnnotationJavaxVersionAccessors vaccForJavaxAnnotationJavaxVersionAccessors = new JavaxAnnotationJavaxVersionAccessors(providers, config);
        public JavaxAnnotationVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.javax.annotation.javax</b>
         */
        public JavaxAnnotationJavaxVersionAccessors getJavax() {
            return vaccForJavaxAnnotationJavaxVersionAccessors;
        }

    }

    public static class JavaxAnnotationJavaxVersionAccessors extends VersionFactory  {

        private final JavaxAnnotationJavaxAnnotationVersionAccessors vaccForJavaxAnnotationJavaxAnnotationVersionAccessors = new JavaxAnnotationJavaxAnnotationVersionAccessors(providers, config);
        public JavaxAnnotationJavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.javax.annotation.javax.annotation</b>
         */
        public JavaxAnnotationJavaxAnnotationVersionAccessors getAnnotation() {
            return vaccForJavaxAnnotationJavaxAnnotationVersionAccessors;
        }

    }

    public static class JavaxAnnotationJavaxAnnotationVersionAccessors extends VersionFactory  {

        public JavaxAnnotationJavaxAnnotationVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>javax.annotation.javax.annotation.api</b> with value <b>1.3.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("javax.annotation.javax.annotation.api"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgModelmapperVersionAccessors vaccForOrgModelmapperVersionAccessors = new OrgModelmapperVersionAccessors(providers, config);
        private final OrgProjectlombokVersionAccessors vaccForOrgProjectlombokVersionAccessors = new OrgProjectlombokVersionAccessors(providers, config);
        private final OrgSpringframeworkVersionAccessors vaccForOrgSpringframeworkVersionAccessors = new OrgSpringframeworkVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.modelmapper</b>
         */
        public OrgModelmapperVersionAccessors getModelmapper() {
            return vaccForOrgModelmapperVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.projectlombok</b>
         */
        public OrgProjectlombokVersionAccessors getProjectlombok() {
            return vaccForOrgProjectlombokVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.springframework</b>
         */
        public OrgSpringframeworkVersionAccessors getSpringframework() {
            return vaccForOrgSpringframeworkVersionAccessors;
        }

    }

    public static class OrgModelmapperVersionAccessors extends VersionFactory  {

        public OrgModelmapperVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.modelmapper.modelmapper</b> with value <b>3.2.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getModelmapper() { return getVersion("org.modelmapper.modelmapper"); }

    }

    public static class OrgProjectlombokVersionAccessors extends VersionFactory  {

        public OrgProjectlombokVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.projectlombok.lombok</b> with value <b>1.18.34</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getLombok() { return getVersion("org.projectlombok.lombok"); }

    }

    public static class OrgSpringframeworkVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkAmqpVersionAccessors vaccForOrgSpringframeworkAmqpVersionAccessors = new OrgSpringframeworkAmqpVersionAccessors(providers, config);
        private final OrgSpringframeworkBootVersionAccessors vaccForOrgSpringframeworkBootVersionAccessors = new OrgSpringframeworkBootVersionAccessors(providers, config);
        public OrgSpringframeworkVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.amqp</b>
         */
        public OrgSpringframeworkAmqpVersionAccessors getAmqp() {
            return vaccForOrgSpringframeworkAmqpVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.springframework.boot</b>
         */
        public OrgSpringframeworkBootVersionAccessors getBoot() {
            return vaccForOrgSpringframeworkBootVersionAccessors;
        }

    }

    public static class OrgSpringframeworkAmqpVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkAmqpSpringVersionAccessors vaccForOrgSpringframeworkAmqpSpringVersionAccessors = new OrgSpringframeworkAmqpSpringVersionAccessors(providers, config);
        public OrgSpringframeworkAmqpVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.amqp.spring</b>
         */
        public OrgSpringframeworkAmqpSpringVersionAccessors getSpring() {
            return vaccForOrgSpringframeworkAmqpSpringVersionAccessors;
        }

    }

    public static class OrgSpringframeworkAmqpSpringVersionAccessors extends VersionFactory  {

        public OrgSpringframeworkAmqpSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springframework.amqp.spring.amqp</b> with value <b>3.1.7</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAmqp() { return getVersion("org.springframework.amqp.spring.amqp"); }

    }

    public static class OrgSpringframeworkBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringVersionAccessors vaccForOrgSpringframeworkBootSpringVersionAccessors = new OrgSpringframeworkBootSpringVersionAccessors(providers, config);
        public OrgSpringframeworkBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring</b>
         */
        public OrgSpringframeworkBootSpringVersionAccessors getSpring() {
            return vaccForOrgSpringframeworkBootSpringVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootVersionAccessors vaccForOrgSpringframeworkBootSpringBootVersionAccessors = new OrgSpringframeworkBootSpringBootVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring.boot</b>
         */
        public OrgSpringframeworkBootSpringBootVersionAccessors getBoot() {
            return vaccForOrgSpringframeworkBootSpringBootVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors = new OrgSpringframeworkBootSpringBootStarterVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring.boot.starter</b>
         */
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors getStarter() {
            return vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterDataVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors = new OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.amqp</b> with value <b>3.3.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAmqp() { return getVersion("org.springframework.boot.spring.boot.starter.amqp"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.hateoas</b> with value <b>3.3.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getHateoas() { return getVersion("org.springframework.boot.spring.boot.starter.hateoas"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.test</b> with value <b>3.3.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getTest() { return getVersion("org.springframework.boot.spring.boot.starter.test"); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.web</b> with value <b>3.3.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getWeb() { return getVersion("org.springframework.boot.spring.boot.starter.web"); }

        /**
         * Group of versions at <b>versions.org.springframework.boot.spring.boot.starter.data</b>
         */
        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors getData() {
            return vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataVersionAccessors extends VersionFactory  {

        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.springframework.boot.spring.boot.starter.data.jpa</b> with value <b>3.3.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJpa() { return getVersion("org.springframework.boot.spring.boot.starter.data.jpa"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
