package cn.mycommons.stuff.core.gen

import cn.mycommons.stuff.core.IGenerateRule

/**
 * abc --> ABC
 */
class UppercaseGenerateImpl : IGenerateRule {

    override fun gen(name: String): String = name.toUpperCase()
}