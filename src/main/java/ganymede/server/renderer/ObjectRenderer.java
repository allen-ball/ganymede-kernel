package ganymede.server.renderer;

import ball.annotation.ServiceProviderFor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ganymede.server.Renderer;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * {@link Object} {@link Renderer} service provider.
 *
 * @author {@link.uri mailto:ball@hcf.dev Allen D. Ball}
 * @version $Revision$
 */
@ServiceProviderFor({ Renderer.class })
@ForType(Object.class)
@NoArgsConstructor @ToString
public class ObjectRenderer extends StringRenderer {
    @Override
    public void renderTo(ObjectNode bundle, Object object) {
        super.renderTo(bundle, String.valueOf(object));
    }
}