package cn.mycommons.stuff.action.tag

import cn.mycommons.stuff.action.base.AddAction
import cn.mycommons.stuff.core.JsonType

class TagAddAction : AddAction() {

    override fun getJsonType(): JsonType = JsonType.ProtoStuffTag
}