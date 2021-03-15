package ganymede.server.renderer;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PROTECTED;
import static org.springframework.util.MimeTypeUtils.TEXT_HTML_VALUE;

/**
 * Thymeleaf template-based {@link ganymede.server.Renderer} abstract base
 * class.
 *
 * @author {@link.uri mailto:ball@hcf.dev Allen D. Ball}
 * @version $Revision$
 */
@NoArgsConstructor(access = PROTECTED)
public abstract class AbstractThymeleafHTMLRenderer extends StringRenderer {
    private final TemplateEngine engine = new TemplateEngine();
    private final String template;

    {
        var resolver = new StringTemplateResolver();

        resolver.setTemplateMode(StringTemplateResolver.DEFAULT_TEMPLATE_MODE);

        engine.setTemplateResolver(resolver);

        var name = getClass().getSimpleName() + ".html";
        var resource = new ClassPathResource(name, getClass());

        try (var in = resource.getInputStream()) {
            template = StreamUtils.copyToString(in, UTF_8);
        } catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }

    /**
     * Method to get the {@link Map} to populate the template
     * {@link Context}.
     *
     * @param   object          The {@link Object} to render (assignable to
     *                          {@link #getForType()}.
     *
     * @return  The {@link Context} {@link Map}.
     */
    protected abstract Map<String,Object> getMap(Object object);

    @Override
    public void renderTo(ObjectNode bundle, Object object) {
        if (! bundle.with(DATA).has(TEXT_HTML_VALUE)) {
            try {
                var html = engine.process(template, new Context(null, getMap(object)));

                bundle.with(DATA).put(TEXT_HTML_VALUE, html);
            } catch (Exception exception) {
            }
        }

        super.renderTo(bundle, String.valueOf(object));
    }
}