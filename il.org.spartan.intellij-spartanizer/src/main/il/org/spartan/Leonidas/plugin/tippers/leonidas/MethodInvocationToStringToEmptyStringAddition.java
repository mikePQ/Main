package il.org.spartan.Leonidas.plugin.tippers.leonidas;

import il.org.spartan.Leonidas.auxilary_layer.ExampleMapFactory;

import java.util.Map;

import static il.org.spartan.Leonidas.plugin.leonidas.The.element;

/**
 * s.toString() => "" + x
 *
 * @author Oren Afek
 * @since 31-05-2017
 */
@SuppressWarnings("ALL")
public class MethodInvocationToStringToEmptyStringAddition implements LeonidasTipperDefinition {

    Object identifier0;

    @Override
    public void constraints() {
        element(0).asIdentifier.notExpressionStatement();
        element(0).asIdentifier.noMoreMethodsApply();
    }

    @Override
    public void matcher() {
        new Template(() ->
                /* start */
                identifier0.toString()
                /* end */
        );
    }

    @Override
    public void replacer() {
        new Template(() ->
                /* start */
                "" + identifier0
                /* end */
        );
    }


    @Override
    public Map<String, String> getExamples() {
        return new ExampleMapFactory()
                .put("donald.toString()", "\"\" + donald")
                .put("donald.toString();", null)
                .put("donald.string()", null)
                .put("donald.string().equals()", null)
                .put("String str2 = i.toString() + \"whwh\";", "String str2 = \"\" + i + \"whwh\";")
                .map();
    }
}