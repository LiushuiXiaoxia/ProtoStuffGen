package cn.mycommons.stuff.action.base

import cn.mycommons.stuff.core.FileType
import cn.mycommons.stuff.core.IFileModify
import cn.mycommons.stuff.core.impl.JavaImpl
import cn.mycommons.stuff.core.impl.KotlinImpl
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiJavaFile
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtFile

abstract class AddAction : BaseAction() {

    override fun doModify(project: Project, psiFile: PsiFile, fileType: FileType, clazz: PsiElement) {
        val fileModify: IFileModify? = when (fileType) {
            FileType.JavaFile -> {
                JavaImpl(project, psiFile as PsiJavaFile, clazz as PsiClass, getJsonType())
            }
            FileType.KotlinFile -> {
                when (clazz) {
                    is KtClass -> KotlinImpl(project, psiFile as KtFile, clazz, null, getJsonType())
                    is KtClassBody -> KotlinImpl(project, psiFile as KtFile, null, clazz, getJsonType())
                    else -> null
                }
            }
        }
        fileModify?.addAnnotation()

        // 处理内部类
        clazz.children
                .filter {
                    it is PsiClass || it is KtClass || it is KtClassBody
                }
                .forEach {
                    doModify(project, psiFile, fileType, it)
                }
    }
}