package cn.mycommons.stuff.action.fastjson

import cn.mycommons.stuff.action.base.AddAction
import cn.mycommons.stuff.core.JsonType

class FastJsonAddAction : AddAction() {

    override fun getJsonType(): JsonType = JsonType.FastJson
}