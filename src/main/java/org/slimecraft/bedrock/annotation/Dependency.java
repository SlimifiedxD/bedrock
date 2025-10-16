package org.slimecraft.bedrock.annotation;

import org.slimecraft.bedrock.dependency.LoadOrder;
import org.slimecraft.bedrock.dependency.LoadStage;

public @interface Dependency {
    String name();
    LoadStage loadStage();
    LoadOrder loadOrder();
    boolean required() default false;
    boolean joinClasspath() default true;
}
