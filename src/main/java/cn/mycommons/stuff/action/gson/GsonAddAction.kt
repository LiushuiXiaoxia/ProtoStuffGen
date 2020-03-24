package cn.mycommons.stuff.action.gson

import cn.mycommons.stuff.action.base.AddAction
import cn.mycommons.stuff.core.JsonType

class GsonAddAction : AddAction() {

    override fun getJsonType(): JsonType = JsonType.Gson
}