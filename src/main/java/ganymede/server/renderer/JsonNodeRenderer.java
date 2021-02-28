package ganymede.server.renderer;

import ball.annotation.ServiceProviderFor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ganymede.server.Renderer;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * {@link JsonNode} {@link Renderer} service provider.
 *
 * @author {@link.uri mailto:ball@hcf.dev Allen D. Ball}
 * @version $Revision$
 */
@ServiceProviderFor({ Renderer.class })
@ForType(JsonNode.class)
@NoArgsConstructor @ToString
public class JsonNodeRenderer implements AnnotatedRenderer {
    private static final String MIME_TYPE = "application/json";

    @Override
    public void renderTo(ObjectNode bundle, Object object) {
        if (! bundle.with(DATA).has(MIME_TYPE)) {
            bundle.with(DATA).set(MIME_TYPE, (JsonNode) object);
            bundle.with(METADATA).with(MIME_TYPE)
                .put("expanded", true);
        }
    }
}