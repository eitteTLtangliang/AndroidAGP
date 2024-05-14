package com.liang.plugin.timecost

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class TimeCostClassVisitor(nextVisitor: ClassVisitor, private val className: String) : ClassVisitor(Opcodes.ASM5, nextVisitor) {

    override fun visitMethod(access: Int, name: String, descriptor: String, signature: String, exceptions: Array<out String>): MethodVisitor {
        val oldMethodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        val newMethodVisitor = object : AdviceAdapter(Opcodes.ASM5, oldMethodVisitor, access, name, descriptor) {
                override fun onMethodEnter() {
                    if (isNeedVisitMethod(name)) {
                        mv.visitLdcInsn(name)
                        mv.visitLdcInsn(className)
                        mv.visitMethodInsn(INVOKEDYNAMIC, "com/liang/androidagp/TimeCache", "putStartTime", "(Ljava/lang/String;Ljava/lang/String;)V", false)
                    }
                    super.onMethodEnter()
                }

                override fun onMethodExit(opcode: Int) {
                    if (isNeedVisitMethod(name)) {
                        mv.visitLdcInsn(name)
                        mv.visitLdcInsn(className)
                        mv.visitMethodInsn(INVOKESTATIC,"com/liang/androidagp/TimeCache","putEndTime","(Ljava/lang/String;Ljava/lang/String;)V", false)
                    }
                    super.onMethodExit(opcode)
                }

            }
        return newMethodVisitor
    }

    private fun isNeedVisitMethod(name: String): Boolean {
        return name != "putStartTime" && name != "putEndTime" && name != "<clinit>" && name != "printlnTime" && name != "<init>"
    }

}