package org.slimecraft.bedrock.internal;

import org.slimecraft.bedrock.annotation.PluginConfig;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@SupportedAnnotationTypes("org.slimecraft.bedrock.annotation.PluginConfig")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
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
                        final String qualifiedName = typeElement.getQualifiedName().toString();
                        final TypeElement javaPluginElement = processingEnv.getElementUtils().getTypeElement("org.bukkit.plugin.java.JavaPlugin");
                        if (javaPluginElement == null) continue;
                        if (!processingEnv.getTypeUtils().isAssignable(typeElement.asType(), javaPluginElement.asType())) continue;

                        data.put("main", qualifiedName);

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

                        final FileObject yamlFile = filer.createResource(
                                StandardLocation.CLASS_OUTPUT,
                                "",
                                "paper-plugin.yml"
                        );

                        final DumperOptions options = new DumperOptions();

                        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
                        options.setPrettyFlow(true);

                        final Yaml yaml = new Yaml(options);

                        try (final Writer writer = yamlFile.openWriter()) {
                            yaml.dump(data, writer);
                        }

                        final FileObject bedrockInitFile = filer.createSourceFile("org.slimecraft.bedrock.generated.GeneratedBedrockInit");
                        final String codeName = qualifiedName + ".class";
                        try (final Writer writer = bedrockInitFile.openWriter()) {
                            writer.write(
                                    """
                                            package org.slimecraft.bedrock.generated;
                                            
                                            import org.slimecraft.bedrock.internal.Bedrock;
                                            import org.slimecraft.bedrock.util.FastBoardHelper
                                            import org.bukkit.plugin.java.JavaPlugin;
                                            
                                            public class GeneratedBedrockInit {
                                                static {
                                                    Bedrock.init(JavaPlugin.getProvidingPlugin(%s));
                                                    FastBoardHelper.init();
                                                }
                                            }
                                            """.formatted(codeName));
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