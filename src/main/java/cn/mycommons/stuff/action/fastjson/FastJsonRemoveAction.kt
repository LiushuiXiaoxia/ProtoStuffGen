package cn.mycommons.stuff.action.fastjson

import cn.mycommons.stuff.action.base.RemoveAction
import cn.mycommons.stuff.core.JsonType

open class FastJsonRemoveAction : RemoveAction() {

    override fun getJsonType(): JsonType = JsonType.FastJson
}