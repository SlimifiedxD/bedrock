package org.slimecraft.bedrock.annotation;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.*;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@SupportedAnnotationTypes("org.slimecraft.bedrock.annotation.PluginConfig")
public class PluginConfigProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            for (final Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                final Filer filer = processingEnv.getFiler();
                for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
                    try {
                        if (!mirror.getAnnotationType().toString().equals(PluginConfig.class.getCanonicalName()))
                            continue;
                        final Map<? extends ExecutableElement, ? extends AnnotationValue> values = mirror.getElementValues();
                        final Map<String, Object> data = new LinkedHashMap<>();
                        final TypeElement typeElement = (TypeElement) element;

                        data.put("main", typeElement.getQualifiedName().toString());

                        for (ExecutableElement method : mirror
                                .getAnnotationType()
                                .asElement()
                                .getEnclosedElements()
                                .stream()
                                .filter(e -> e.getKind() == ElementKind.METHOD)
                                .map(ExecutableElement.class::cast)
                                .toList()) {
                            final String paramName = method.getSimpleName().toString();
                            final AnnotationValue explicitValue = values.get(method);
                            final Object paramValue = explicitValue != null
                                    ? explicitValue.getValue()
                                    : method.getDefaultValue().getValue();
                            if (paramName.equals("apiVersion")) {
                                data.put("api-version", paramValue);
                                continue;
                            }
                            data.put(paramName, paramValue);
                        }

                        final FileObject file = filer.createResource(
                                StandardLocation.CLASS_OUTPUT,
                                "",
                                "paper-plugin.yml"
                        );

                        final DumperOptions options = new DumperOptions();

                        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
                        options.setPrettyFlow(true);

                        final Yaml yaml = new Yaml(options);

                        try (final Writer writer = file.openWriter()) {
                            yaml.dump(data, writer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }
}