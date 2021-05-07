package ganymede.jsr223;
/*-
 * ##########################################################################
 * Ganymede
 * %%
 * Copyright (C) 2021 Allen D. Ball
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ##########################################################################
 */
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import javax.script.ScriptContext;
import javax.script.ScriptException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import static javax.script.ScriptContext.ENGINE_SCOPE;
import static lombok.AccessLevel.PROTECTED;

/**
 * Handlebars {@link javax.script.ScriptEngine}.
 *
 * {@bean.info}
 *
 * @see Handlebars
 * @see Context
 *
 * @author {@link.uri mailto:ball@hcf.dev Allen D. Ball}
 */
@RequiredArgsConstructor(access = PROTECTED) @Getter @ToString @Log4j2
public class HandlebarsScriptEngine extends AbstractTemplateScriptEngine {
    private final HandlebarsScriptEngineFactory factory;
    private final Handlebars handlebars = new Handlebars();

    @Override
    public String eval(String script, ScriptContext context) throws ScriptException {
        var bindings = context.getBindings(ENGINE_SCOPE);
        var out = "";

        try {
            var template = handlebars.compileInline(script);

            out = template.apply(Context.newContext(bindings));
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }

        return out;
    }
}
