package com.github.wlmxenl.exercise.custom.flowtag

import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.github.wlmxenl.exercise.common.page.BaseFragment
import com.github.wlmxenl.exercise.custom.databinding.FlowTagFragmentBinding

/**
 * FlowLayout 示例
 * @author wangzf
 * @date 2022/2/13
 */
class FlowLayoutFragment : BaseFragment<FlowTagFragmentBinding>() {

    override fun onPageViewCreated(savedInstanceState: Bundle?) {
        appBarView?.setTitle("FlowTagLayout")

        val countryList = arrayOf("阿富汗", "奥兰群岛", "阿尔巴尼亚", "阿尔及利亚", "美属萨摩亚", "安道尔", "安哥拉",
            "安圭拉", "南极洲", "安提瓜和巴布达", "阿根廷", "亚美尼亚", "阿鲁巴", "澳大利亚", "奥地利", "阿塞拜疆",
            "巴哈马", "巴林", "孟加拉国", "巴巴多斯", "白俄罗斯", "比利时", "伯利兹", "贝宁", "百慕大", "不丹",
            "玻利维亚", "波黑", "博茨瓦纳", "布维岛", "巴西", "英属印度洋领地", "文莱", "保加利亚", "布基纳法索",
            "布隆迪", "柬埔寨", "喀麦隆", "加拿大", "佛得角", "开曼群岛", "中非", "乍得", "智利", "中国",
            "圣诞岛", "科科斯（基林）群岛", "哥伦比亚", "科摩罗", "刚果（布）", "刚果（金）", "库克群岛",
            "哥斯达黎加", "科特迪瓦", "克罗地亚", "古巴", "塞浦路斯", "捷克", "丹麦", "吉布提", "多米尼克",
            "多米尼加", "厄瓜多尔", "埃及", "萨尔瓦多", "赤道几内亚", "厄立特里亚", "爱沙尼亚", "埃塞俄比亚",
            "福克兰群岛（马尔维纳斯）", "法罗群岛", "斐济", "芬兰", "法国", "法属圭亚那", "法属波利尼西亚",
            "法属南部领地", "加蓬", "冈比亚", "格鲁吉亚", "德国", "加纳", "直布罗陀", "希腊", "格陵兰", "格林纳达",
            "瓜德罗普", "关岛", "危地马拉", "格恩西岛", "几内亚", "几内亚比绍", "圭亚那", "海地", "赫德岛和麦克唐纳岛",
            "梵蒂冈", "洪都拉斯", "香港", "匈牙利", "冰岛", "印度", "印度尼西亚", "伊朗", "伊拉克", "爱尔兰",
            "英国属地曼岛", "以色列", "意大利", "牙买加", "日本", "泽西岛", "约旦", "哈萨克斯坦", "肯尼亚",
            "基里巴斯", "朝鲜", "韩国", "科威特", "吉尔吉斯斯坦", "老挝", "拉脱维亚", "黎巴嫩", "莱索托",
            "利比里亚", "利比亚", "列支敦士登", "立陶宛", "卢森堡", "澳门", "前南马其顿", "马达加斯加", "马拉维",
            "马来西亚", "马尔代夫", "马里", "马耳他", "马绍尔群岛", "马提尼克", "毛利塔尼亚", "毛里求斯", "马约特",
            "墨西哥", "密克罗尼西亚联邦", "摩尔多瓦", "摩纳哥", "蒙古", "黑山", "蒙特塞拉特", "摩洛哥", "莫桑比克",
            "缅甸", "纳米比亚", "瑙鲁", "尼泊尔", "荷兰", "荷属安的列斯", "新喀里多尼亚", "新西兰", "尼加拉瓜",
            "尼日尔", "尼日利亚", "纽埃", "诺福克岛", "北马里亚纳", "挪威", "阿曼", "巴基斯坦", "帕劳", "巴勒斯坦",
            "巴拿马", "巴布亚新几内亚", "巴拉圭", "秘鲁", "菲律宾", "皮特凯恩", "波兰", "葡萄牙", "波多黎各",
            "卡塔尔", "留尼汪", "罗马尼亚", "俄罗斯联邦", "卢旺达", "圣赫勒拿", "圣基茨和尼维斯", "圣卢西亚",
            "圣皮埃尔和密克隆", "圣文森特和格林纳丁斯", "萨摩亚", "圣马力诺", "圣多美和普林西比", "沙特阿拉伯",
            "塞内加尔", "塞尔维亚", "塞舌尔", "塞拉利昂", "新加坡", "斯洛伐克", "斯洛文尼亚", "所罗门群岛",
            "索马里", "南非", "南乔治亚岛和南桑德韦奇岛", "西班牙", "斯里兰卡", "苏丹", "苏里南",
            "斯瓦尔巴岛和扬马延岛", "斯威士兰", "瑞典", "瑞士", "叙利亚", "台湾", "塔吉克斯坦", "坦桑尼亚",
            "泰国", "东帝汶", "多哥", "托克劳", "汤加", "特立尼达和多巴哥", "突尼斯", "土耳其", "土库曼斯坦",
            "特克斯和凯科斯群岛", "图瓦卢", "乌干达", "乌克兰", "阿联酋", "英国", "美国", "美国本土外小岛屿",
            "乌拉圭", "乌兹别克斯坦", "瓦努阿图", "委内瑞拉", "越南", "英属维尔京群岛", "美属维尔京群岛",
            "瓦利斯和富图纳", "西撒哈拉", "也门", "赞比亚", "津巴布韦")

        for (element in countryList) {
            val marginLayoutParams = ViewGroup.MarginLayoutParams(-2, -2)
            // marginLayoutParams.setMargins(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics).toInt())
            binding.flowTagLayout.addView(FlowTagView(requireActivity()).apply {
                setPadding(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, resources.displayMetrics).toInt())
                setText(element)
            }, marginLayoutParams)
        }
    }
}