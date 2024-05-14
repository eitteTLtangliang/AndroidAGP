package com.liang.plugin.methodrecord

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MethodRecordPlugin:Plugin<Project> {

    override fun apply(project: Project) {
        val androidComponent = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponent.onVariants { variant ->
            variant.instrumentation.transformClassesWith(MethodRecordTransform::class.java, InstrumentationScope.PROJECT) {}
            variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS)
        }
    }

}