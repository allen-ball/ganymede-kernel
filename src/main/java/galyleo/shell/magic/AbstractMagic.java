package galyleo.shell.magic;

import lombok.NoArgsConstructor;
import org.springframework.util.PropertyPlaceholderHelper;

import static lombok.AccessLevel.PROTECTED;

/**
 * Abstract base class for {@link galyleo.shell.Magic}s.
 *
 * @author {@link.uri mailto:ball@hcf.dev Allen D. Ball}
 * @version $Revision$
 */
@NoArgsConstructor(access = PROTECTED)
public abstract class AbstractMagic implements AnnotatedMagic {

    /**
     * Common static {@link PropertyPlaceholderHelper}.
     */
    protected static final PropertyPlaceholderHelper HELPER =
        new PropertyPlaceholderHelper("${", "}", ":", true);
}