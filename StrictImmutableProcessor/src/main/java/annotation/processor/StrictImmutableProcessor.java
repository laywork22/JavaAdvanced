package annotation.processor;

import annotation.StrictImmutable;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("annotation.StrictImmutable")
public class StrictImmutableProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(StrictImmutable.class).stream().
                filter((element) -> element.getKind() == ElementKind.CLASS).
                collect(Collectors.toSet());

        for (Element e : annotatedElements) {
            for (Element ef : e.getEnclosedElements()) {
                if (ef.getKind() == ElementKind.FIELD) {
                    checkIfImmutable(e, e.getSimpleName().toString());
                }
            }
        }

        return false;
    }

    private void checkIfImmutable(Element e, String className) {
        Set<? extends Modifier> fieldModifiers = e.getModifiers();
        boolean isFinal = fieldModifiers.contains(Modifier.FINAL);


        if (!isFinal) {
            processingEnv.getMessager().printError("I campi " + className +  " devono essere dichiarati come final in una classe @StrictImmutable", e);
        }
    }
}
