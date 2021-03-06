package cn.mycommons.stuff.action.more

import cn.mycommons.stuff.action.util.isFileOk
import cn.mycommons.stuff.core.FileType
import cn.mycommons.stuff.core.IFileModify
import cn.mycommons.stuff.core.IGenerateRule
import cn.mycommons.stuff.core.JsonType
import cn.mycommons.stuff.core.impl.JavaImpl
import cn.mycommons.stuff.core.impl.KotlinImpl
import cn.mycommons.stuff.ui.ConfigDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiJavaFile
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtFile


class MorAction : AnAction() {

    override fun update(event: AnActionEvent) {
        event.presentation.isVisible = isFileOk(event)
    }

    override fun actionPerformed(event: AnActionEvent) {
        try {
            doAction(event)
        } catch (e: Exception) {
            e.printStackTrace()
            val project = event.getData(PlatformDataKeys.PROJECT)
            Messages.showMessageDialog(project, e.message, "Warning", Messages.getWarningIcon())
        }
    }

    private fun doAction(event: AnActionEvent) {
        var msg: String? = null
        do {
            val psiFile = event.getData(LangDataKeys.PSI_FILE)
            if (psiFile == null) {
                msg = "No file"
                break
            }

            println("psiFile = $psiFile")

            var fileType: FileType?

            if (psiFile is PsiJavaFile) {
                fileType = FileType.JavaFile
            } else if (psiFile is KtFile) {
                fileType = FileType.KotlinFile
            } else {
                msg = "is not java or kotlin file"
                break
            }

            val project = event.getData(PlatformDataKeys.PROJECT)

            if (project != null) {
                val dialog = ConfigDialog()
                dialog.setOnCallback(object : ConfigDialog.OnCallback {

                    override fun onOk(jsonType: JsonType, rule: IGenerateRule) {
                        println("jsonType = $jsonType, rule = $rule")
                        onOk(project, psiFile, fileType, jsonType, rule)
                    }

                    override fun onCancel() {
                    }
                })
                dialog.show()
            }
        } while (false)

        println("msg = $msg")

        if (msg != null && msg.isNotEmpty()) {
            throw RuntimeException(msg)
        }
    }

    private fun onOk(project: Project,
                     psiFile: PsiFile,
                     fileType: FileType,
                     jsonType: JsonType,
                     rule: IGenerateRule) {
        WriteCommandAction.runWriteCommandAction(project) {
            psiFile.children
                    .filter {
                        println("PsiFile1 -> $psiFile")
                        println("it1 -> $it")
                        it is PsiClass || it is KtClass
                    }
                    .forEach { doModify(project, psiFile, fileType, it, jsonType, rule) }
        }
    }


    private fun doModify(project: Project,
                         psiFile: PsiFile,
                         fileType: FileType,
                         clazz: PsiElement,
                         jsonType: JsonType,
                         rule: IGenerateRule) {

        val fileModify: IFileModify? = when (fileType) {
            FileType.JavaFile -> {
                JavaImpl(project, psiFile as PsiJavaFile, clazz as PsiClass, jsonType, rule)
            }
            FileType.KotlinFile -> {
                when (clazz) {
                    is KtClass -> KotlinImpl(project, psiFile as KtFile, clazz, null, jsonType, rule)
                    is KtClassBody -> KotlinImpl(project, psiFile as KtFile, null, clazz, jsonType, rule)
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
                    doModify(project, psiFile, fileType, it, jsonType, rule)
                }
    }
}