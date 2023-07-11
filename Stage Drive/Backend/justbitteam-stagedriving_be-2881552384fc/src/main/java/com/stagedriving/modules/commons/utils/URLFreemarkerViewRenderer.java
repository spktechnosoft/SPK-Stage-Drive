package com.stagedriving.modules.commons.utils;

import com.google.common.base.Charsets;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.dropwizard.views.View;
import io.dropwizard.views.ViewRenderer;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

/**
 * Created by simone on 08/03/14.
 */
public class URLFreemarkerViewRenderer implements ViewRenderer {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(URLFreemarkerViewRenderer.class);

    private final LoadingCache<Class<?>, Configuration> configurationCache;

    @Inject
    private AppConfiguration configuration;

    private TemplateLoader templateCache;

    @Inject
    public URLFreemarkerViewRenderer(TemplateLoader templateCache) {

        this.templateCache = templateCache;

        this.configurationCache = CacheBuilder.newBuilder()
                .concurrencyLevel(128)
                .build(templateCache);
    }

    private static class JbURLTemplateLoader extends URLTemplateLoader {

        private AppConfiguration configuration;

        @Inject
        public JbURLTemplateLoader(AppConfiguration configuration) {
            this.configuration = configuration;
        }

        @Override
        protected URL getURL(String name) {
            LOGGER.info("URL: "+name);
            if (name.contains("https://") || name.contains("http://")) {

                int index = 0;
                if (name.contains("http://")) {
                    index = name.indexOf("http://");
                } else if (name.contains("https://")) {
                    index = name.indexOf("https://");
                }
                String part = name.substring(index);
                if (!part.equals("")) {
                    try {
                        return new URL(part);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }
    }

    private static class TemplateLoader extends CacheLoader<Class<?>, Configuration> {

        @Inject
        private JbURLTemplateLoader urlTemplateLoader;

        @Override
        public Configuration load(Class<?> key) throws Exception {
            final Configuration configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            configuration.setObjectWrapper(new DefaultObjectWrapper(DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
            configuration.loadBuiltInEncodingMap();
            configuration.setDefaultEncoding(Charsets.UTF_8.name());
            //configuration.setClassForTemplateLoading(key, "/");

            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(new freemarker.cache.TemplateLoader[] {new ClassTemplateLoader(key, "/"), urlTemplateLoader});

            configuration.setTemplateLoader(multiTemplateLoader);
            return configuration;
        }
    }

    @Override
    public boolean isRenderable(View view) {
        return view.getTemplateName().endsWith(".ftl");
//        return !view.getTemplateName().isEmpty();
    }

    @Override
    public void render(View view,
                       Locale locale,
                       OutputStream output) throws IOException, WebApplicationException {
            final Configuration configuration = configurationCache.getUnchecked(view.getClass());
            final Charset charset = view.getCharset().orElse(Charset.forName(configuration.getEncoding(locale)));
            final Template template = configuration.getTemplate(view.getTemplateName(), locale, charset.name());
        try {
            template.process(view, new OutputStreamWriter(output, template.getEncoding()));
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void configure(Map<String, String> map) {

    }

    @Override
    public String getConfigurationKey() {
        return null;
    }


}